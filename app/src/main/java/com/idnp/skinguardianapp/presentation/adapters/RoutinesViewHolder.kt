package com.idnp.skinguardianapp.presentation.adapters
import android.graphics.Paint
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.idnp.skinguardianapp.R
import com.idnp.skinguardianapp.databinding.ItemRoutineBinding
import com.idnp.skinguardianapp.domain.model.Routine

class RoutinesViewHolder(
    view: View
): RecyclerView.ViewHolder(view) {
    private val binding = ItemRoutineBinding.bind(view)
    private val tvRoutineTitle = binding.tvRoutineTitle
    private val tvRoutineDesc = binding.tvRoutineDescription


    fun render(
        routine: Routine,
        position: Int,
        onCheckBoxClicked: (Int) -> Unit,
        onButtonClick: (Long) -> Unit
    ){
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

        if(routine.tieneTemporizador){

            binding.tiempoTemporizador.text = convertirMilisegundosAHorasMinutosSegundos(routine.tiempoTemporizador)
            binding.button.setOnClickListener{onButtonClick(routine.tiempoTemporizador)}
            binding.cbRoutine.setOnClickListener{onCheckBoxClicked(position)}
        }else{
            binding.temporizadorContainer.visibility = View.GONE
        }

    }

    fun convertirMilisegundosAHorasMinutosSegundos(milisegundos: Long): String {
        val horas = (milisegundos / (1000 * 60 * 60)) % 24
        val minutos = (milisegundos / (1000 * 60)) % 60
        val segundos = (milisegundos / 1000) % 60

        return String.format("%02d:%02d:%02d", horas, minutos, segundos)
    }
}