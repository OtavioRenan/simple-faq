package simple_faq.com.domain

import java.sql.Timestamp

class Doubt(
    var id: Long,

    var title: String,

    var description: String,

    var created: Timestamp?,

    var updated: Timestamp?
)