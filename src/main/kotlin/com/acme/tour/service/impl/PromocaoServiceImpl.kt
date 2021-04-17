package com.acme.tour.service.impl

import com.acme.tour.model.Promocao
import com.acme.tour.service.PromocaoService
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class PromocaoServiceImpl : PromocaoService {

    companion object {
        val initialPromocoes = arrayOf(
            Promocao(1, "Maravilhosa viagem para Cancun", 4299.00, "Cancun", true, 7),
            Promocao(2, "Tour em Hobbiton", 299.00, "New Zealand", true, 1),
            Promocao(3, "Viagem para Wellington", 69.00, "New Zealand", false, 2),
            Promocao(4, "Glacier Trekking in Franz-Josef", 349.00, "New Zealand", false, 3),
        )
    }

    val promocoes = ConcurrentHashMap(initialPromocoes.associateBy(Promocao::id))

    override fun getAll(localFilter: String): List<Promocao> {
        return promocoes.filter {
            it.value.local.contains(localFilter, true)
        }.map(Map.Entry<Long, Promocao>::value).toList()
    }

    override fun getById(id: Long): Promocao? {
        return promocoes[id]
    }

    override fun create(promocao: Promocao) {
        promocoes[promocao.id] = promocao
    }

    override fun update(id: Long, promocao: Promocao) {
        delete(id)
        create(promocao)
    }

    override fun delete(id: Long) {
        promocoes.remove(id)
    }
}
