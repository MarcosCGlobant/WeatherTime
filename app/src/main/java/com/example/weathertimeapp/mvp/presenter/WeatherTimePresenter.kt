package com.example.weathertimeapp.mvp.presenter

import com.example.weathertimeapp.mvp.contracts.WeatherTimeContracts
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
        view.setCitiesListAdapter(model.createListOfCities())
    }

    private fun setSoftInputMode() {
        view.setSoftInputMode()
    }

    private fun requestCityForecast() {
        var subscribe = model.getForecastByCityId(CITY_ID) //TODO on a future feature: CITY_ID represents the city,
            .subscribeOn(Schedulers.io())                  //that the user inserts to search the forecast
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { forecast ->
                view.showForecastRecyclerView(forecast)
            }
    }

    companion object {
        private const val CITY_ID = 3429439
    }
}