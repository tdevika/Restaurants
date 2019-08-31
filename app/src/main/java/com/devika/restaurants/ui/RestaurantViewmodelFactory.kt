package com.devika.restaurants.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devika.restaurants.data.repository.BaseRepository

class RestaurantViewmodelFactory(var repository: BaseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RestarentListViewModel::class.java)) {
            return RestarentListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }

}