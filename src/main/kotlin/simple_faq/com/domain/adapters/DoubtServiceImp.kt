package simple_faq.com.domain.adapters

import simple_faq.com.domain.Doubt
import simple_faq.com.domain.ports.interfaces.DoubtServicePort
import simple_faq.com.domain.ports.repositories.DoubtRepositoryPort
import simple_faq.com.domain.services.GlobalUtilService

class DoubtServiceImp(
    private val repository: DoubtRepositoryPort,
    private val utilService: GlobalUtilService
) : DoubtServicePort {
    override fun findAll(): List<Doubt> {
        return repository.findAll();
    }

    override fun findAll(inputs: Map<String, String>): List<Doubt> {
        return repository.findAll(inputs.getOrDefault("fields", ""));
    }

    override fun findById(id: Long): Doubt {
        return repository.findById(id);
    }

    override fun create(response: Doubt): Doubt {
        response.created = utilService.now();
        return repository.create(response);
    }

    override fun update(id: Long, doubt: Doubt): Doubt {
        val old = findById(id);

        doubt.created = old.created;
        doubt.updated = utilService.now();
        return repository.update(id, doubt);
    }

    override fun deleteById(id: Long) {
        repository.deleteById(findById(id).id);
    }
}