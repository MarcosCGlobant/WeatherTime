package com.example.weathertimeapp.data.services.response

import com.example.weathertimeapp.utils.DEFAULT_ID
import com.example.weathertimeapp.utils.DEFAULT_STRING

data class CityResponse(
    val id: Int = DEFAULT_ID,
    val name: String = DEFAULT_STRING,
    val country: String = DEFAULT_STRING
)