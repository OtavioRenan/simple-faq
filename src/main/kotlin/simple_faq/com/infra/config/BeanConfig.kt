package simple_faq.com.infra.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.validation.annotation.Validated
import simple_faq.com.domain.adapters.DoubtAndResponseServiceImp
import simple_faq.com.domain.adapters.DoubtServiceImp
import simple_faq.com.domain.adapters.ResponseServiceImp
import simple_faq.com.domain.ports.interfaces.DoubtAndResponseServicePort
import simple_faq.com.domain.ports.interfaces.DoubtServicePort
import simple_faq.com.domain.ports.interfaces.ResponseServicePort
import simple_faq.com.domain.ports.repositories.DoubtRepositoryPort
import simple_faq.com.domain.ports.repositories.ResponseRepositoryPort
import simple_faq.com.domain.services.GlobalUtilService

@Validated
@Configuration
class BeanConfig {

    @Bean
    fun utilService(): GlobalUtilService {
        return GlobalUtilService()
    }

    @Bean
    fun doubtService(doubtRepositoryPort: DoubtRepositoryPort): DoubtServicePort {
        return DoubtServiceImp(doubtRepositoryPort, utilService());
    }

    @Bean
    fun responseService(responseRepositoryPort: ResponseRepositoryPort): ResponseServicePort {
        return ResponseServiceImp(responseRepositoryPort, utilService());
    }

    @Bean
    fun doubtAndResponseService(
        doubtRepositoryPort: DoubtRepositoryPort,
        responseRepositoryPort: ResponseRepositoryPort
    ): DoubtAndResponseServicePort {
        return DoubtAndResponseServiceImp(doubtService(doubtRepositoryPort), responseService(responseRepositoryPort));
    }
}