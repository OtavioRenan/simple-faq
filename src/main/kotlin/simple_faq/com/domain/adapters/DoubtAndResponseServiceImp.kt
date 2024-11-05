package simple_faq.com.domain.adapters

import simple_faq.com.domain.Doubt
import simple_faq.com.domain.dtos.DoubtDTO
import simple_faq.com.domain.ports.interfaces.DoubtAndResponseServicePort
import simple_faq.com.domain.ports.interfaces.ResponseServicePort
import simple_faq.com.domain.ports.interfaces.DoubtServicePort

class DoubtAndResponseServiceImp(
    private val doubtService: DoubtServicePort,
    private val responseService: ResponseServicePort
) : DoubtAndResponseServicePort {
    override fun findAll(inputs: Map<String, String>): List<DoubtDTO> {
        return doubtService.findAll(inputs).map { doubt -> buildDTO(doubt) };
    }

    private fun buildDTO(doubt: Doubt): DoubtDTO {
        return DoubtDTO(doubt.title, responseService.findByDoubtId(doubt.id));
    }
}