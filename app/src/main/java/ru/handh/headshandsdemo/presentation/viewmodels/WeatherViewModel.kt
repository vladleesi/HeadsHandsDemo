package ru.handh.headshandsdemo.presentation.viewmodels

import androidx.lifecycle.LiveData
import ru.handh.headshandsdemo.domain.interactors.WeatherInteractor
import ru.handh.headshandsdemo.domain.models.WeatherDomain

class WeatherViewModel(private val weatherInteractor: WeatherInteractor): BaseViewModel() {

    fun getWeather(cityName: String): LiveData<WeatherDomain?> {
        return invoke(weatherInteractor.getWeather(cityName), null)
    }
}