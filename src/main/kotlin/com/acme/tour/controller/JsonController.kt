package com.acme.tour.controller

import com.acme.tour.model.Client
import com.acme.tour.model.Phone
import com.acme.tour.model.SimpleObject
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping(value = ["/json"])
class JsonController {

    @GetMapping
    fun getJson() = SimpleObject()

    @GetMapping("/client")
    fun getClient() = Client(
        id = 1,
        name = "Evandro",
        birthdate = LocalDateTime.of(1989, 1, 30, 0, 0),
        phone = Phone(ddd = "31", number = "33998877", type = "mobile")
    )
}
