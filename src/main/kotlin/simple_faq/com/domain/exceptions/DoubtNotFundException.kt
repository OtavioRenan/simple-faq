package simple_faq.com.domain.exceptions

import org.springframework.http.HttpStatus

class DoubtNotFundException(): RuntimeException("Pergunta não encontrada.") {
    var status: HttpStatus = HttpStatus.BAD_REQUEST;
}