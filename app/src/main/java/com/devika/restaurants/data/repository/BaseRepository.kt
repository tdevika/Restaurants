package com.devika.restaurants.data.repository

import android.content.Context
import androidx.sqlite.db.SimpleSQLiteQuery
import com.devika.RestaurantApplication.Companion.apiService
import com.devika.restaurants.data.database.RestaurantDataBase
import com.devika.restaurants.data.model.Favourite
import com.devika.restaurants.data.model.Restaurant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BaseRepository(application: Context) {

    var restaurantDao = RestaurantDataBase.getInstance(application).rastaurantDao()
    suspend fun getRestaurantFromServer(): List<Restaurant> {
        var favourite: List<String> = restaurantDao.getFavourite()
        var restauranrsFromServer = apiService.getRestarentList().restaurants
        var restaurantData = updaDateRestaurant(favourite, restauranrsFromServer)
        restaurantDao.setRestaurants(restaurantData)
        return restaurantDao.getRestaurants()
    }

    fun updaDateRestaurant(
        favourite: List<String>,
        restaurants: List<Restaurant>
    ): List<Restaurant> {
        for (restaurent in restaurants) {
            if (favourite.contains(restaurent.name)) {
                restaurent.favourite = true
            }

        }
        return restaurants
    }

    fun setFavourite(favourite: Favourite) {
        CoroutineScope(Dispatchers.IO).launch {
            restaurantDao.setFavouritename(favourite)
            val restaurentnames = restaurantDao.getFavFromRestaurent(favourite.favouriteName)
            restaurentnames.favourite = true
            restaurantDao.updateData(restaurentnames)
        }
    }

    fun deleteFavouriteName(favourite: Favourite) {
        CoroutineScope(Dispatchers.IO).launch {
            restaurantDao.deleteFavourite(favourite)
            val restaurants = restaurantDao.getFavFromRestaurent(favourite.favouriteName)
            restaurants.favourite = false
            restaurantDao.updateData(restaurants)
        }
    }

    suspend fun getRestaurantsBySort(selectedOption: String): List<Restaurant> {
        val query =
            SimpleSQLiteQuery("SELECT * FROM restaurants order by favourite DESC ,status ASC, $selectedOption")
        return restaurantDao.getRestaurantsBySort(query)
    }
   suspend fun getSearchRestaurant(query:String):List<Restaurant>{
      return restaurantDao.getSearchRestaurant(query)

    }

}
