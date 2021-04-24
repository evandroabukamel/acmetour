package com.acme.tour.repository

import com.acme.tour.model.Promotion
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface PromotionRepository : PagingAndSortingRepository<Promotion, Long>
