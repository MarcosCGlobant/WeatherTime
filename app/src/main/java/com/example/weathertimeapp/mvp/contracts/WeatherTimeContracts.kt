package com.example.weathertimeapp.mvp.contracts

import com.example.weathertimeapp.data.entities.Forecast
import io.reactivex.Observable

interface WeatherTimeContracts {
    interface Model {
        fun createListOfCities(): MutableList<String>
        fun getCityId(cityName: String): Int?
        fun getForecastByCityId(id: Int): Observable<Forecast>
    }

    interface View {
        fun setSoftInputMode()
        fun setCitiesListAdapter(cities: MutableList<String>)
        fun showForecastRecyclerView(forecast: Forecast)
        fun hideLoading()
        fun showLoading()
        fun showToastNetworkError()
        fun showToastNoItemToShowError()
        fun showInsertCityNameError()
        fun clearViewOnError()
    }

    interface Presenter {
        fun initPresenter()
        fun onSearchButtonPressed(cityName: String)
    }
}