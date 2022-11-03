package com.example.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.adapter.CitiesAdapter

class CitiesViewModel: ViewModel() {
    // допустим данные получил со стороннего источника
    private val _cities: MutableLiveData<ArrayList<String>> = MutableLiveData(
        arrayListOf(
            "Moscow",
            "Saint-Petersburg",
            "Novosibirsk",
            "Yekaterinburg",
            "Kazan",
            "Nizhny-Novgorod",
            "Chelyabinsk",
            "Krasnoyarsk",
            "Samara",
            "Ufa",
        )
    )

    val cities: LiveData<ArrayList<String>> = _cities

    val citiesAdapter = CitiesAdapter()

    fun setAdapter() {
        _cities.value?.let {
            citiesAdapter.setCitiesList(it as ArrayList)
        }
    }
}