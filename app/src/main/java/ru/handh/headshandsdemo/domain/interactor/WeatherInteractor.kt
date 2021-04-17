package ru.handh.headshandsdemo.domain.interactor

import io.reactivex.rxjava3.core.Single
import ru.handh.headshandsdemo.domain.WeatherRepo
import ru.handh.headshandsdemo.domain.model.WeatherDomain

class WeatherInteractor(private val weatherRepo: WeatherRepo) {

    fun getWeather(cityName: String): Single<WeatherDomain?> {
        return weatherRepo.getWeather(cityName)
    }
}