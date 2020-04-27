package com.example.weathertimeapp.mvp.presenter

import com.example.weathertimeapp.mvp.contracts.WeatherTimeContracts
import com.example.weathertimeapp.utils.EMPTY_STRING
import com.example.weathertimeapp.utils.UNKNOWN
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherTimePresenter(
    private val model: WeatherTimeContracts.Model,
    private val view: WeatherTimeContracts.View
) : WeatherTimeContracts.Presenter {

    override fun initPresenter() {
        createAndSetCitiesList()
        setSoftInputMode()
    }

    private fun createAndSetCitiesList() {

        view.setCitiesListAdapter(model.createListOfCities())
    }

    private fun setSoftInputMode() {
        view.setSoftInputMode()
    }

    override fun onSearchButtonPressed(cityName: String) {
        if (cityName != EMPTY_STRING) {
            view.showLoading()
            model.getCityId(cityName)?.let { requestCityForecast(it) }
        } else {
            view.showInsertCityNameError()
        }
    }

    private fun requestCityForecast(id: Int) {
        if (id == UNKNOWN) {
            view.hideLoading()
            view.showToastNoItemToShowError()
        } else {
            var subscribe = model.getForecastByCityId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ forecast ->
                    view.showForecastRecyclerView(forecast)
                    view.hideLoading()
                }, {
                    view.hideLoading()
                    view.showToastNetworkError()
                })
        }
    }
}