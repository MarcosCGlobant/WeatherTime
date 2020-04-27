package com.example.weathertimeapp.data.entities

import com.example.weathertimeapp.utils.DEFAULT_STRING

data class Day(
    val temperature: Temperature = Temperature(),
    val weather: Weather,
    val date: String = DEFAULT_STRING
)