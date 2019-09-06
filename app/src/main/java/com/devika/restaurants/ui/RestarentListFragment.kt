package com.devika.restaurants.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.devika.RestaurantApplication
import com.devika.restaurants.R
import com.devika.restaurants.databinding.FragmentRestaurantsListBinding

class RestarentListFragment : Fragment() {
    lateinit var binding: FragmentRestaurantsListBinding
    lateinit var restarentListViewModel: RestarentListViewModel
    lateinit var restaurentListAdapter: RestaurantListAdapter
    lateinit var restaurantViewModelFactory: RestaurantViewmodelFactory
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_restaurants_list, container, false)
        initToolbar()
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        restaurantViewModelFactory = RestaurantViewmodelFactory(RestaurantApplication.repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initRecyclerView()
        initViewModel()
    }

    private fun initViewModel() {
        restarentListViewModel = ViewModelProviders.of(this, restaurantViewModelFactory)
            .get(RestarentListViewModel::class.java)
        restarentListViewModel.restarantMutableLiveData.observe(
            this, Observer { restaurentListAdapter.updateData(it) })
    }

    private fun initRecyclerView() {
        restaurentListAdapter = RestaurantListAdapter(emptyList(), RestaurantApplication.repository)
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(activity)
            adapter = restaurentListAdapter
        }
    }

    private fun initToolbar() {

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                restarentListViewModel.searchRestaurant(query)
                return true
            }


        })
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.groupId == R.id.sorting_group) {
            if (!item.isChecked) {
                item.isChecked = true
                var selectedOption = when (item?.itemId) {
                    R.id.ave_prod_price -> "ave_prod_price asc"
                    R.id.best_match -> "best_match desc"
                    R.id.delivery_cost -> "delivery_cost asc"
                    R.id.distance -> "distance asc"
                    R.id.min_cost -> "min_cost asc"
                    R.id.newest -> "newest desc"
                    R.id.popularity -> "popularity desc"
                    R.id.average_rating -> "rating_average DESC"
                    else -> "popularity desc"
                }
                restarentListViewModel.getRestaurents(selectedOption)
            }
        }
        return super.onOptionsItemSelected(item)

    }
}