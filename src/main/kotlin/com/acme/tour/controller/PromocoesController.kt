package com.acme.tour.controller

import com.acme.tour.model.Promocao
import com.acme.tour.service.PromocaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/promocoes"])
class PromocoesController {

    @Autowired
    lateinit var promocaoService: PromocaoService

    @GetMapping
    fun getAll(
        @RequestParam(name = "local", required = false, defaultValue = "")
        localFilter: String
    ): List<Promocao> {
        return promocaoService.getAll(localFilter)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Promocao? {
        return promocaoService.getById(id)
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody promocao: Promocao
    ) {
        promocaoService.update(id, promocao)
    }

    @PostMapping
    fun create(@RequestBody promocao: Promocao) {
        promocaoService.create(promocao)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long) {
        promocaoService.delete(id)
    }
}
