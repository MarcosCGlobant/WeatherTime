package com.example.weathertimeapp.services.response

data class ForecastResponse(
    val list: ArrayList<DayResponse>,
    val city: CityResponse
)