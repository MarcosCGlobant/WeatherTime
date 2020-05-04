package com.example.weathertimeapp.mvp.presenter

import com.example.weathertimeapp.mvp.contracts.WeatherDetailsContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherDetailsPresenter(
    private val model: WeatherDetailsContract.Model,
    private val view: WeatherDetailsContract.View,
    private val cityData: Pair<Int?, Int?>
) : WeatherDetailsContract.Presenter {

    override fun requestAndShowExtendedWeather() {
        view.showLoading()
        model.getExtendedWeather(cityData as Pair<Int, Int>)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ day ->
                view.showExtendedWeather(day)
                view.hideLoading()
            }, {
                view.hideLoading()
                view.showToastNetworkError()
            })
    }
}