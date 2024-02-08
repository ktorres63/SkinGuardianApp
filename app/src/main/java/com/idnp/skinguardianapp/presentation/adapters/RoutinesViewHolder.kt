package com.idnp.skinguardianapp.presentation.adapters

import android.graphics.Paint
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.idnp.skinguardianapp.R
import com.idnp.skinguardianapp.domain.model.Routine
import com.idnp.skinguardianapp.databinding.ItemRoutineBinding

class RoutinesViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemRoutineBinding.bind(view)
    private val tvRoutineTitle = binding.tvRoutineTitle
    private val tvRoutineDesc = binding.tvRoutineDescription

    fun render(routine: Routine){
        tvRoutineTitle.text = routine.title
        tvRoutineDesc.text = routine.Description
        binding.cbRoutine.isChecked = routine.isSelected

        if(routine.isSelected){
            tvRoutineTitle.paintFlags = tvRoutineTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            tvRoutineDesc.paintFlags = tvRoutineDesc.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            Log.i("checkbox Checked", "ON")

        }
        else{
            tvRoutineTitle.paintFlags = tvRoutineTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            tvRoutineDesc.paintFlags = tvRoutineDesc.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }


    }

}