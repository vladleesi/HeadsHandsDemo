package ru.handh.headshandsdemo.data.api

import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.handh.headshandsdemo.data.api.services.WeatherServices
import java.util.concurrent.TimeUnit

object CommonApi {

    /** Getting weather services */
    fun weatherServices(baseUrl: String): WeatherServices {
        return RetrofitClient.getClient(baseUrl).create(WeatherServices::class.java)
    }

    private object RetrofitClient {

        private const val TIMEOUT = 10L

        /** Builder with default params */
        private fun getRetrofitBuilder(baseUrl: String): Retrofit.Builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))

        /** OkHttpClient with HttpLoggingInterceptor */
        private fun okHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .callTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().also {
                    it.level = HttpLoggingInterceptor.Level.BASIC
                })
                .build()
        }

        /** Getting retrofit client */
        fun getClient(baseUrl: String): Retrofit =
            getRetrofitBuilder(baseUrl).client(okHttpClient()).build()
    }
}