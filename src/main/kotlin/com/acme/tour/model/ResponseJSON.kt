package com.acme.tour.model

import java.time.LocalDateTime

data class ResponseJSON(
    val message: String = "",
    val datetime: LocalDateTime = LocalDateTime.now(),
)
