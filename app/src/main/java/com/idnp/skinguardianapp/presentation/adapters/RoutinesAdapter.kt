package com.idnp.skinguardianapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idnp.skinguardianapp.R
import com.idnp.skinguardianapp.domain.model.Routine

class RoutinesAdapter(var routines: List<Routine>, private val onRoutineSelected: (Int)-> Unit): RecyclerView.Adapter<RoutinesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutinesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_routine,parent,false)
        return RoutinesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return routines.size
    }

    override fun onBindViewHolder(holder: RoutinesViewHolder, position: Int) {
        holder.render(routines[position])
        holder.itemView.setOnClickListener { onRoutineSelected(position) }
    }
}










