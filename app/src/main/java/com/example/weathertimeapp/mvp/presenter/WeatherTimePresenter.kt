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
    }

    private fun createAndSetCitiesList() {

        view.setCitiesListAdapter(model.createListOfCities())
    }

    private fun setSoftInputMode() {
        view.setSoftInputMode()
    }

    override fun onSearchButtonPressed(cityName: String) {
        view.showLoading()
        model.getCityId(cityName)?.let { requestCityForecast(it) }
    }

    private fun requestCityForecast(id: Int) {
        var subscribe = model.getForecastByCityId(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ forecast ->
                if (forecast == null) {
                    view.showToastNoItemToShow()
                } else {
                    view.showForecastRecyclerView(forecast)
                }
                view.hideLoading()

            }, { e ->
                view.hideLoading()
                view.showToastNetworkError(e.message.toString())
            })
    }
}
