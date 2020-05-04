package com.example.weathertimeapp.mvp.presenter

import com.example.weathertimeapp.mvp.contract.WeatherTimeContract
import com.example.weathertimeapp.util.UNKNOWN
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherTimePresenter(
    private val model: WeatherTimeContract.Model,
    private val view: WeatherTimeContract.View
) : WeatherTimeContract.Presenter {

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
        if (cityName.isNotEmpty()) {
            view.showLoading()
            model.getCityId(cityName)?.let { requestCityForecast(it) }
        } else {
            view.showInsertCityNameError()
            view.clearViewOnError()
        }
    }

    private fun requestCityForecast(id: Int) {
        if (id == UNKNOWN) {
            view.hideLoading()
            view.showToastNoItemToShowError()
            view.clearViewOnError()
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
                    view.clearViewOnError()
                })
        }
    }
}