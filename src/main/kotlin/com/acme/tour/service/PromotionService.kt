package com.acme.tour.service

import com.acme.tour.model.Promotion

interface PromotionService {

    fun getAll(
        page: Int,
        size: Int,
        sortBy: String? = "",
        sortDirection: String? = ""
    ): List<Promotion>

    fun getByLocal(
        localFilter: String = "",
        page: Int,
        size: Int,
        sortBy: String? = "local",
        sortDirection: String? = "ASC"
    ): List<Promotion>

    fun getById(id: Long): Promotion?

    fun create(promotion: Promotion)

    fun update(id: Long, promotion: Promotion)

    fun delete(id: Long)

    fun count(): Long

    fun getPromotionsCheaperThan1000(): List<Promotion>

    fun updatePriceByLocal(price: Double, local: String)
}
