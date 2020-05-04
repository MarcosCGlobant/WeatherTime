package com.example.weathertimeapp.data.services.response

import com.example.weathertimeapp.utils.DEFAULT_INT
import com.example.weathertimeapp.utils.DEFAULT_TEMP

data class MainResponse(
    val feels_like: Double = DEFAULT_TEMP,
    val temp_min: Double = DEFAULT_TEMP,
    val temp_max: Double = DEFAULT_TEMP,
    val pressure: Int = DEFAULT_INT,
    val humidity: Int = DEFAULT_INT
)