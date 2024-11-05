package simple_faq.com

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SimpleFaqApplication

fun main(args: Array<String>) {
	runApplication<SimpleFaqApplication>(*args)
}
