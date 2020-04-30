package com.example.weathertimeapp.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.weathertimeapp.R
import com.example.weathertimeapp.mvp.contracts.WeatherDetailsContract
import com.example.weathertimeapp.mvp.model.WeatherDetailsModel
import com.example.weathertimeapp.mvp.presenter.WeatherDetailsPresenter
import com.example.weathertimeapp.mvp.view.WeatherDetailsView
import kotlinx.android.synthetic.main.fragment_extended_weather.view.btn_close

class WeatherDetailsFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView: View = inflater.inflate(R.layout.fragment_extended_weather, container, false)
        val cityId = this.arguments?.getInt(CITY_ID)
        val date = this.arguments?.getString(DATE)
        val weatherDetailPresenter: WeatherDetailsContract.Presenter =
            WeatherDetailsPresenter(
                WeatherDetailsModel(),
                WeatherDetailsView(this),
                cityId,
                date
            )
        rootView.btn_close.setOnClickListener {
            this.dismiss()
        }
        return rootView
    }

    companion object {
        private const val CITY_ID = "character_id"
        private const val DATE = "date"
        fun newInstance(city_id: Int, weather_date: String): WeatherDetailsFragment {
            val args = Bundle()
            args.putInt(CITY_ID, city_id)
            args.putString(DATE, weather_date)
            val fragment = WeatherDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}