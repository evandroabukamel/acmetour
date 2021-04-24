package com.acme.tour.service.impl

import com.acme.tour.model.Promotion
import com.acme.tour.repository.PromotionRepository
import com.acme.tour.service.PromotionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
class PromotionServiceImpl : PromotionService {

    @Autowired
    lateinit var promotionRepository: PromotionRepository

    override fun getAll(page: Int, size: Int): List<Promotion> {
        val pageble = PageRequest.of(page, size)
        return promotionRepository.findAll(pageble).toList()
    }

    override fun getByLocal(
        localFilter: String,
        start: Int,
        size: Int,
    ): List<Promotion> {
        val pageble = PageRequest.of(start, size)
        return promotionRepository
            .findAll(pageble)
            .toList()
            .filter {
                it.local.contains(localFilter, true)
            }.toList()
    }

    override fun getById(id: Long): Promotion? {
        return promotionRepository.findById(id).orElseGet(null)
    }

    override fun create(promotion: Promotion) {
        this.promotionRepository.save(promotion)
    }

    override fun update(id: Long, promotion: Promotion) {
        create(promotion)
    }

    override fun delete(id: Long) {
        this.promotionRepository.deleteById(id)
    }

    override fun count(): Long {
        return this.promotionRepository.count()
    }
}
