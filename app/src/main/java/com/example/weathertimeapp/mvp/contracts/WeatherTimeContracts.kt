package com.example.weathertimeapp.mvp.contracts

import com.example.weathertimeapp.mvp.entities.City

interface WeatherTimeContracts {
    interface Model {
        fun createListOfCities(listOfCities: MutableList<City>): MutableList<String>
    }

    interface View {
        fun setCitiesListAdapter(cities: MutableList<String>)
    }

    interface Presenter {
        fun createAndSetCitiesList()
    }
}