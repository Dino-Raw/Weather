package com.example.weather.viewholder

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.databinding.CitiesRowBinding

class CitiesViewHolder(private val binding: CitiesRowBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(city: String) {
        binding.city.text = city

        binding.root.setOnClickListener {
            val bundle: Bundle = bundleOf("city" to city)
            it.findNavController().navigate(R.id.action_CitiesFragment_to_WeatherFragment, bundle)
        }
    }
}