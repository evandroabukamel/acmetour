package com.acme.tour.controller

import com.acme.tour.model.Promotion
import com.acme.tour.model.ResponseJSON
import com.acme.tour.service.PromotionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/promotions"])
class PromotionsController {

    @Autowired
    lateinit var promotionService: PromotionService

    @GetMapping
    fun getAll(
        @RequestParam(name = "local", required = false, defaultValue = "")
        localFilter: String
    ): ResponseEntity<List<Promotion>> {
        val promocoes = promotionService.getAll(localFilter)
        val statusCode = if (promocoes.isEmpty()) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(promocoes, statusCode)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Promotion?> {
        val promotion = promotionService.getById(id)
        val statusCode = if (promotion != null) HttpStatus.OK else HttpStatus.NOT_FOUND
        return ResponseEntity(promotion, statusCode)
    }

    @PostMapping
    fun create(@RequestBody promotion: Promotion): ResponseEntity<ResponseJSON> {
        if (promotionService.getById(promotion.id) != null) {
            val body = ResponseJSON(
                message = "Promotion #${promotion.id} already exists."
            )
            return ResponseEntity(body, HttpStatus.CONFLICT)
        }

        promotionService.create(promotion)
        val body = ResponseJSON(
            message = "Promotion created."
        )
        return ResponseEntity(body, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody promotion: Promotion
    ) : ResponseEntity<Unit> {
        var statusCode = HttpStatus.NOT_FOUND
        if (promotionService.getById(id) != null) {
            promotionService.update(id, promotion)
            statusCode = HttpStatus.ACCEPTED
        }
        return ResponseEntity(Unit, statusCode)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Unit> {
        val promotion = promotionService.getById(id)
        val statusCode = if (promotion != null) HttpStatus.OK else HttpStatus.NOT_FOUND
        promotionService.delete(id)
        return ResponseEntity(Unit, statusCode)
    }
}
