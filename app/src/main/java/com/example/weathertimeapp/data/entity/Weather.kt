package com.example.weathertimeapp.data.entity

import com.example.weathertimeapp.util.DEFAULT_ID
import com.example.weathertimeapp.util.DEFAULT_STRING

data class Weather(
    val id: Int = DEFAULT_ID,
    val state: String = DEFAULT_STRING,
    val description: String = DEFAULT_STRING,
    val icon: String = DEFAULT_STRING
)