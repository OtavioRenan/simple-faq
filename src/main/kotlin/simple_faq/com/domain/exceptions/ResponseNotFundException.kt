package simple_faq.com.domain.exceptions

import org.springframework.http.HttpStatus

class ResponseNotFundException() : RuntimeException("Resposta não encontrada.") {
    var status: HttpStatus = HttpStatus.BAD_REQUEST;
}