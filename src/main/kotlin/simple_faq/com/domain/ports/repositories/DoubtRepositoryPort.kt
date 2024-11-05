package simple_faq.com.domain.ports.repositories

import simple_faq.com.domain.Doubt

interface DoubtRepositoryPort {
    fun findAll(): List<Doubt>;

    fun findAll(fields: String): List<Doubt>;

    fun findById(id: Long): Doubt;

    fun create(doubt: Doubt): Doubt;

    fun update(id: Long, doubt: Doubt): Doubt;

    fun deleteById(id: Long);
}