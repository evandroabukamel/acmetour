package com.acme.tour.model

class SimpleObject(
    val name: String = "Hello",
    private val zone: String = "World"
) {

    fun getPlace() = zone
}
