package com.example.weathertimeapp.mvp.view

import android.app.Activity
import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weathertimeapp.R
import com.example.weathertimeapp.adapters.ForecastRecyclerAdapter
import com.example.weathertimeapp.data.entities.Forecast
import com.example.weathertimeapp.mvp.contracts.WeatherTimeContracts
import com.example.weathertimeapp.mvp.view.base.ActivityView
import kotlinx.android.synthetic.main.activity_main.activity_main_autocomplete_text_view_city
import kotlinx.android.synthetic.main.activity_main.activity_main_city_name_text_view
import kotlinx.android.synthetic.main.activity_main.main_activity_recycler_view
import kotlinx.android.synthetic.main.activity_main.progressBar

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
        activity?.activity_main_autocomplete_text_view_city?.setAdapter(adapter)
    }

    override fun showForecastRecyclerView(forecast: Forecast) {
        activity?.activity_main_city_name_text_view?.text = forecast.city.name
        activity?.main_activity_recycler_view?.apply {
            this.layoutManager = LinearLayoutManager(activity)
            forecastAdapter = ForecastRecyclerAdapter()
            adapter = forecastAdapter
            forecastAdapter.submitList(forecast.days)
        }
    }

    override fun showToastNoItemToShowError() {
        showErrorMessage(R.string.message_no_items_to_show)
    }

    override fun showInsertCityNameError() {
        showErrorMessage(R.string.message_insert_city_name)
    }

    override fun showToastNetworkError() {
        showErrorMessage(R.string.message_network_connection)
    }

    private fun showErrorMessage(message: Int) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun hideLoading() {
        activity?.progressBar?.visibility = View.GONE
        showCityName()
        showRecycler()
    }

    override fun showLoading() {
        activity?.progressBar?.visibility = View.VISIBLE
        hideCityName()
        hideRecycler()
    }

    private fun hideRecycler() {
        activity?.main_activity_recycler_view?.visibility = View.GONE
    }

    private fun showRecycler() {
        activity?.main_activity_recycler_view?.visibility = View.VISIBLE
    }

    private fun hideCityName() {
        activity?.activity_main_city_name_text_view?.visibility = View.GONE
    }

    private fun showCityName() {
        activity?.activity_main_city_name_text_view?.visibility = View.VISIBLE
    }
}