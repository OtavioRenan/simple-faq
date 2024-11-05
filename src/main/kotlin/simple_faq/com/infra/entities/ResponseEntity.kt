package simple_faq.com.infra.entities

import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import simple_faq.com.domain.Response
import java.sql.Timestamp

@Entity(name = "faq_responses")
class ResponseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @get:NotNull(message = "Informe a pergunta.")
    @Column(name = "doubt_id")
    val doubtId: Long,

    @get:NotEmpty(message = "Informe o título.")
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
    fun toResponse(): Response = Response(
        id,
        doubtId,
        title,
        description,
        created,
        updated
    );
}