package com.example.weathertimeapp.entities

data class Forecast(
    val list: List<Day> = listOf(),
    val city: City = City()
)