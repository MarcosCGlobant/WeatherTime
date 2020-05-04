package com.example.weathertimeapp.data.mapper

import com.example.weathertimeapp.data.entity.City
import com.example.weathertimeapp.data.entity.Day
import com.example.weathertimeapp.data.entity.Forecast
import com.example.weathertimeapp.data.entity.Temperature
import com.example.weathertimeapp.data.entity.Weather
import com.example.weathertimeapp.data.entity.Wind
import com.example.weathertimeapp.data.service.response.CityResponse
import com.example.weathertimeapp.data.service.response.DayResponse
import com.example.weathertimeapp.data.service.response.ForecastResponse
import com.example.weathertimeapp.data.service.response.MainResponse
import com.example.weathertimeapp.data.service.response.WeatherResponse
import com.example.weathertimeapp.data.service.response.WindResponse

class ForecastMapperService : BaseMapper<ForecastResponse, Forecast> {

    override fun transform(type: ForecastResponse): Forecast = Forecast(
        transformToDaysList(type.list),
        transformCity(type.city)
    )

    private fun transformToDaysList(list: List<DayResponse>): List<Day> = list.map() { transformDay(it) }

    private fun transformDay(type: DayResponse): Day = Day(
        transformTemperature(type.main),
        transformWeather(type.weather),
        transformWind(type.wind),
        type.dt_txt
    )

    private fun transformCity(type: CityResponse): City = City(
        id = type.id,
        name = type.name,
        country = COUNTRY_AR
    )

    private fun transformTemperature(type: MainResponse): Temperature = Temperature(
        temp_min = type.temp_max,
        temp_max = type.temp_min

    )

    private fun transformWeather(type: List<WeatherResponse>): Weather = Weather(
        id = type[ZERO].id,
        state = type[ZERO].main,
        description = type[ZERO].description,
        icon = type[ZERO].icon
    )

    private fun transformWind(type: WindResponse): Wind = Wind(
        speed = type.speed
    )

    companion object {
        private const val ZERO = 0
        private const val COUNTRY_AR = "AR"
    }
}