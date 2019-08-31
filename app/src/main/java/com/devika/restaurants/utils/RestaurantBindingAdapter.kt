package com.devika.restaurants.utils

import android.widget.TextView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.databinding.BindingAdapter
import org.w3c.dom.Text

@BindingAdapter("rating")
fun bindRating(ratingBar: AppCompatRatingBar, rating: Double) {
    ratingBar.rating = rating.toFloat()
}
/*@BindingAdapter("text")
fun setText(view: TextView, status: Int) {
    view.text = status.toString()
}*/



