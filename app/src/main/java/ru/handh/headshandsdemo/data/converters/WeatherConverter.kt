package ru.handh.headshandsdemo.data.converters

import ru.handh.headshandsdemo.data.models.*
import ru.handh.headshandsdemo.domain.models.WeatherDomain

object WeatherConverter {

    fun fromApiToDomain(weatherApi: WeatherApi): WeatherDomain {
        val temp = weatherApi.main.temp
        val tempString = if (temp > 0) "+${temp.toInt()}" else "${temp.toInt()}"
        return WeatherDomain(weatherApi.name, tempString)
    }
}