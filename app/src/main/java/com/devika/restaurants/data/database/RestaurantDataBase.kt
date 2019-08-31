package com.devika.restaurants.data.database

import android.content.Context
import androidx.room.*
import com.devika.restaurants.data.model.Favourite
import com.devika.restaurants.data.model.Restaurant
import com.devika.restaurants.data.model.RestaurantList
import com.devika.restaurants.data.model.StatusCoverter

@Database(entities = [Restaurant::class,Favourite::class], version = 2, exportSchema = false)
@TypeConverters(StatusCoverter::class)
abstract class RestaurantDataBase : RoomDatabase() {
    abstract fun rastaurantDao(): RestaurantDAO

    companion object {
        @Volatile
        private var INSTANCE: RestaurantDataBase? = null

        fun getInstance(context: Context): RestaurantDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RestaurantDataBase::class.java,
                        "restaurant_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE=instance
                }
                return instance
            }

        }
    }
}
