package com.example.weathertimeapp.data.service.response

import com.example.weathertimeapp.util.DEFAULT_ID
import com.example.weathertimeapp.util.DEFAULT_STRING

data class CityResponse(
    val id: Int = DEFAULT_ID,
    val name: String = DEFAULT_STRING,
    val country: String = DEFAULT_STRING
)