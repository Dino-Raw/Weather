package com.example.weather.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.WeatherRowBinding
import com.example.weather.model.Hour
import com.example.weather.viewholder.WeatherViewHolder

class WeatherAdapter: RecyclerView.Adapter<WeatherViewHolder>() {
    private val hourList = ArrayList<Hour>()

    @SuppressLint("NotifyDataSetChanged")
    fun setHourList(hourList: ArrayList<Hour>){
        this.hourList.clear()
        this.hourList.addAll(hourList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = WeatherRowBinding.inflate(layoutInflater)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(hourList[position])
    }

    override fun getItemCount() = hourList.size
}