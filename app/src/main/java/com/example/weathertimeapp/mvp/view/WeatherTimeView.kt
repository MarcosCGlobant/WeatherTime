package com.example.weathertimeapp.mvp.view

import android.app.Activity
import android.widget.ArrayAdapter
import com.example.weathertimeapp.mvp.contracts.WeatherTimeContracts
import com.example.weathertimeapp.mvp.view.base.ActivityView
import kotlinx.android.synthetic.main.activity_main.activity_main_edit_text_city

class WeatherTimeView(activity: Activity) : ActivityView(activity), WeatherTimeContracts.View {

    override fun setCitiesListAdapter(cities: MutableList<String>) {
        val adapter = activity?.applicationContext?.let { ArrayAdapter(it, android.R.layout.simple_list_item_1, cities) }
        activity?.activity_main_edit_text_city?.setAdapter(adapter)
    }
}