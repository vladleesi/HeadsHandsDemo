package ru.handh.headshandsdemo.domain.interactors

import io.reactivex.rxjava3.core.Single
import ru.handh.headshandsdemo.domain.repositories.WeatherRepo
import ru.handh.headshandsdemo.domain.models.WeatherDomain

class WeatherInteractor(private val weatherRepo: WeatherRepo) {

    fun getWeather(cityName: String): Single<WeatherDomain?> {
        return weatherRepo.getWeather(cityName)
    }
}