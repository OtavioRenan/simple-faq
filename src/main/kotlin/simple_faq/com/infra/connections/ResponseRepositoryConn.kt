package simple_faq.com.infra.connections

import org.springframework.data.jpa.repository.JpaRepository
import simple_faq.com.infra.entities.ResponseEntity

interface ResponseRepositoryConn: JpaRepository<ResponseEntity, Long> {
    fun findByDoubtId(doubtId: Long): List<ResponseEntity>;
}