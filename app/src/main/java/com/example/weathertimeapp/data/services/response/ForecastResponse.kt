package com.example.weathertimeapp.data.services.response

data class ForecastResponse(
    val list: List<DayResponse> = listOf(),
    val city: CityResponse
)