package com.devika.restaurants.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.devika.restaurants.data.model.Favourite
import com.devika.restaurants.data.model.Restaurant
import com.devika.restaurants.data.model.RestaurantList
import com.devika.restaurants.data.repository.BaseRepository
import com.devika.restaurants.databinding.ItemRestaurantsBinding

class RestaurantListAdapter(
    var restaurentList: List<Restaurant>,
    var baseRepository: BaseRepository
) :
    RecyclerView.Adapter<RestaurantListAdapter.SearchViewHolder>() {
    fun updateData(restaurant: List<Restaurant>) {
        this.restaurentList = restaurant
        notifyDataSetChanged()
    }

    lateinit var binding: ItemRestaurantsBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val container = LayoutInflater.from(parent.context)
        binding = ItemRestaurantsBinding.inflate(container)
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return restaurentList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        var restaurant = restaurentList[position]
        holder.setRestaurant(restaurant)
    }

    inner class SearchViewHolder(var binding: ItemRestaurantsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setRestaurant(restaurant: Restaurant) {
            binding.restaurent = restaurant

            binding.fav.setOnClickListener(
                View.OnClickListener {
                    if (binding.fav.isChecked) {

                        baseRepository.setFavourite(Favourite(favouriteName = restaurant.name))
                    } else {
                        baseRepository.deleteFavouriteName(Favourite(favouriteName = restaurant.name))

                    }
                })
        }

    }
}

