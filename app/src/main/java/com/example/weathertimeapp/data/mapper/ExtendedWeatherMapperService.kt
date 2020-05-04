package com.example.weathertimeapp.data.mapper

import com.example.weathertimeapp.data.entity.Day
import com.example.weathertimeapp.data.entity.Temperature
import com.example.weathertimeapp.data.entity.Weather
import com.example.weathertimeapp.data.entity.Wind
import com.example.weathertimeapp.data.service.response.DayResponse
import com.example.weathertimeapp.data.service.response.MainResponse
import com.example.weathertimeapp.data.service.response.WeatherResponse
import com.example.weathertimeapp.data.service.response.WindResponse

class ExtendedWeatherMapperService : BaseMapper<DayResponse, Day> {

    override fun transform(type: DayResponse): Day = Day(
        transformTemperature(type.main),
        transformWeather(type.weather),
        transformWind(type.wind),
        type.dt_txt
    )

    private fun transformTemperature(type: MainResponse): Temperature = Temperature(
        temp_feels_like = type.feels_like,
        temp_min = type.temp_max,
        temp_max = type.temp_min,
        pressure = type.pressure,
        humidity = type.humidity
    )

    private fun transformWind(type: WindResponse): Wind = Wind(
        speed = type.speed
    )

    private fun transformWeather(type: List<WeatherResponse>): Weather = Weather(
        id = type[ZERO].id,
        state = type[ZERO].main,
        description = type[ZERO].description,
        icon = type[ZERO].icon
    )

    companion object {
        private const val ZERO = 0
    }
}