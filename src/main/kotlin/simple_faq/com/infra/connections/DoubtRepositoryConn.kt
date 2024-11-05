package simple_faq.com.infra.connections

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import simple_faq.com.infra.entities.DoubtEntity

interface DoubtRepositoryConn : JpaRepository<DoubtEntity, Long> {
    @Query(
        value = "SELECT * FROM faq_doubts " +
                "WHERE ( (:fields IS NULL OR :fields = '') OR title LIKE %:fields% " +
                "OR description LIKE %:fields% ) ", nativeQuery = true
    )
    fun findAll(fields: String): List<DoubtEntity>;
}