package com.uc.week4retrofit.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uc.week4retrofit.R
import com.uc.week4retrofit.model.Genre
import com.uc.week4retrofit.view.MovieDetailsActivity

class Genre_Adapter(private val genreList: List<Genre>) :
    RecyclerView.Adapter<Genre_Adapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val genretv: TextView

        init {
            // Define click listener for the ViewHolder's View.
        genretv = view.findViewById(R.id.genre_tv)
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Genre_Adapter.ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_genre, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: Genre_Adapter.ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.genretv.text = genreList[position].name
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = genreList.size
}