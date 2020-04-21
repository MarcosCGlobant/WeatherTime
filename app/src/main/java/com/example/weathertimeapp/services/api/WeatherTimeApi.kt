package com.example.weathertimeapp.services.api

import com.example.weathertimeapp.services.response.ForecastResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface WeatherTimeApi {
    @GET("forecast")
    fun getCityById(@QueryMap filter: HashMap<String, String>): Call<ForecastResponse>
}