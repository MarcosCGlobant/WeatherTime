package com.example.weathertimeapp.mvp.entities

data class Weather(
    var date: String,
    var maxTemp: String,
    var minTemp: String,
    var image: String,
    var description: String
) {

    override fun toString(): String {
        return "Weather(date='$date', image='$image', maxTemp='$maxTemp', minTemp='$minTemp, description='$description')"
    }
}