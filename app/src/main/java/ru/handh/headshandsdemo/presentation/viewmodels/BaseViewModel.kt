package ru.handh.headshandsdemo.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun <T> invoke(single: Single<T?>, throwableValue: T?): LiveData<T?> {
        val liveData = MutableLiveData<T?>()
        compositeDisposable.add(single.subscribe({
            liveData.postValue(it)
        }, {
            liveData.postValue(throwableValue)
        }))
        return liveData
    }
}