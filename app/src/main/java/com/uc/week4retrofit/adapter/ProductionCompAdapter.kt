package com.uc.week4retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uc.week4retrofit.R
import com.uc.week4retrofit.helper.Const
import com.uc.week4retrofit.model.ProductionCompany

class ProductionCompAdapter(private val productionList: List<ProductionCompany>) :
    RecyclerView.Adapter<ProductionCompAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageProComp: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            imageProComp = view.findViewById(R.id.pc_iv)
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ProductionCompAdapter.ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_productioncomp, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ProductionCompAdapter.ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        if (productionList[position].logo_path != "null") {
            Glide.with(viewHolder.itemView.context)
                .load(Const.IMG_URL + productionList[position].logo_path)
                .into(viewHolder.imageProComp)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = productionList.size
}
