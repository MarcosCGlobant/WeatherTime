package com.example.weathertimeapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weathertimeapp.R
import com.example.weathertimeapp.adapter.OnWeatherListener
import com.example.weathertimeapp.mvp.contract.WeatherTimeContract
import com.example.weathertimeapp.mvp.model.WeatherTimeModel
import com.example.weathertimeapp.mvp.presenter.WeatherTimePresenter
import com.example.weathertimeapp.mvp.view.WeatherTimeView
import kotlinx.android.synthetic.main.activity_main.activity_main_autocomplete_text_view_city
import kotlinx.android.synthetic.main.activity_main.activity_main_button_search

class WeatherTimeActivity : AppCompatActivity(), OnWeatherListener {

    private lateinit var presenter: WeatherTimeContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = WeatherTimePresenter(WeatherTimeModel(this.assets), WeatherTimeView(this, this))
        presenter.initPresenter()

        initSearchButtonOnClickListener()
    }

    private fun initSearchButtonOnClickListener() {
        activity_main_button_search.setOnClickListener {
            presenter.onSearchButtonPressed(activity_main_autocomplete_text_view_city?.text.toString())
        }
    }

    override fun onWeatherClick(cityId: Int, position: Int) {
        val weatherFragment = WeatherDetailsFragment.newInstance(cityId, position)
        weatherFragment.show(supportFragmentManager, getString(R.string.tag))
    }
}