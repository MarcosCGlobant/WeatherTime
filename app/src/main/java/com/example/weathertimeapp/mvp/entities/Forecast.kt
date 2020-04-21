package com.example.weathertimeapp.mvp.entities

data class Forecast(
    val list: ArrayList<Day> = arrayListOf(),
    val city: City = City()
)