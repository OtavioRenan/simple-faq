package simple_faq.com.domain.ports.interfaces

import simple_faq.com.domain.Response

interface ResponseServicePort {
    fun findAll(): List<Response>;

    fun findByDoubtId(doubtId: Long): List<Response>;

    fun findById(id: Long): Response;

    fun create(response: Response): Response;

    fun update(id: Long, response: Response): Response;

    fun deleteById(id: Long);
}