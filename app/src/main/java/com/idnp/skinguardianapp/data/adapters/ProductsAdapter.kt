package com.idnp.skinguardianapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.idnp.skinguardianapp.R

class ProductsAdapter(
    private val context: Context,
    private val productsTitles: List<String>,
    private val productsDescriptions: List<String>
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productName: TextView = view.findViewById(R.id.tvRoutineTitle)
        val productDescription: TextView = view.findViewById(R.id.tvRoutineDescription)
        val productImage: ShapeableImageView = view.findViewById(R.id.title_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_products, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Asignar el título y la descripción del producto
        holder.productName.text = context.getString(getTitleResource(position))
        holder.productDescription.text = context.getString(getDescriptionResource(position))
        // Asignar la imagen del producto
        val imageName = "imagen${position + 1}" // Imagen1, Imagen2, ...
        val resourceId = context.resources.getIdentifier(imageName, "drawable", context.packageName)
        holder.productImage.setImageResource(resourceId)
    }

    override fun getItemCount(): Int {
        return productsTitles.size
    }

    // Función auxiliar para obtener el ID del recurso de string del título del producto
    private fun getTitleResource(position: Int): Int {
        val resourceName = "titulo${position + 1}" // titulo1, titulo2, ...
        return context.resources.getIdentifier(resourceName, "string", context.packageName)
    }

    // Función auxiliar para obtener el ID del recurso de string de la descripción del producto
    private fun getDescriptionResource(position: Int): Int {
        val resourceName = "texto${position + 1}" // texto1, texto2, ...
        return context.resources.getIdentifier(resourceName, "string", context.packageName)
    }
}
