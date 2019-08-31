package com.devika.restaurants.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName








data class RestaurantList(
    val restaurants: List<Restaurant>
)

@Entity(tableName = "restaurants")
data class Restaurant(
    @PrimaryKey
    @NonNull
    val name: String,
    @Embedded
    val sortingValues: SortingValues,
    val status: Status,
    var favourite: Boolean = false

)

@Entity(tableName = "favorite")
data class Favourite(
    @PrimaryKey
    val favouriteName: String

)


data class SortingValues(
    @ColumnInfo(name = "ave_prod_price")
    val averageProductPrice: Int,
    @ColumnInfo(name = "best_match")
    val bestMatch: Int,
    @ColumnInfo(name = "delivery_cost")
    val deliveryCosts: Int,
    val distance: Int,
    @ColumnInfo(name = "min_cost")
    val minCost: Int,
    val newest: Int,
    val popularity: Int,
    @ColumnInfo(name = "rating_average")
    val ratingAverage: Double
)