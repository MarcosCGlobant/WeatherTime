package com.example.weathertimeapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weathertimeapp.R
import com.example.weathertimeapp.mvp.contracts.WeatherTimeContracts
import com.example.weathertimeapp.mvp.model.WeatherTimeModel
import com.example.weathertimeapp.mvp.presenter.WeatherTimePresenter
import com.example.weathertimeapp.mvp.view.WeatherTimeView

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: WeatherTimeContracts.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = WeatherTimePresenter(WeatherTimeModel(readCitiesJSONFile()), WeatherTimeView(this))
        presenter.initPresenter()
    }

    private fun readCitiesJSONFile(): String {
        return applicationContext.assets.open(FILE_NAME).bufferedReader().use {
            it.readText()
        }
    }

    companion object {
        private const val FILE_NAME = "city.list.json"
    }
}