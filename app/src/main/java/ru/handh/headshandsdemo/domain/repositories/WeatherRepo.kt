package ru.handh.headshandsdemo.domain.repositories

import io.reactivex.rxjava3.core.Single
import ru.handh.headshandsdemo.domain.models.WeatherDomain

interface WeatherRepo {

    fun getWeather(cityName: String): Single<WeatherDomain?>
}