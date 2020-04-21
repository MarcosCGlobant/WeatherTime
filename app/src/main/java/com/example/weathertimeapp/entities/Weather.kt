package com.example.weathertimeapp.entities

import com.example.weathertimeapp.utils.DEFAULT_ID
import com.example.weathertimeapp.utils.DEFAULT_STRING

data class Weather(
    val id: Int = DEFAULT_ID,
    val state: String = DEFAULT_STRING,
    val description: String = DEFAULT_STRING,
    val icon: String = DEFAULT_STRING
)