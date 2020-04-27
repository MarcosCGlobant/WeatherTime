package com.example.weathertimeapp.mvp.model

import android.content.res.AssetManager
import com.example.weathertimeapp.data.entities.City
import com.example.weathertimeapp.data.entities.Forecast
import com.example.weathertimeapp.data.mapper.ForecastMapperService
import com.example.weathertimeapp.data.services.ForecastRequestGenerator
import com.example.weathertimeapp.data.services.api.WeatherTimeApi
import com.example.weathertimeapp.mvp.contracts.WeatherTimeContracts
import com.example.weathertimeapp.utils.COUNTRY
import com.example.weathertimeapp.utils.COUNTRY_AR
import com.example.weathertimeapp.utils.ID
import com.example.weathertimeapp.utils.NAME
import com.example.weathertimeapp.utils.UNIT
import io.reactivex.Observable
import org.json.JSONArray

class WeatherTimeModel(private val assetManager: AssetManager) : WeatherTimeContracts.Model {

    private val api: ForecastRequestGenerator = ForecastRequestGenerator()
    private val mapper: ForecastMapperService = ForecastMapperService()
    private val mapOfCities = mutableMapOf<String, String>()

    override fun createListOfCities(): MutableList<String> {
        val listOfCities = mutableListOf<String>()
        val citiesArray = JSONArray(assetManager.open(FILE_NAME).bufferedReader().use { it.readText() })
        for (i in 0 until citiesArray.length()) {
            val cityJsonObject = citiesArray.getJSONObject(i)
            if (cityJsonObject.get(COUNTRY) == COUNTRY_AR) {
                val city = City(
                    cityJsonObject.get(ID).toString().toInt(),
                    cityJsonObject.get(NAME).toString(),
                    cityJsonObject.get(COUNTRY).toString()
                )
                mapOfCities[city.name] = city.id.toString()
                listOfCities.add(city.name)
            }
        }
        return listOfCities
    }

    override fun getCityId(cityName: String): Int? {
        return mapOfCities[cityName]?.toInt()
    }

    override fun getForecastByCityId(id: Int): Observable<Forecast> {
        return Observable.create { subscriber ->
            val queryHashMap = LinkedHashMap<String, String>()
            queryHashMap[ID_CITY] = id.toString()
            queryHashMap[APPID] = ForecastRequestGenerator.API_KEY
            queryHashMap[UNITS] = UNIT
            val callResponse = api.createService(WeatherTimeApi::class.java).getCityById(queryHashMap)
            val response = callResponse.execute()
            if (response.isSuccessful) {
                subscriber.onNext(mapper.transform(response.body()!!))
                subscriber.onComplete()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }

    companion object {
        private const val ID_CITY = "id"
        private const val APPID = "APPID"
        private const val UNITS = "units"
        private const val FILE_NAME = "city_list.json"
    }
}