package com.example.weathertimeapp.mvp.contracts

import com.example.weathertimeapp.mvp.entities.City
import com.example.weathertimeapp.mvp.entities.Weather

interface WeatherTimeContracts {
    interface Model {
        fun createListOfCities(listOfCities: ArrayList<City>): MutableList<String>
        fun createDataSet(): ArrayList<Weather>
    }

    interface View {
        fun setSoftInputMode()
        fun setCitiesListAdapter(cities: MutableList<String>)
        fun showRecyclerView()
        fun showDataSet(data: ArrayList<Weather>)
    }

    interface Presenter {
        fun initPresenter()
        fun setSoftInputMode()
        fun createAndSetCitiesList()
        fun initRecyclerView()
        fun createAndShowData()
    }
}