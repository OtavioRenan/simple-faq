package simple_faq.com.domain.services

import simple_faq.com.SimpleFaqApplication
import java.sql.Timestamp

class GlobalUtilService: SimpleFaqApplication() {
    fun now(): Timestamp {
        return Timestamp(System.currentTimeMillis());
    }
}