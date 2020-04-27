package com.example.weathertimeapp.data.entities

data class Forecast(
    val days: List<Day> = listOf(),
    val city: City = City()
)