package com.example.weather.viewholder

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.WeatherRowBinding
import com.example.weather.model.Hour
import com.squareup.picasso.Picasso

class WeatherViewHolder(private val binding: WeatherRowBinding)
    : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(hour: Hour) {
            Picasso.Builder(binding.root.context).build()
                .load("https:${hour.condition.icon}").into(binding.conditionIcon)
            binding.conditionText.text = hour.condition.text
            binding.temperatureText.text = "${hour.temperature} °C"
            binding.timeText.text = hour.time
        }
}