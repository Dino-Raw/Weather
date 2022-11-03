package com.example.weather.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.CitiesRowBinding
import com.example.weather.viewholder.CitiesViewHolder

class CitiesAdapter: RecyclerView.Adapter<CitiesViewHolder>() {
    private val citiesList = ArrayList<String>()

    @SuppressLint("NotifyDataSetChanged")
    fun setCitiesList(citiesList: ArrayList<String>) {
        this.citiesList.clear()
        this.citiesList.addAll(citiesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CitiesRowBinding.inflate(layoutInflater)
        return CitiesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        holder.bind(citiesList[position])
    }

    override fun getItemCount() = citiesList.size
}