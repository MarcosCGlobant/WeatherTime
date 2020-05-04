package com.example.weathertimeapp.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.weathertimeapp.R
import com.example.weathertimeapp.mvp.contracts.WeatherDetailsContracts
import com.example.weathertimeapp.mvp.model.WeatherDetailsModel
import com.example.weathertimeapp.mvp.presenter.WeatherDetailsPresenter
import com.example.weathertimeapp.mvp.view.WeatherDetailsView
import kotlinx.android.synthetic.main.fragment_extended_weather.view.btn_close

class WeatherDetailsFragment : DialogFragment() {

    private lateinit var weatherDetailPresenter: WeatherDetailsContracts.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView: View = inflater.inflate(R.layout.fragment_extended_weather, container, false)
        val cityData = Pair(this.arguments?.getInt(CITY_ID), this.arguments?.getInt(POSITION))
        weatherDetailPresenter =
            WeatherDetailsPresenter(
                WeatherDetailsModel(),
                WeatherDetailsView(this),
                cityData
            )
        rootView.btn_close.setOnClickListener {
            this.dismiss()
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherDetailPresenter.requestAndShowExtendedWeather()
    }

    companion object {
        private const val CITY_ID = "character_id"
        private const val POSITION = "position"
        fun newInstance(city_id: Int, weather_position: Int): WeatherDetailsFragment {
            val args = Bundle()
            args.putInt(CITY_ID, city_id)
            args.putInt(POSITION, weather_position)
            val fragment = WeatherDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}