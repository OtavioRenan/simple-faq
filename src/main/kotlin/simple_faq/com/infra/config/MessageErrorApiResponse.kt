package simple_faq.com.infra.config

import java.sql.Timestamp

class MessageErrorApiResponse(
    var timestamp: Timestamp,
    var status: Int,
    var error: String,
    var message: String,
    var path: String,
    var fields: HashMap<String, String>
)