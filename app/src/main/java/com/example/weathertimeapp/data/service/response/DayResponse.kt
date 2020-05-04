package com.example.weathertimeapp.data.service.response

import com.example.weathertimeapp.util.DEFAULT_STRING

data class DayResponse(
    val main: MainResponse,
    val weather: List<WeatherResponse> = listOf(),
    val wind: WindResponse,
    val dt_txt: String = DEFAULT_STRING
)