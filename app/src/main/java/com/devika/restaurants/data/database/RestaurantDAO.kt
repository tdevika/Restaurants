package com.devika.restaurants.data.database

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.devika.restaurants.data.model.Favourite
import com.devika.restaurants.data.model.Restaurant

@Dao
interface RestaurantDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setRestaurants(resstaurants: List<Restaurant>)

    @Query("SELECT * FROM restaurants order by favourite DESC ,status ASC")
    suspend fun getRestaurants(): List<Restaurant>

    @RawQuery
    suspend fun getRestaurantsBySort(sort: SupportSQLiteQuery): List<Restaurant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setFavouritename(restaurant: Favourite)

    @Query("SELECT * FROM favorite")
    suspend fun getFavourite(): List<String>

    @Delete
    suspend fun deleteFavourite(name: Favourite)

    @Query("SELECT * from restaurants WHERE name=:name")
    suspend fun getFavFromRestaurent(name: String): Restaurant

    @Update
    suspend fun updateData(resstaurants: Restaurant)

    @Query("SELECT * FROM restaurants WHERE name LIKE :query")
    suspend fun getSearchRestaurant(query:String):List<Restaurant>


}