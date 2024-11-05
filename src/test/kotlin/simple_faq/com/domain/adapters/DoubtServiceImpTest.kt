package simple_faq.com.domain.adapters

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*

import simple_faq.com.domain.Doubt
import simple_faq.com.domain.utils.GlobalTestUtil
import simple_faq.com.domain.exceptions.DoubtNotFundException

import simple_faq.com.domain.ports.interfaces.DoubtServicePort
import simple_faq.com.domain.ports.repositories.DoubtRepositoryPort
import simple_faq.com.domain.services.GlobalUtilService

class DoubtServiceImpTest {
    private val repository: DoubtRepositoryPort = mock(DoubtRepositoryPort::class.java);

    private val util: GlobalTestUtil = GlobalTestUtil();

    private lateinit var service: DoubtServicePort;

    private lateinit var doubt: Doubt;

    @BeforeEach
    fun setup() {
        service = DoubtServiceImp(repository, GlobalUtilService());

        doubt = util.buildDoubt();
    }

    @Test
    fun success_when_access_findAll() {
        val expected: List<Doubt> = listOf(doubt);

        `when`(repository.findAll()).thenReturn(expected);

        val actual: List<Doubt> = service.findAll();

        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
        assertEquals(actual.size, expected.size);
    }

    @Test
    fun success_when_access_findAll_with_inputs() {
        val expected: List<Doubt> = listOf(doubt);

        `when`(repository.findAll("")).thenReturn(expected);

        val actual: List<Doubt> = service.findAll(hashMapOf());

        verify(repository).findAll("");
        verifyNoMoreInteractions(repository);
        assertEquals(actual.size, expected.size);
    }

    @Test
    fun success_when_access_findById() {
        `when`(repository.findById(doubt.id)).thenReturn(doubt);

        val actual: Doubt = service.findById(doubt.id);

        verify(repository).findById(doubt.id);
        verifyNoMoreInteractions(repository);
        assertEquals(actual.title, doubt.title);
        assertEquals(actual.description, doubt.description);
    }

    @Test
    fun error_when_access_findById() {
        `when`(repository.findById(doubt.id)).thenThrow(DoubtNotFundException());

        val expected = assertThrows<DoubtNotFundException> {
            service.findById(doubt.id);
        }

        verify(repository).findById(doubt.id);
        verifyNoMoreInteractions(repository);
        assertEquals("Pergunta não encontrada.", expected.message);
    }

    @Test
    fun success_when_create() {
        `when`(repository.create(doubt)).thenReturn(doubt);

        val actual: Doubt = service.create(doubt);

        verify(repository).create(doubt);
        verifyNoMoreInteractions(repository);
        assertEquals(actual.id, doubt.id);
    }

    @Test
    fun success_when_update() {
        `when`(repository.findById(doubt.id)).thenReturn(doubt);

        val expected: Doubt = util.buildDoubt(doubt.id, doubt.created);

        `when`(repository.update(doubt.id, expected)).thenReturn(expected);

        val actual: Doubt = service.update(doubt.id, expected);

        verify(repository).findById(doubt.id);
        verify(repository).update(doubt.id, expected);
        assertEquals(expected.title, actual.title);
        assertEquals(expected.description, actual.description);
        assertEquals(expected.updated, actual.updated);
    }

    @Test
    fun success_when_delete() {
        `when`(repository.findById(doubt.id)).thenReturn(doubt);

        service.deleteById(doubt.id);

        verify(repository).findById(doubt.id);
        verify(repository).deleteById(doubt.id);
    }
}