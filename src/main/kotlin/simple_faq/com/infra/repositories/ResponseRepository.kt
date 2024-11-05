package simple_faq.com.infra.repositories

import org.springframework.stereotype.Component
import simple_faq.com.domain.Response
import simple_faq.com.domain.exceptions.ResponseNotFundException
import simple_faq.com.domain.ports.repositories.ResponseRepositoryPort
import simple_faq.com.infra.connections.ResponseRepositoryConn
import simple_faq.com.infra.entities.ResponseEntity

@Component
class ResponseRepository(private var repository: ResponseRepositoryConn) : ResponseRepositoryPort {
    override fun findAll(): List<Response> {
        return repository.findAll().map { response -> response.toResponse() }.toMutableList();
    }

    override fun findByDoubtId(doubtId: Long): List<Response> {
        return repository.findByDoubtId(doubtId).map { response -> response.toResponse() }.toMutableList();
    }

    override fun findById(id: Long): Response {
        return repository.findById(id).orElseThrow { ResponseNotFundException() }.toResponse();
    }

    override fun create(response: Response): Response {
        return repository.save(
            ResponseEntity(
                response.id,
                response.doubtId,
                response.title,
                response.description,
                response.created,
                response.updated
            )
        ).toResponse()
    }

    override fun update(id: Long, response: Response): Response {
        response.id = id;
        return create(response);
    }

    override fun deleteById(id: Long) {
        repository.deleteById(id);
    }
}