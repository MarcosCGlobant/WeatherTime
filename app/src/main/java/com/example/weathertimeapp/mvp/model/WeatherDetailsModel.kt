package com.example.weathertimeapp.mvp.model

import com.example.weathertimeapp.data.entities.Day
import com.example.weathertimeapp.data.mapper.ExtendedWeatherMapperService
import com.example.weathertimeapp.data.services.ForecastRequestGenerator
import com.example.weathertimeapp.data.services.api.WeatherTimeApi
import com.example.weathertimeapp.mvp.contracts.WeatherDetailsContracts
import com.example.weathertimeapp.utils.UNIT
import io.reactivex.Observable

class WeatherDetailsModel : WeatherDetailsContracts.Model {

    private val api: ForecastRequestGenerator = ForecastRequestGenerator()
    private val mapper: ExtendedWeatherMapperService = ExtendedWeatherMapperService()

    override fun getExtendedWeather(cityData: Pair<Int, Int>): Observable<Day> {
        return Observable.create { subscriber ->
            val queryHashMap = LinkedHashMap<String, String>()
            queryHashMap[ID_CITY] = cityData.first.toString()
            queryHashMap[APPID] = ForecastRequestGenerator.API_KEY
            queryHashMap[UNITS] = UNIT
            val callResponse = api.createService(WeatherTimeApi::class.java).getCityById(queryHashMap)
            val response = callResponse.execute()
            if (response.isSuccessful) {
                response.body()?.list?.get(cityData.second)?.let { mapper.transform(it) }?.let { subscriber.onNext(it) }
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
    }
}