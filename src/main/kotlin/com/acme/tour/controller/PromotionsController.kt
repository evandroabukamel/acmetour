package com.acme.tour.controller

import com.acme.tour.exception.PromotionNotFoundException
import com.acme.tour.model.ErrorResponse
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
        @RequestParam(name = "local", required = false, defaultValue = "") localFilter: String,
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "100") size: Int,
        @RequestParam(required = false, defaultValue = "") sortBy: String,
        @RequestParam(required = false, defaultValue = "") sortDirection: String,
    ): ResponseEntity<List<Promotion>> {
        val promotions = if (localFilter.isEmpty())
            promotionService.getAll(page, size, sortBy, sortDirection)
        else
            promotionService.getByLocal(localFilter, page, size, sortBy, sortDirection)

        val statusCode = if (promotions.isEmpty()) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(promotions, statusCode)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Any> {
        val promotion = promotionService.getById(id)

        if (promotion != null) {
            return ResponseEntity(promotion, HttpStatus.OK)
        }

        return ResponseEntity(
            ErrorResponse(
                PromotionNotFoundException::class.simpleName.toString(),
                "Promotion(s) not found."
            ),
            HttpStatus.NOT_FOUND
        )
    }

    @PostMapping
    fun create(@RequestBody promotion: Promotion): ResponseEntity<ResponseJSON> {
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

    @GetMapping("/count")
    fun count(): ResponseEntity<Map<String, Any>> {
        val count = promotionService.count()
        return ResponseEntity.ok().body(mapOf<String, Any>(
            "count" to count
        ))
    }

    @GetMapping("/cheaper-than-1000")
    fun getPromotionsCheaperThan1000(): ResponseEntity<List<Promotion>> {
        val promotions = promotionService.getPromotionsCheaperThan1000()
        val statusCode = if (promotions.isEmpty()) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(promotions, statusCode)
    }

    @PutMapping("/update-price-by-local/{local}")
    fun updatePriceByLocal(
        @PathVariable local: String,
        @RequestBody payload: Map<String, Any>
    ): ResponseEntity<Unit> {
        promotionService.updatePriceByLocal(payload["price"] as Double, local)
        return ResponseEntity(Unit, HttpStatus.OK)
    }
}
