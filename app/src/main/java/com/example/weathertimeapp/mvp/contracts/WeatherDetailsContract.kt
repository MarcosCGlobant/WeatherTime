package com.example.weathertimeapp.mvp.contracts

import com.example.weathertimeapp.data.entities.Day
import io.reactivex.Observable

interface WeatherDetailsContract {
    interface Model {
        fun getExtendedWeather(cityData: Pair<Int?, Int?>): Observable<Day>
    }

    interface View {
        fun showExtendedWeather(day: Day)
        fun showLoading()
        fun hideLoading()
        fun showToastNetworkError()
    }

    interface Presenter {
        fun requestAndShowExtendedWeather()
    }
}