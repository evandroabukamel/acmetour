package com.acme.tour.model

import com.fasterxml.jackson.annotation.*
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Client(
    @JsonProperty("idClient")
    val id: Long,
    val name: String,
    @JsonFormat(
        shape = JsonFormat.Shape.STRING,
        pattern = "yyyy-MM-dd"
    )
    val birthdate: LocalDateTime,
    val phone: Phone?,
)

@JsonIgnoreProperties("type")
data class Phone(
    val ddd: String = "",
    val number: String = "",
    val type: String = ""
)
