package com.example.weathertimeapp.mvp.model

import com.example.weathertimeapp.entities.City
import com.example.weathertimeapp.mvp.contracts.WeatherTimeContracts
import com.example.weathertimeapp.services.ForecastRequestGenerator
import com.example.weathertimeapp.services.api.WeatherTimeApi
import com.example.weathertimeapp.services.response.ForecastResponse
import com.example.weathertimeapp.utils.COUNTRY
import com.example.weathertimeapp.utils.COUNTRY_AR
import com.example.weathertimeapp.utils.ID
import com.example.weathertimeapp.utils.NAME
import io.reactivex.Observable
import org.json.JSONArray

class WeatherTimeModel(private val citiesJson: String) : WeatherTimeContracts.Model {

    private val api: ForecastRequestGenerator = ForecastRequestGenerator()

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

    override fun getForecastByCityId(id: Int): Observable<ForecastResponse> {
        return Observable.create { subscriber ->
            val queryHashMap = LinkedHashMap<String, String>()
            queryHashMap[ID_CITY] = id.toString()
            queryHashMap[APPID] = ForecastRequestGenerator.API_KEY
            val callResponse = api.createService(WeatherTimeApi::class.java).getCityById(queryHashMap)
            val response = callResponse.execute()
            if (response.isSuccessful) {
                response.body()?.city?.id?.let { subscriber.onComplete() }
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }

    companion object {
        private const val ID_CITY = "id"
        private const val APPID = "APPID"
    }
}