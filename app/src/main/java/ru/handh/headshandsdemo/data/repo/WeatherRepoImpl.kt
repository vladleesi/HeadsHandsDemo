package ru.handh.headshandsdemo.data.repo

import android.util.Log
import io.reactivex.rxjava3.core.Single
import ru.handh.headshandsdemo.data.api.ApiConfig
import ru.handh.headshandsdemo.data.api.CommonApi
import ru.handh.headshandsdemo.data.converter.WeatherConverter
import ru.handh.headshandsdemo.domain.WeatherRepo
import ru.handh.headshandsdemo.domain.model.WeatherDomain

class WeatherRepoImpl : WeatherRepo {

    private val weatherServices by lazy { CommonApi.weatherServices(ApiConfig.WEATHER_BASE_URL) }
    private val apiKey = "56666cec79b188b1dcba614b4472172e"
    private val units = "metric"

    override fun getWeather(cityName: String): Single<WeatherDomain?> {
        return weatherServices.getWeather(cityName, units, apiKey)
            .map { weatherApi -> WeatherConverter.fromApiToDomain(weatherApi) }
            .doOnError { Log.d(TAG, it.message ?: it.toString()) }
            .doOnSuccess { Log.i(TAG, "Getting weather have been success") }
            .onErrorReturn { null }
    }

    companion object {
        const val TAG = "WeatherRepoImpl"
    }
}