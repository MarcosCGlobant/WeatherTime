package com.example.weathertimeapp.data.mapper

import com.example.weathertimeapp.data.entities.City
import com.example.weathertimeapp.data.entities.Day
import com.example.weathertimeapp.data.entities.Forecast
import com.example.weathertimeapp.data.entities.Temperature
import com.example.weathertimeapp.data.entities.Weather
import com.example.weathertimeapp.data.services.response.CityResponse
import com.example.weathertimeapp.data.services.response.DayResponse
import com.example.weathertimeapp.data.services.response.ForecastResponse
import com.example.weathertimeapp.data.services.response.MainResponse
import com.example.weathertimeapp.data.services.response.WeatherResponse

class ForecastMapperService : BaseMapper<ForecastResponse, Forecast> {

    override fun transform(type: ForecastResponse): Forecast {
        type.apply {
            return Forecast(
                city = transformCity(city),
                days = transformToDaysList(list)
            )
        }
    }

    private fun transformToDaysList(list: List<DayResponse>): List<Day> {
        return list.map()
        {
            transformDay(it)
        }
    }

    private fun transformDay(type: DayResponse): Day {
        lateinit var day: Day
        type.apply {
            day = Day(
                temperature = transformTemperature(main),
                weather = transformWeather(weather),
                date = dt_txt
            )
        }
        return day
    }

    private fun transformCity(type: CityResponse): City {
        type.apply {
            return City(
                id = id,
                name = name,
                country = COUNTRY_AR
            )
        }
    }

    private fun transformTemperature(type: MainResponse): Temperature {
        type.apply {
            return Temperature(
                temp_min = temp_max,
                temp_max = temp_min
            )
        }
    }

    private fun transformWeather(type: List<WeatherResponse>): Weather {
        lateinit var weather: Weather
        type[ZERO].apply {
            weather = Weather(
                id = id,
                state = main,
                description = description,
                icon = icon
            )
        }
        return weather
    }

    companion object {
        private const val ZERO = 0
        private const val COUNTRY_AR = "AR"
    }
}
