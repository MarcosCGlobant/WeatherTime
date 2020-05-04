package com.example.weathertimeapp.data.service.api

import com.example.weathertimeapp.data.service.response.ForecastResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface WeatherTimeApi {
    @GET("forecast")
    fun getCityById(@QueryMap filter: HashMap<String, String>): Call<ForecastResponse>
}