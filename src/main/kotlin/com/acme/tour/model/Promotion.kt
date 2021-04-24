package com.acme.tour.model

import javax.persistence.*

@Entity
@Table(name = "promotion")
data class Promotion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val description: String = "",
    val price: Double = 0.0,
    val local: String = "",

    @Column(name = "is_all_inclusive")
    val isAllInclusive: Boolean = false,

    @Column(name = "quantity_days")
    val qtyDays: Int = 0,
)
