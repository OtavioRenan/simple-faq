package simple_faq.com.domain.ports.interfaces

import simple_faq.com.domain.Doubt

interface DoubtServicePort {
    fun findAll(): List<Doubt>;

    fun findAll(inputs: Map<String, String>): List<Doubt>;

    fun findById(id: Long): Doubt;

    fun create(response: Doubt): Doubt;

    fun update(id: Long, response: Doubt): Doubt;

    fun deleteById(id: Long);
}