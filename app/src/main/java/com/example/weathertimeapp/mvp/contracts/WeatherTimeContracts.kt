package com.example.weathertimeapp.mvp.contracts

import com.example.weathertimeapp.entities.City
import com.example.weathertimeapp.services.response.ForecastResponse
import io.reactivex.Observable

interface WeatherTimeContracts {
    interface Model {
        fun createListOfCities(listOfCities: ArrayList<City>): MutableList<String>
        fun getForecastByCityId(id: Int): Observable<ForecastResponse>
    }

    interface View {
        fun setSoftInputMode()
        fun setCitiesListAdapter(cities: MutableList<String>)
        fun showRecyclerView()
    }

    interface Presenter {
        fun initPresenter()
    }
}