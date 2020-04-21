package com.example.weathertimeapp.mvp.entities

import com.example.weathertimeapp.utils.DEFAULT_STRING

data class Day(
    val temperature: Temperature = Temperature(),
    val weather: ArrayList<Weather>,
    val date: String = DEFAULT_STRING
)