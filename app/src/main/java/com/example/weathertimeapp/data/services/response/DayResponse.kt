package com.example.weathertimeapp.data.services.response

import com.example.weathertimeapp.utils.DEFAULT_STRING

data class DayResponse(
    val main: MainResponse,
    val weather: List<WeatherResponse> = listOf(),
    val dt_txt: String = DEFAULT_STRING
)