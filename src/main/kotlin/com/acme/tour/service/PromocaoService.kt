package com.acme.tour.service

import com.acme.tour.model.Promocao

interface PromocaoService {

    fun getAll(localFilter: String = ""): List<Promocao>

    fun getById(id: Long): Promocao?

    fun create(promocao: Promocao)

    fun update(id: Long, promocao: Promocao)

    fun delete(id: Long)
}
