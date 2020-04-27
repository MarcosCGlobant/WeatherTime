package com.example.weathertimeapp.data.entities

import com.example.weathertimeapp.utils.DEFAULT_TEMP

data class Temperature(
    val temp_min: Double = DEFAULT_TEMP,
    val temp_max: Double = DEFAULT_TEMP
)