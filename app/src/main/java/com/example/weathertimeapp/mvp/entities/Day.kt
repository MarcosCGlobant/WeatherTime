package com.example.weathertimeapp.mvp.entities

import com.example.weathertimeapp.utils.DEFAULT_STRING

data class Day(
    val main: Main = Main(),
    val weather: ArrayList<Weather>,
    val dt_txt: String = DEFAULT_STRING
)