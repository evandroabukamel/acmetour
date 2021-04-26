package com.acme.tour.repository

import com.acme.tour.model.Promotion
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface PromotionRepository : PagingAndSortingRepository<Promotion, Long> {

    @Query(value = "SELECT p FROM Promotion p WHERE p.price < :price", nativeQuery = false)
    fun findPromotionsCheaperThan(@Param("price") price: Double): List<Promotion>

    @Query(value = "select p from Promotion p where p.local IN :names")
    fun findPromotionsByLocals(
        @Param("names") locals: List<String>
    ): List<Promotion>

    @Query(value = "update Promotion p set p.price = :price where p.local = :local")
    @Transactional
    @Modifying
    fun updatePriceByLocal(
        @Param("price") price: Double,
        @Param("local") local: String,
    )
}
