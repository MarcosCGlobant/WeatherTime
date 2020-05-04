package com.example.weathertimeapp.adapter

interface OnWeatherListener {
    fun onWeatherClick(cityId: Int, position: Int)
}