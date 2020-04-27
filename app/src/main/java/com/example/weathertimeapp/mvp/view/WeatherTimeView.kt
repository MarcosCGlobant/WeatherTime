package com.example.weathertimeapp.mvp.view

import android.app.Activity
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weathertimeapp.adapters.ForecastRecyclerAdapter
import com.example.weathertimeapp.data.entities.Forecast
import com.example.weathertimeapp.mvp.contracts.WeatherTimeContracts
import com.example.weathertimeapp.mvp.view.base.ActivityView
import kotlinx.android.synthetic.main.activity_main.activity_main_edit_text_city
import kotlinx.android.synthetic.main.activity_main.main_activity_recycler_view

class WeatherTimeView(activity: Activity) : ActivityView(activity), WeatherTimeContracts.View {

    private lateinit var forecastAdapter: ForecastRecyclerAdapter

    override fun setSoftInputMode() {
        activity?.window?.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        )
        activity?.window?.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING
        )
    }

    override fun setCitiesListAdapter(cities: MutableList<String>) {
        val adapter = activity?.applicationContext?.let { ArrayAdapter(it, android.R.layout.simple_list_item_1, cities) }
        activity?.activity_main_edit_text_city?.setAdapter(adapter)
    }

    override fun showForecastRecyclerView(forecast: Forecast) {
        activity?.main_activity_recycler_view?.apply {
            this.layoutManager = LinearLayoutManager(activity)
            forecastAdapter = ForecastRecyclerAdapter()
            adapter = forecastAdapter
            forecastAdapter.submitList(forecast.days)
        }
    }
}