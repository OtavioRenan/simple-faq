package simple_faq.com.infra.entities

import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import simple_faq.com.domain.Doubt
import java.sql.Timestamp

@Entity(name = "faq_doubts")
class DoubtEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @get:NotEmpty(message = "Informe a título.")
    val title: String,

    @get:NotEmpty(message = "Informe a descrição.")
    val description: String,

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    val created: Timestamp?,

    @LastModifiedDate
    @Column(name = "updated_at", nullable = true)
    val updated: Timestamp?
) {
    fun toDoubt(): Doubt = Doubt(
        id,
        title,
        description,
        created,
        updated
    );
}