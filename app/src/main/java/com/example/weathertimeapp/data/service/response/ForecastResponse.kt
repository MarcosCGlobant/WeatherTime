package com.example.weathertimeapp.data.service.response

data class ForecastResponse(
    val list: List<DayResponse> = listOf(),
    val city: CityResponse
)