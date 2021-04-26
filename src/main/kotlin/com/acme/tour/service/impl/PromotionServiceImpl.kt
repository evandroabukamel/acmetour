package com.acme.tour.service.impl

import com.acme.tour.model.Promotion
import com.acme.tour.repository.PromotionRepository
import com.acme.tour.service.PromotionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component

@Component
class PromotionServiceImpl : PromotionService {

    @Autowired
    lateinit var promotionRepository: PromotionRepository

    private fun getPageRequest(
        page: Int,
        size: Int,
        sortBy: String?,
        sortDirection: String?
    ): PageRequest {
        if (!sortBy.isNullOrEmpty()) {
            val sort = Sort.by(
                if (sortDirection?.toUpperCase() == "DESC")
                    Sort.Direction.DESC
                else Sort.Direction.ASC,
                sortBy,
            )

            return PageRequest.of(page, size, sort)
        }

        return PageRequest.of(page, size)
    }

    override fun getAll(
        page: Int,
        size: Int,
        sortBy: String?,
        sortDirection: String?,
    ): List<Promotion> {
        val pageble = getPageRequest(page, size, sortBy, sortDirection)
        return promotionRepository.findAll(pageble).toList()
    }

    override fun getByLocal(
        localFilter: String,
        page: Int,
        size: Int,
        sortBy: String?,
        sortDirection: String?,
    ): List<Promotion> {
        val pageble = getPageRequest(page, size, sortBy, sortDirection)

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
