package com.example.weathertimeapp.mvp.model

import com.example.weathertimeapp.mvp.contracts.WeatherTimeContracts
import com.example.weathertimeapp.mvp.entities.City
import com.example.weathertimeapp.mvp.utils.COUNTRY
import com.example.weathertimeapp.mvp.utils.COUNTRY_AR
import com.example.weathertimeapp.mvp.utils.ID
import com.example.weathertimeapp.mvp.utils.NAME
import org.json.JSONArray

class WeatherTimeModel(private val citiesJson: String) : WeatherTimeContracts.Model {

    override fun createListOfCities(listOfCities: ArrayList<City>): MutableList<String> {
        val jsonArray = JSONArray(citiesJson)
        val stringList = mutableListOf<String>()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            if (jsonObject.get(COUNTRY) == COUNTRY_AR) {
                val city = City(
                    jsonObject.get(ID).toString().toInt(),
                    jsonObject.get(NAME).toString(),
                    jsonObject.get(COUNTRY).toString()
                )
                stringList.add(city.name)
                listOfCities.add(city)
            }
        }
        return stringList
    }
}