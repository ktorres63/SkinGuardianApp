package com.idnp.skinguardianapp.presentation.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.idnp.skinguardianapp.databinding.ItemProductsBinding
import com.idnp.skinguardianapp.domain.model.Product

class ProductsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemProductsBinding.bind(view)

    private val photo = binding.ivProductIMG

    fun render(productModel: Product) {
        binding.tvProductTitle.text = productModel.title
        binding.tvProductDescription.text = productModel.description
        Glide.with(photo.context).load(productModel.photo).into(photo)

    }
}