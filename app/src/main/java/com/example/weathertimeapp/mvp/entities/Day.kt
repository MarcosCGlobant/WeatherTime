package com.example.weathertimeapp.mvp.entities

import com.example.weathertimeapp.utils.DEFAULT_STRING

data class Day(
    val temperature: Temperature = Temperature(),
    val weather: List<Weather> = listOf(),
    val date: String = DEFAULT_STRING
)