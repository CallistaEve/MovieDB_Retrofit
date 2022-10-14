package com.uc.week4retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uc.week4retrofit.R
import com.uc.week4retrofit.model.Genre
import com.uc.week4retrofit.model.SpokenLanguage

class LangAdapter(private val langList: List<SpokenLanguage>) :
    RecyclerView.Adapter<LangAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val langtv: TextView

        init {
            // Define click listener for the ViewHolder's View.
            langtv = view.findViewById(R.id.lang_tv)
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): LangAdapter.ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_lang, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: LangAdapter.ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.langtv.text = langList[position].name
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = langList.size
}