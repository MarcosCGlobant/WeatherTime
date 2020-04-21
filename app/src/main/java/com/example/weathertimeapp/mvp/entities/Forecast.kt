package com.example.weathertimeapp.mvp.entities

data class Forecast(
    val list: List<Day> = listOf(),
    val city: City = City()
)