package com.devika.restaurants.data.services

import retrofit2.http.GET
import com.devika.restaurants.data.model.*
import retrofit2.http.*


interface ApiService {
    @GET("5d5d37e34acbd77681dce3c2")
    suspend fun getRestarentList(): RestaurantList

}