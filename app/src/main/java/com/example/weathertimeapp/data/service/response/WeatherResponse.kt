package com.example.weathertimeapp.data.service.response

import com.example.weathertimeapp.util.DEFAULT_ID
import com.example.weathertimeapp.util.DEFAULT_STRING

data class WeatherResponse(
    val id: Int = DEFAULT_ID,
    val main: String = DEFAULT_STRING,
    val description: String = DEFAULT_STRING,
    val icon: String = DEFAULT_STRING
)