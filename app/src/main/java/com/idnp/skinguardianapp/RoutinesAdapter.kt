package com.idnp.skinguardianapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

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










