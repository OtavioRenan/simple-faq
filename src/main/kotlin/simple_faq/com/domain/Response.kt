package simple_faq.com.domain

import java.sql.Timestamp

class Response(
    var id: Long,

    var doubtId: Long,

    var title: String,

    var description: String,

    var created: Timestamp?,

    var updated: Timestamp?
)