package ru.handh.headshandsdemo.presentation.viewmodels

import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.core.Single
import ru.handh.headshandsdemo.domain.interactor.WeatherInteractor
import ru.handh.headshandsdemo.domain.model.WeatherDomain

class WeatherViewModel(private val weatherInteractor: WeatherInteractor): BaseViewModel() {

    fun getWeather(cityName: String): LiveData<WeatherDomain?> {
        return invoke(weatherInteractor.getWeather(cityName), null)
    }
}