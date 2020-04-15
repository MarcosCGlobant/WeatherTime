package com.example.weathertimeapp.mvp.presenter

import com.example.weathertimeapp.mvp.contracts.WeatherTimeContracts
import com.example.weathertimeapp.mvp.entities.City

class WeatherTimePresenter(
    private val model: WeatherTimeContracts.Model,
    private val view: WeatherTimeContracts.View
) : WeatherTimeContracts.Presenter {

    override fun createAndSetCitiesList() {
        view.setCitiesListAdapter(model.createListOfCities(cities))
    }

    companion object {
        private var cities = ArrayList<City>()
    }
}