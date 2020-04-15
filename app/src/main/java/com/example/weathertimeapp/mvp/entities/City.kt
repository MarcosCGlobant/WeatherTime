package com.example.weathertimeapp.mvp.entities

data class City(
    val id: Int = DEFAULT_ID,
    val name: String = NOT_FOUND,
    val country: String = NOT_FOUND,
    val main: String = NOT_FOUND
) {
    companion object {
        const val NOT_FOUND = "NOT FOUND"
        const val DEFAULT_ID = 0
    }
}