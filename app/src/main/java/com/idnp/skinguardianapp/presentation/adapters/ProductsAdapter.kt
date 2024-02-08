package com.idnp.skinguardianapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.idnp.skinguardianapp.R
import com.idnp.skinguardianapp.domain.model.Product


class ProductsAdapter(private val productList: List<Product>,
) : RecyclerView.Adapter<ProductsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductsViewHolder(layoutInflater.inflate(R.layout.item_products,parent,false))
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val item = productList[position]
        holder.render(item)
    }
}