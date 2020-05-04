package com.example.weathertimeapp.mvp.contract

import com.example.weathertimeapp.data.entity.Day
import io.reactivex.Observable

interface WeatherDetailsContract {
    interface Model {
        fun getExtendedWeather(cityData: Pair<Int, Int>): Observable<Day>
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