package com.example.weathertimeapp.adapters

interface OnWeatherListener {
    fun onWeatherClick(cityId: Int, date: String)
}