package simple_faq.com.domain.adapters

import simple_faq.com.domain.Response
import simple_faq.com.domain.ports.interfaces.ResponseServicePort
import simple_faq.com.domain.ports.repositories.ResponseRepositoryPort
import simple_faq.com.domain.services.GlobalUtilService

class ResponseServiceImp(
    private val repository: ResponseRepositoryPort,
    private val utilService: GlobalUtilService
) : ResponseServicePort {
    override fun findAll(): List<Response> {
        return repository.findAll();
    }

    override fun findByDoubtId(doubtId: Long): List<Response> {
        return repository.findByDoubtId(doubtId);
    }

    override fun findById(id: Long): Response {
        return repository.findById(id);
    }

    override fun create(response: Response): Response {
        response.created = utilService.now();
        return repository.create(response);
    }

    override fun update(id: Long, response: Response): Response {
        val old = findById(id);

        response.created = old.created;
        response.updated = utilService.now();
        return repository.update(id, response);
    }

    override fun deleteById(id: Long) {
        repository.deleteById(findById(id).id);
    }
}