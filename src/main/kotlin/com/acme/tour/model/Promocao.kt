package com.acme.tour.model

data class Promocao(
    val id: Long,
    val descricao: String,
    val preco: Double,
    val local: String,
    val isAllInclusive: Boolean,
    val qtdDias: Int,
)
