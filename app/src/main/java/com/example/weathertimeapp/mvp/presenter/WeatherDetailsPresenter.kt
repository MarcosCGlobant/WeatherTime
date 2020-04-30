package com.example.weathertimeapp.mvp.presenter

import com.example.weathertimeapp.mvp.contracts.WeatherDetailsContract

class WeatherDetailsPresenter(
    private val model: WeatherDetailsContract.Model,
    private val view: WeatherDetailsContract.View,
    cityId: Int?,
    date: String?
) : WeatherDetailsContract.Presenter {
}