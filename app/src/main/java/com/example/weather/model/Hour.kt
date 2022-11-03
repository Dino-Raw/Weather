package com.example.weather.model

data class Hour(
    val time: String,
    val condition: Condition,
    val temperature: Double,
)
