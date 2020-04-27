package com.example.weathertimeapp.data.entities

import com.example.weathertimeapp.utils.DEFAULT_ID
import com.example.weathertimeapp.utils.DEFAULT_STRING

data class City(
    val id: Int = DEFAULT_ID,
    val name: String = DEFAULT_STRING,
    val country: String = DEFAULT_STRING
)