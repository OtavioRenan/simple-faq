package simple_faq.com.domain.ports.interfaces

import simple_faq.com.domain.dtos.DoubtDTO

interface DoubtAndResponseServicePort {
    fun findAll(inputs: Map<String, String>): List<DoubtDTO>;
}