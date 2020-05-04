package com.example.weathertimeapp.data.entity

data class Forecast(
    val days: List<Day> = listOf(),
    val city: City = City()
)