package com.example.weathertimeapp.data.mapper

import com.example.weathertimeapp.data.entities.Day
import com.example.weathertimeapp.data.entities.Temperature
import com.example.weathertimeapp.data.entities.Weather
import com.example.weathertimeapp.data.entities.Wind
import com.example.weathertimeapp.data.services.response.DayResponse
import com.example.weathertimeapp.data.services.response.MainResponse
import com.example.weathertimeapp.data.services.response.WeatherResponse
import com.example.weathertimeapp.data.services.response.WindResponse

class ExtendedWeatherMapperService : BaseMapper<DayResponse, Day> {

    override fun transform(type: DayResponse): Day {
        lateinit var day: Day
        type.apply {
            day = Day(
                temperature = transformTemperature(main),
                weather = transformWeather(weather),
                wind = transformWind(wind),
                date = dt_txt
            )
        }
        return day
    }

    private fun transformTemperature(type: MainResponse): Temperature {
        type.apply {
            return Temperature(
                temp_feels_like = feels_like,
                temp_min = temp_max,
                temp_max = temp_min,
                pressure = pressure,
                humidity = humidity
            )
        }
    }

    private fun transformWind(type: WindResponse): Wind {
        type.apply {
            return Wind(
                speed = speed
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
    }
}