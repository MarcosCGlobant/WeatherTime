package com.example.weathertimeapp.services.response

import com.example.weathertimeapp.utils.DEFAULT_TEMP

data class MainResponse(
    val temp_min: Double = DEFAULT_TEMP,
    val temp_max: Double = DEFAULT_TEMP
)