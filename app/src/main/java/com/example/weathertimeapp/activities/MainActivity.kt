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

        presenter = WeatherTimePresenter(WeatherTimeModel(this.assets), WeatherTimeView(this))
        presenter.initPresenter()
    }
}