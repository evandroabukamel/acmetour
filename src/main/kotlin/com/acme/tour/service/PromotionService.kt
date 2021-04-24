package com.acme.tour.service

import com.acme.tour.model.Promotion

interface PromotionService {

    fun getAll(page: Int, size: Int): List<Promotion>

    fun getByLocal(localFilter: String = "", start: Int, size: Int): List<Promotion>

    fun getById(id: Long): Promotion?

    fun create(promotion: Promotion)

    fun update(id: Long, promotion: Promotion)

    fun delete(id: Long)

    fun count(): Long
}
