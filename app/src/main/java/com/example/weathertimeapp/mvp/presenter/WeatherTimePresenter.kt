package com.example.weathertimeapp.mvp.presenter

import com.example.weathertimeapp.mvp.contracts.WeatherTimeContracts
import com.example.weathertimeapp.mvp.entities.City
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherTimePresenter(
    private val model: WeatherTimeContracts.Model,
    private val view: WeatherTimeContracts.View
) : WeatherTimeContracts.Presenter {

    override fun initPresenter() {
        createAndSetCitiesList()
        setSoftInputMode()
        requestCityForecast()
    }

    private fun createAndSetCitiesList() {
        view.setCitiesListAdapter(model.createListOfCities(cities))
    }

    private fun setSoftInputMode() {
        view.setSoftInputMode()
    }

    private fun requestCityForecast() {
        model.getForecastByCityId(3429439)
            .subscribeOn(Schedulers.io())
            .observeOn(
                AndroidSchedulers
                    .mainThread()
            )
            .subscribe({ forecast ->
                val request = forecast
            })
    }

    companion object {
        private var cities = ArrayList<City>()
    }
}