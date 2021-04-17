package ru.handh.headshandsdemo.domain

import io.reactivex.rxjava3.core.Single
import ru.handh.headshandsdemo.domain.model.WeatherDomain

interface WeatherRepo {

    fun getWeather(cityName: String): Single<WeatherDomain?>
}