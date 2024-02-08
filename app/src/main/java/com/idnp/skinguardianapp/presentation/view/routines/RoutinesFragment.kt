package com.idnp.skinguardianapp.presentation.view.routines

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.idnp.skinguardianapp.R
import com.idnp.skinguardianapp.data.services.TimerForegroundService
import com.idnp.skinguardianapp.presentation.adapters.RoutinesAdapter
import com.idnp.skinguardianapp.domain.model.Routine
import com.idnp.skinguardianapp.databinding.FragmentRoutinesBinding
import java.text.SimpleDateFormat
import java.util.TimeZone


class RoutinesFragment : Fragment() {
    private var _binding: FragmentRoutinesBinding? = null
    private val binding get() = _binding!!
    private lateinit var  rvRoutines: RecyclerView
    private lateinit var routinesAdapter: RoutinesAdapter
    private lateinit var btnAddRoutine: AppCompatButton

    private val routines = mutableListOf(
        Routine(
            "protector Solar",
            "utilizar protector solar todos los dias",
            true,
            30000L
        ),
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoutinesBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponent()
        initUI()
        initListeners()
    }
    private fun initComponent(){

        rvRoutines = binding.rvRoutines
        btnAddRoutine = binding.btnNewRoutine

    }
    private fun initListeners() {
        btnAddRoutine.setOnClickListener {
            showDialog()
        }
    }

    private fun initUI(){
        routinesAdapter = RoutinesAdapter(
            routines,
            {pos -> onItemSelected(pos)},
            {time ->
                val startIntent = Intent(context, TimerForegroundService::class.java)
                startIntent.putExtra(
                    TimerForegroundService.COMMAND_ID,
                    TimerForegroundService.COMMAND_START
                )
                startIntent.putExtra(TimerForegroundService.STARTED_TIMER_TIME_MS, time)
                context?.startService(startIntent)
            })
        rvRoutines.layoutManager = LinearLayoutManager(context)
        rvRoutines.adapter = routinesAdapter

    }


    var timeInMilliseconds:Long = 0;
    private fun showDialog(){
        val dialog = Dialog(requireContext())

        dialog.setContentView(R.layout.dialog_routine)

        val btnAddRoutine: Button = dialog.findViewById(R.id.btnAddRoutine)
        val etRoutineTitle: EditText = dialog.findViewById(R.id.etRoutineTitle)
        val etRoutineDescription: EditText = dialog.findViewById(R.id.etRoutineDescription)
        val editTextTime: EditText = dialog.findViewById(R.id.editTextTime)
        val checkBoxTemporizador: CheckBox = dialog.findViewById(R.id.checkBoxTemporizador)

        editTextTime.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                val timeString = editTextTime.text.toString()
                timeInMilliseconds = TextTimeToMilliseconds(timeString)

            }

        })


        btnAddRoutine.setOnClickListener {
            val currentRoutineTitle = etRoutineTitle.text.toString()
            val currentRoutineDesc = etRoutineDescription.text.toString()

            if(currentRoutineTitle.isNotEmpty() && currentRoutineDesc.isNotEmpty()){
                routines.add(Routine(
                    etRoutineTitle.text.toString(),
                    etRoutineDescription.text.toString(),
                    checkBoxTemporizador.isChecked,
                    timeInMilliseconds
                ))
                updateTasks()
                dialog.hide()

            }
        }
        dialog.show()

    }
    private fun onItemSelected(position:Int){
        routines[position].isSelected = !routines[position].isSelected
        updateTasks()
    }

    private fun updateTasks() {
        routinesAdapter.notifyDataSetChanged()
    }


    private fun TextTimeToMilliseconds(timeString: String): Long{

        try {
            val formato = SimpleDateFormat("HH:mm:ss")
            formato.isLenient = false // Esto hace que el parseo sea estricto
            formato.timeZone = TimeZone.getTimeZone("UTC")

            val date = formato.parse(timeString)
            date?.let {
                return it.time
            }
        } catch (e: Exception) {
            // Manejar cualquier error que pueda ocurrir durante el proceso de conversión
            println("Error al convertir el tiempo: ${e.message}")
        }

        return 0
    }
}