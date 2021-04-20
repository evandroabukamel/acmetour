package com.acme.tour.service

import com.acme.tour.model.Promotion

interface PromotionService {

    fun getAll(localFilter: String = ""): List<Promotion>

    fun getById(id: Long): Promotion?

    fun create(promotion: Promotion)

    fun update(id: Long, promotion: Promotion)

    fun delete(id: Long)
}
