package simple_faq.com.domain.adapters

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito.*
import simple_faq.com.domain.Response
import simple_faq.com.domain.utils.GlobalTestUtil
import simple_faq.com.domain.exceptions.ResponseNotFundException
import simple_faq.com.domain.ports.interfaces.ResponseServicePort
import simple_faq.com.domain.ports.repositories.ResponseRepositoryPort
import simple_faq.com.domain.services.GlobalUtilService

class ResponseServiceImpTest {
    private val repository: ResponseRepositoryPort = mock(ResponseRepositoryPort::class.java);

    private val util: GlobalTestUtil = GlobalTestUtil();

    private lateinit var service: ResponseServicePort;

    private lateinit var response: Response;

    @BeforeEach
    fun setup() {
        service = ResponseServiceImp(repository, GlobalUtilService());

        response = util.buildResponse();
    }

    @Test
    fun success_when_access_findAll() {
        val expected: List<Response> = listOf(response);

        `when`(repository.findAll()).thenReturn(expected);

        val actual: List<Response> = service.findAll();


        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
        assertEquals(actual.size, expected.size);
    }

    @Test
    fun success_when_access_findByDoubtId() {
        val expected: List<Response> = listOf(response);

        `when`(repository.findByDoubtId(response.doubtId)).thenReturn(expected);

        val actual: List<Response> = service.findByDoubtId(response.doubtId);

        verify(repository).findByDoubtId(response.doubtId);
        verifyNoMoreInteractions(repository);
        assertEquals(actual.size, expected.size);
    }

    @Test
    fun success_when_access_findById() {
        `when`(repository.findById(response.id)).thenReturn(response);

        val actual: Response = service.findById(response.id);

        verify(repository).findById(response.id);
        verifyNoMoreInteractions(repository);
        assertEquals(actual.title, response.title);
        assertEquals(actual.description, response.description);
    }

    @Test
    fun error_when_access_findById() {
        `when`(repository.findById(response.id)).thenThrow(ResponseNotFundException());

        val expected = org.junit.jupiter.api.assertThrows<ResponseNotFundException> {
            service.findById(response.id);
        }

        verify(repository).findById(response.id);
        verifyNoMoreInteractions(repository);
        assertEquals("Resposta não encontrada.", expected.message);
    }

    @Test
    fun success_when_create() {
        `when`(repository.create(response)).thenReturn(response);

        val actual: Response = service.create(response);

        verify(repository).create(response);
        verifyNoMoreInteractions(repository);
        assertEquals(actual.id, response.id);
    }

    @Test
    fun success_when_update() {
        `when`(repository.findById(response.id)).thenReturn(response);

        val expected: Response = util.buildResponse(response.id, response.doubtId, response.created);

        `when`(repository.update(response.id, expected)).thenReturn(expected);

        val actual: Response = service.update(response.id, expected);

        verify(repository).findById(response.id);
        verify(repository).update(response.id, expected);
        assertEquals(expected.title, actual.title);
        assertEquals(expected.description, actual.description);
        assertEquals(expected.updated, actual.updated);
    }

    @Test
    fun success_when_delete() {
        `when`(repository.findById(response.id)).thenReturn(response);

        service.deleteById(response.id);

        verify(repository).findById(response.id);
        verify(repository).deleteById(response.id);
    }
}