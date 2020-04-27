package com.example.weathertimeapp.mvp.contracts

import com.example.weathertimeapp.data.entities.Forecast
import io.reactivex.Observable

interface WeatherTimeContracts {
    interface Model {
        fun createListOfCities(): MutableList<String>
        fun getForecastByCityId(id: Int): Observable<Forecast>
    }

    interface View {
        fun setSoftInputMode()
        fun setCitiesListAdapter(cities: MutableList<String>)
        fun showForecastRecyclerView(forecast: Forecast)
    }

    interface Presenter {
        fun initPresenter()
    }
}