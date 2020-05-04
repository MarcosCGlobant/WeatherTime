package com.example.weathertimeapp.data.entities

import com.example.weathertimeapp.utils.DEFAULT_INT
import com.example.weathertimeapp.utils.DEFAULT_TEMP

data class Temperature(
    val temp_feels_like: Double = DEFAULT_TEMP,
    val temp_min: Double = DEFAULT_TEMP,
    val temp_max: Double = DEFAULT_TEMP,
    val pressure: Int = DEFAULT_INT,
    val humidity: Int = DEFAULT_INT
)