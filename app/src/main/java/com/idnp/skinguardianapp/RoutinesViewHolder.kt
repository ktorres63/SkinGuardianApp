package com.idnp.skinguardianapp

import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RoutinesViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val tvRoutine: TextView = view.findViewById(R.id.tvRoutine)
    private val cbRoutine: CheckBox = view.findViewById(R.id.cbRoutine)

    fun render(routine: Routine){
        if(routine.isSelected){
            tvRoutine.paintFlags = tvRoutine.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
        else{
            tvRoutine.paintFlags = tvRoutine.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
        tvRoutine.text = routine.name
        cbRoutine.isChecked = routine.isSelected
    }

}