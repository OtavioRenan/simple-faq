package simple_faq.com.infra.repositories

import org.springframework.stereotype.Component
import simple_faq.com.domain.Doubt
import simple_faq.com.domain.exceptions.DoubtNotFundException
import simple_faq.com.domain.ports.repositories.DoubtRepositoryPort
import simple_faq.com.infra.connections.DoubtRepositoryConn
import simple_faq.com.infra.entities.DoubtEntity

@Component
class DoubtRepository(private var repository: DoubtRepositoryConn) : DoubtRepositoryPort {
    override fun findAll(): List<Doubt> {
        return repository.findAll().map { doubt -> doubt.toDoubt() };
    }

    override fun findAll(fields: String): List<Doubt> {
        return repository.findAll(fields).map { doubt -> doubt.toDoubt() };
    }

    override fun findById(id: Long): Doubt {
        return repository.findById(id).orElseThrow { DoubtNotFundException() }.toDoubt();
    }

    override fun create(doubt: Doubt): Doubt {
        return repository.save(
            DoubtEntity(
                doubt.id,
                doubt.title,
                doubt.description,
                doubt.created,
                doubt.updated
            )
        ).toDoubt()
    }

    override fun update(id: Long, doubt: Doubt): Doubt {
        doubt.id = id;
        return create(doubt);
    }

    override fun deleteById(id: Long) {
        repository.deleteById(id);
    }
}