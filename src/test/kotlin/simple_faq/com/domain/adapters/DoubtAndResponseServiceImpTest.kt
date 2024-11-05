package simple_faq.com.domain.adapters

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito.*
import simple_faq.com.domain.Doubt
import simple_faq.com.domain.Response
import simple_faq.com.domain.utils.GlobalTestUtil
import simple_faq.com.domain.dtos.DoubtDTO
import simple_faq.com.domain.ports.interfaces.DoubtAndResponseServicePort
import simple_faq.com.domain.ports.repositories.DoubtRepositoryPort
import simple_faq.com.domain.ports.repositories.ResponseRepositoryPort
import simple_faq.com.domain.services.GlobalUtilService
import kotlin.random.Random

class DoubtAndResponseServiceImpTest {
    private val doubtRepository: DoubtRepositoryPort = mock(DoubtRepositoryPort::class.java);

    private val responseRepository: ResponseRepositoryPort = mock(ResponseRepositoryPort::class.java);

    private val util: GlobalTestUtil = GlobalTestUtil();

    private lateinit var service: DoubtAndResponseServicePort;

    @BeforeEach
    fun setup() {
        service = DoubtAndResponseServiceImp(
            DoubtServiceImp(doubtRepository, GlobalUtilService()),
            ResponseServiceImp(responseRepository, GlobalUtilService())
        );
    }

    @Test
    fun success_when_access_findAll() {
        val doubt: Doubt =  util.buildDoubt();

        val response: Response = util.buildResponse(doubt.id);

        val expected: List<DoubtDTO> = listOf(DoubtDTO(doubt.title, listOf(response)));

        `when`(doubtRepository.findAll("")).thenReturn(listOf(doubt));

        `when`(responseRepository.findByDoubtId(doubt.id)).thenReturn(listOf(response));

        val actual: List<DoubtDTO> = service.findAll(hashMapOf());

        verify(doubtRepository).findAll("");
        verify(responseRepository).findByDoubtId(doubt.id);
        assertEquals(actual.size, expected.size);
    }
}