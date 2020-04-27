package com.example.weathertimeapp.data.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ForecastRequestGenerator {
    private val httpClient = OkHttpClient.Builder().build()

    private val builder = Retrofit.Builder()
        .baseUrl(OPEN_WEATHER_BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())

    fun <S> createService(serviceClass: Class<S>): S {
        val retrofit = builder.client(httpClient).build()
        return retrofit.create(serviceClass)
    }

    companion object {
        const val API_KEY = "33b5f0a79456f98b7a25c98ea319831d"
        private const val OPEN_WEATHER_BASE_URL = "http://api.openweathermap.org/data/2.5/"
    }
}