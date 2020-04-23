package com.example.weathertimeapp.mvp.model

import android.content.res.AssetManager
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

class WeatherTimeModel(private val assetManager: AssetManager) : WeatherTimeContracts.Model {

    private val api: ForecastRequestGenerator = ForecastRequestGenerator()

    override fun createListOfCities(): MutableList<String> {
        val citiesArray = JSONArray(assetManager.open(FILE_NAME).bufferedReader().use { it.readText() })
        val listOfCities = mutableListOf<String>()
        for (i in 0 until citiesArray.length()) {
            val cityJsonObject = citiesArray.getJSONObject(i)
            if (cityJsonObject.get(COUNTRY) == COUNTRY_AR) {
                val city = City(
                    cityJsonObject.get(ID).toString().toInt(),
                    cityJsonObject.get(NAME).toString(),
                    cityJsonObject.get(COUNTRY).toString()
                )
                listOfCities.add(city.name)
            }
        }
        return listOfCities
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
        private const val FILE_NAME = "city_list.json"
    }
}