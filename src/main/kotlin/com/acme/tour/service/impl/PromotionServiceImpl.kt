package com.acme.tour.service.impl

import com.acme.tour.model.Promotion
import com.acme.tour.service.PromotionService
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class PromotionServiceImpl : PromotionService {

    companion object {
        val initialPromotions = arrayOf(
            Promotion(1, "Maravilhosa viagem para Cancun", 4299.00, "Cancun", true, 7),
            Promotion(2, "Tour em Hobbiton", 299.00, "New Zealand", true, 1),
            Promotion(3, "Viagem para Wellington", 69.00, "New Zealand", false, 2),
            Promotion(4, "Glacier Trekking in Franz-Josef", 349.00, "New Zealand", false, 3),
        )
    }

    val promotions = ConcurrentHashMap(initialPromotions.associateBy(Promotion::id))

    override fun getAll(localFilter: String): List<Promotion> {
        return promotions.filter {
            it.value.local.contains(localFilter, true)
        }.map(Map.Entry<Long, Promotion>::value).toList()
    }

    override fun getById(id: Long): Promotion? {
        return promotions[id]
    }

    override fun create(promotion: Promotion) {
        promotions[promotion.id] = promotion
    }

    override fun update(id: Long, promotion: Promotion) {
        delete(id)
        create(promotion)
    }

    override fun delete(id: Long) {
        promotions.remove(id)
    }
}
