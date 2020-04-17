package com.example.weathertimeapp.mvp.presenter

import com.example.weathertimeapp.mvp.contracts.WeatherTimeContracts
import com.example.weathertimeapp.mvp.entities.City

class WeatherTimePresenter(
    private val model: WeatherTimeContracts.Model,
    private val view: WeatherTimeContracts.View
) : WeatherTimeContracts.Presenter {

    override fun initPresenter() {
        createAndSetCitiesList()
        setSoftInputMode()
        initRecyclerView()
        createAndShowData()
    }

    private fun createAndSetCitiesList() {
        view.setCitiesListAdapter(model.createListOfCities(cities))
    }

    private fun setSoftInputMode() {
        view.setSoftInputMode()
    }

    private fun initRecyclerView() {
        view.showRecyclerView()
    }

    private fun createAndShowData() {
        view.showDataSet(model.createDataSet())
    }

    companion object {
        private var cities = ArrayList<City>()
    }
}