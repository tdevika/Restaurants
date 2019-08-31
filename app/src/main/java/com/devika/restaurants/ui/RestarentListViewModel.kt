package com.devika.restaurants.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.sqlite.db.SimpleSQLiteQuery
import com.devika.restaurants.data.model.Restaurant
import com.devika.restaurants.data.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RestarentListViewModel(var baseRepository: BaseRepository) : ViewModel() {
    val restarantMutableLiveData = MutableLiveData<List<Restaurant>>()

    init {
        getRestaurents()
    }

    fun getRestaurents() {
        viewModelScope.launch {
            val restaurents = withContext(Dispatchers.IO) {
                baseRepository.getRestaurantFromServer()

            }
            withContext(Dispatchers.Main) {
                restarantMutableLiveData.postValue(restaurents)

            }

        }
    }

    fun getRestaurents(selectedOption: String) {
        viewModelScope.launch {
            val restaurents = withContext(Dispatchers.IO) {
                baseRepository.getRestaurantsBySort(selectedOption)

            }
            withContext(Dispatchers.Main) {
                restarantMutableLiveData.postValue(restaurents)

            }

        }
    }
    fun searchRestaurant(query:String){
        viewModelScope.launch {
          var searchRestaurant= withContext(Dispatchers.IO){
                baseRepository.getSearchRestaurant(query)
            }
            withContext(Dispatchers.Main){
                restarantMutableLiveData.postValue(searchRestaurant)
            }
        }



    }
}