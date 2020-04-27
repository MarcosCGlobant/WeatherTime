package com.example.weathertimeapp.data.services.response

import com.example.weathertimeapp.utils.DEFAULT_ID
import com.example.weathertimeapp.utils.DEFAULT_STRING

data class WeatherResponse(
    val id: Int = DEFAULT_ID,
    val main: String = DEFAULT_STRING,
    val description: String = DEFAULT_STRING,
    val icon: String = DEFAULT_STRING
)