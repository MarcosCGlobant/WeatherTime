package com.example.weathertimeapp.data.entity

import com.example.weathertimeapp.util.DEFAULT_STRING

data class Day(
    val temperature: Temperature = Temperature(),
    val weather: Weather,
    val wind: Wind,
    val date: String = DEFAULT_STRING
)