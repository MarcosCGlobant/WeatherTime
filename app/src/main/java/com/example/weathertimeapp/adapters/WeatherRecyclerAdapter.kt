package com.example.weathertimeapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.weathertimeapp.R
import com.example.weathertimeapp.mvp.entities.Weather
import kotlinx.android.synthetic.main.layout_weather_list_item.view.image_view_weather_icon
import kotlinx.android.synthetic.main.layout_weather_list_item.view.text_view_date
import kotlinx.android.synthetic.main.layout_weather_list_item.view.text_view_description
import kotlinx.android.synthetic.main.layout_weather_list_item.view.text_view_max_temperature
import kotlinx.android.synthetic.main.layout_weather_list_item.view.text_view_min_temperature

class WeatherRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Weather> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WeatherViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_weather_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is WeatherViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(weatherList: List<Weather>) {
        items = weatherList
    }

    class WeatherViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(weather: Weather) {

            itemView.text_view_date.text = weather.date
            itemView.text_view_max_temperature.text = weather.maxTemp
            itemView.text_view_min_temperature.text = weather.minTemp
            itemView.text_view_description.text = weather.description

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(weather.image)
                .into(itemView.image_view_weather_icon)
        }
    }

}