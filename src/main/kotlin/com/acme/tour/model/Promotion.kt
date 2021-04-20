package com.acme.tour.model

data class Promotion(
    val id: Long,
    val description: String,
    val price: Double,
    val local: String,
    val isAllInclusive: Boolean,
    val qtyDays: Int,
)
