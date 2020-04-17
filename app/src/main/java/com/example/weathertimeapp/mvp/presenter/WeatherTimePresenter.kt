package com.example.weathertimeapp.mvp.presenter

import com.example.weathertimeapp.mvp.contracts.WeatherTimeContracts
import com.example.weathertimeapp.mvp.entities.City

class WeatherTimePresenter(
    private val model: WeatherTimeContracts.Model,
    private val view: WeatherTimeContracts.View
) : WeatherTimeContracts.Presenter {

    override fun initPresenter() {
        createAndSetCitiesList()
        setSlowInputMode()
        initRecyclerView()
        createAndShowData()
    }

    override fun createAndSetCitiesList() {
        view.setCitiesListAdapter(model.createListOfCities(cities))
    }

    override fun setSlowInputMode() {
        view.setSlowInputMode()
    }

    override fun initRecyclerView() {
        view.showRecyclerView()
    }

    override fun createAndShowData() {
        view.showDataSet(model.createDataSet())
    }

    companion object {
        private var cities = ArrayList<City>()
    }
}