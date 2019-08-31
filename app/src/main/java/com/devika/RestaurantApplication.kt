package com.devika

import android.app.Application
import com.devika.restaurants.data.repository.BaseRepository
import com.devika.restaurants.data.services.ApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestaurantApplication : Application() {



    companion object {
        lateinit var apiService: ApiService
        lateinit var repository: BaseRepository
    }

    override fun onCreate() {
        super.onCreate()
        val restrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl("https://api.jsonbin.io/b/")
            .build()
        repository = BaseRepository(applicationContext)
        apiService = restrofit.create(ApiService::class.java)
    }

}