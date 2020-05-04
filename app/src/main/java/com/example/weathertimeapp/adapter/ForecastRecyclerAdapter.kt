package com.example.weathertimeapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.weathertimeapp.R
import com.example.weathertimeapp.data.entity.Day
import com.example.weathertimeapp.util.ZERO
import kotlinx.android.synthetic.main.layout_weather_list_item.view.image_view_weather_icon
import kotlinx.android.synthetic.main.layout_weather_list_item.view.text_view_date
import kotlinx.android.synthetic.main.layout_weather_list_item.view.text_view_description
import kotlinx.android.synthetic.main.layout_weather_list_item.view.text_view_max_temperature
import kotlinx.android.synthetic.main.layout_weather_list_item.view.text_view_min_temperature

class ForecastRecyclerAdapter(private val onWeatherListener: OnWeatherListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Day> = ArrayList()
    private var idCity: Int = ZERO

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ForecastViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_weather_list_item, parent, false),
            onWeatherListener, idCity, items
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ForecastViewHolder).bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(daysList: List<Day>, cityId: Int) {
        items = daysList
        idCity = cityId
    }

    class ForecastViewHolder constructor(
        itemView: View, private val onWeatherListener: OnWeatherListener,
        private val idCity: Int, private val items: List<Day>
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        internal fun bind(day: Day) {
            itemView.setOnClickListener(this)
            itemView.apply {
                text_view_date.text = day.date
                text_view_max_temperature.text = "${day.temperature.temp_max.toInt()}$CELSIUS"
                text_view_min_temperature.text = "${day.temperature.temp_min.toInt()}$CELSIUS"
                text_view_description.text = day.weather.description

                val requestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)

                Glide.with(itemView.context)
                    .applyDefaultRequestOptions(requestOptions)
                    .load("$URL${day.weather.icon}$FORMAT")
                    .into(image_view_weather_icon)
            }
        }

        override fun onClick(v: View?) {
            onWeatherListener.onWeatherClick(idCity,adapterPosition )
        }

        companion object {
            private const val CELSIUS = "°C"
            private const val URL = "http://openweathermap.org/img/w/"
            private const val FORMAT = ".png"
        }
    }
}