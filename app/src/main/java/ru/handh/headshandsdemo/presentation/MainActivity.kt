package ru.handh.headshandsdemo.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.core.context.startKoin
import org.koin.dsl.module
import ru.handh.headshandsdemo.R
import ru.handh.headshandsdemo.data.repositories.WeatherRepoImpl
import ru.handh.headshandsdemo.domain.interactors.WeatherInteractor
import ru.handh.headshandsdemo.domain.repositories.WeatherRepo
import ru.handh.headshandsdemo.presentation.fragments.AuthFragment
import ru.handh.headshandsdemo.presentation.viewmodels.WeatherViewModel

class MainActivity : AppCompatActivity() {

    val koin = startKoin {
        modules(weatherModule())
    }.koin

    private fun weatherModule() = module {
        factory<WeatherRepo> { WeatherRepoImpl() }
        factory { WeatherInteractor(get()) }
        factory { WeatherViewModel(get()) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fcv_content, AuthFragment.newInstance(), AuthFragment.TAG)
            .commit()
    }
}