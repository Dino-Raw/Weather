package com.example.weather.model

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class WeatherSharedPreferences {
    @SuppressLint("CommitPrefEdits")
    fun saveWeatherData(context: Context, weatherModel: WeatherModel, city: String) {
        val sp: SharedPreferences =
            context.getSharedPreferences(city, Context.MODE_PRIVATE)

        sp.edit().also { editor ->
            weatherModel.hour.forEach { hour ->
                editor.putString("${hour.time}temperature", hour.temperature.toString())
                editor.putString("${hour.time}conditionText", hour.condition.text).apply()
                editor.putString("${hour.time}conditionIcon", hour.condition.icon).apply()
            }
        }.apply()
    }

    @SuppressLint("CommitPrefEdits")
    fun loadWeatherData(context: Context, city: String): WeatherModel {
        val sp: SharedPreferences =
            context.getSharedPreferences(city, Context.MODE_PRIVATE)

        val hourList: MutableList<Hour> = mutableListOf()

        for(count in 0 until 24) {
            val time = if (count >= 10) "$count:00" else "0$count:00"
            val conditionText = sp.getString("${time}conditionText", null)
            val conditionIcon = sp.getString("${time}conditionIcon", null)
            val temperature = sp.getString("${time}temperature", null)?.toDouble()

            if (conditionText != null && conditionIcon != null && temperature != null)
            hourList.add(
                Hour(
                    time,
                    Condition (
                        conditionText,
                        conditionIcon,
                    ),
                    temperature,

                )
            )
        }

        return WeatherModel(hourList)
    }
}