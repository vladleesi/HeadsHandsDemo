package ru.handh.headshandsdemo.data.api.services

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.handh.headshandsdemo.data.api.ApiConfig
import ru.handh.headshandsdemo.data.models.WeatherApi

interface WeatherServices {

    @GET(ApiConfig.GET_WEATHER_API)
    fun getWeather(
        @Query("q") cityName: String,
        @Query("units") units: String,
        @Query("appid") apiKey: String
    ): Single<WeatherApi>
}