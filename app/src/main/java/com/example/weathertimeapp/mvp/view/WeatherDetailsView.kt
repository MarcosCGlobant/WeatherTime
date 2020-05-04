package com.example.weathertimeapp.mvp.view

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.weathertimeapp.R
import com.example.weathertimeapp.data.entities.Day
import com.example.weathertimeapp.mvp.contracts.WeatherDetailsContracts
import com.example.weathertimeapp.mvp.view.base.FragmentView
import kotlinx.android.synthetic.main.fragment_extended_weather.image_view_weather_icon
import kotlinx.android.synthetic.main.fragment_extended_weather.progress_bar
import kotlinx.android.synthetic.main.fragment_extended_weather.text_view_date
import kotlinx.android.synthetic.main.fragment_extended_weather.text_view_description
import kotlinx.android.synthetic.main.fragment_extended_weather.text_view_humidity_value
import kotlinx.android.synthetic.main.fragment_extended_weather.text_view_max_temperature
import kotlinx.android.synthetic.main.fragment_extended_weather.text_view_min_temperature
import kotlinx.android.synthetic.main.fragment_extended_weather.text_view_pressure_value
import kotlinx.android.synthetic.main.fragment_extended_weather.text_view_temp_feels_value
import kotlinx.android.synthetic.main.fragment_extended_weather.text_view_wind_speed_value

class WeatherDetailsView(fragment: Fragment) : FragmentView(fragment), WeatherDetailsContracts.View {

    override fun showExtendedWeather(day: Day) {
        fragment?.text_view_date?.text = day.date
        fragment?.text_view_temp_feels_value?.text = "${day.temperature.temp_feels_like.toInt()}$CELSIUS"
        fragment?.text_view_max_temperature?.text = "${day.temperature.temp_max.toInt()}$CELSIUS"
        fragment?.text_view_min_temperature?.text = "${day.temperature.temp_min.toInt()}$CELSIUS"
        fragment?.text_view_pressure_value?.text = "${day.temperature.pressure}$MILLIBARS"
        fragment?.text_view_humidity_value?.text = "${day.temperature.humidity}$PERCENT"
        fragment?.text_view_description?.text = day.weather.description
        fragment?.text_view_wind_speed_value?.text = "${day.wind.speed}$METERSSEC"

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        fragment?.let {
            fragment?.image_view_weather_icon?.let { it1 ->
                Glide.with(it)
                    .applyDefaultRequestOptions(requestOptions)
                    .load("$URL${day.weather.icon}$FORMAT")
                    .into(it1)
            }
        }
    }

    override fun showLoading() {
        fragment?.progress_bar?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        fragment?.progress_bar?.visibility = View.GONE
    }

    override fun showToastNetworkError() {
        Toast.makeText(fragment?.context, R.string.message_network_connection, Toast.LENGTH_LONG).show()
    }

    companion object {
        private const val CELSIUS = "°C"
        private const val MILLIBARS = "MB"
        private const val PERCENT = "%"
        private const val METERSSEC = "M/s"
        private const val URL = "http://openweathermap.org/img/w/"
        private const val FORMAT = ".png"
    }
}