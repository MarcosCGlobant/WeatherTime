package com.example.weathertimeapp.mvp.view

import androidx.fragment.app.Fragment
import com.example.weathertimeapp.activities.WeatherDetailsFragment
import com.example.weathertimeapp.data.entities.Forecast
import com.example.weathertimeapp.mvp.contracts.WeatherDetailsContract
import com.example.weathertimeapp.mvp.view.base.FragmentView

class WeatherDetailsView (fragment: Fragment): FragmentView(fragment), WeatherDetailsContract.View{
}