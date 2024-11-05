package simple_faq.com.domain.dtos

import simple_faq.com.domain.Response

class DoubtDTO(
    val title: String,
    val responses: List<Response>,
)