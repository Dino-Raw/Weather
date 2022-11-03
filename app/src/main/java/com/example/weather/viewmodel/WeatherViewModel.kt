package com.example.weather.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.adapter.WeatherAdapter
import com.example.weather.model.WeatherModel
import com.example.weather.model.WeatherParser
import com.example.weather.model.WeatherSharedPreferences
import com.example.weather.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject


class WeatherViewModel: ViewModel() {
    private val _weather: MutableLiveData<WeatherModel?> = MutableLiveData()
    val weather: LiveData<WeatherModel?> = _weather

    private var _param: MutableLiveData<String> = MutableLiveData()
    val param: LiveData<String> = _param

    val weatherAdapter = WeatherAdapter()

    fun setParam(param: String) {
        _param.value = param
    }

    fun getWeather() {
        if (!Repository.isRetrofitInit()) Repository.initRetrofit()


            viewModelScope.launch(Dispatchers.IO) {
                try {
                val weatherDataString =
                    Repository.retrofit.getWeather(param = _param.value).let { response ->
                        response?.body()?.string()
                    }

                withContext(Dispatchers.Default) {
                    if (weatherDataString != null)
                        WeatherParser().parseWeather(JSONObject(weatherDataString))
                            .also { weatherModel ->
                                _weather.postValue(weatherModel)
                            }
                }
                } catch (e: Exception) {
                    _weather.postValue(null)
                }
            }

    }

    fun setAdapter() {
        weatherAdapter.setHourList(_weather.value?.hour as ArrayList)
    }

    fun loadWeatherData(context: Context) {
        println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
        _weather.value = _param.value?.let { city ->
            WeatherSharedPreferences().loadWeatherData(context, city)
        }
        println(_weather.value)
    }

    fun saveWeatherData(context: Context) {
        println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
        _weather.value?.let { weather ->
            _param.value?.let { city ->
                WeatherSharedPreferences().saveWeatherData(context,
                    weather, city)
            }
        }
    }
}