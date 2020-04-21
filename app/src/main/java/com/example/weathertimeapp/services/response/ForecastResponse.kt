package com.example.weathertimeapp.services.response

data class ForecastResponse(
    val list: List<DayResponse> = listOf(),
    val city: CityResponse
)