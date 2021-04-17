package ru.handh.headshandsdemo.data.converter

import ru.handh.headshandsdemo.data.model.*
import ru.handh.headshandsdemo.domain.model.WeatherDomain

object WeatherConverter {

    fun fromApiToDomain(weatherApi: WeatherApi): WeatherDomain {
        val temp = weatherApi.main.temp
        val tempString = if (temp > 0) "+${temp.toInt()}" else "${temp.toInt()}"
        return WeatherDomain(weatherApi.name, tempString)
    }
}