package com.example.weather.model

import org.json.JSONObject

class WeatherParser {
    fun parseWeather(weatherJson: JSONObject): WeatherModel {
        val weather = WeatherModel(
            hour = mutableListOf()
        )

        val weatherHourArrayJson = weatherJson
            .optJSONObject("forecast")
            ?.optJSONArray("forecastday")
            ?.optJSONObject(0)
            ?.optJSONArray("hour")

        if (weatherHourArrayJson != null)
            for (hour in 0 until weatherHourArrayJson.length()) {
                weatherHourArrayJson.optJSONObject(hour).also { weatherHourJson ->
                    val time = weatherHourJson.optString("time").substringAfter(" ")
                    val temperature = weatherHourJson.optDouble("temp_c")
                    val conditionModel: Condition? = weatherHourJson?.optJSONObject("condition")
                        .let { condition ->
                            if (condition != null)
                                Condition (
                                    text = condition.optString("text"),
                                    icon = condition.optString("icon"),
                                )
                            else
                                null
                    }

                    if (conditionModel != null)
                        Hour(
                            time = time,
                            temperature = temperature,
                            condition = conditionModel,
                        ).also { hour ->
                            if (weather.hour.isEmpty() || hour !in weather.hour)
                                weather.hour.add(hour)
                        }
                }
            }

        return weather
    }
}