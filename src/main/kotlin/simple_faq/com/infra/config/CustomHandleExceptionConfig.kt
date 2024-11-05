package simple_faq.com.infra.config

import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import simple_faq.com.domain.exceptions.DoubtNotFundException
import simple_faq.com.domain.exceptions.ResponseNotFundException
import java.sql.Timestamp

@RestControllerAdvice
class CustomHandleExceptionConfig {

    @ExceptionHandler
    fun doubtNotFundException(ex: DoubtNotFundException): ResponseEntity<Any> {
        return ResponseEntity.status(ex.status).body(createMessageErrorApiResponse(ex, ex.status, hashMapOf()));
    }

    @ExceptionHandler
    fun responseNotFundException(ex: ResponseNotFundException): ResponseEntity<Any> {
        return ResponseEntity.status(ex.status).body(createMessageErrorApiResponse(ex, ex.status, hashMapOf()));
    }

    @ExceptionHandler
    fun constraintViolationException(ex: ConstraintViolationException): ResponseEntity<MessageErrorApiResponse> {
        val status: HttpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        return ResponseEntity.status(status)
            .body(createMessageErrorApiResponse(ex, status, createFieldsConstraintViolations(ex)));
    }

    private fun createFieldsConstraintViolations(ex: ConstraintViolationException): HashMap<String, String> {
        val fields: HashMap<String, String> = hashMapOf()

        ex.constraintViolations.forEach { violation ->
            fields[violation.propertyPath.toString()] = violation.message.toString()
        }

        return fields
    }

    private fun createMessageErrorApiResponse(
        ex: Throwable,
        status: HttpStatus,
        fields: HashMap<String, String>
    ): MessageErrorApiResponse {
        return MessageErrorApiResponse(
            Timestamp(System.currentTimeMillis()),
            status.value(),
            status.name,
            ex.localizedMessage,
            "",
            fields
        );
    }
}