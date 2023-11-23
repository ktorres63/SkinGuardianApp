package com.idnp.skinguardianapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RoutineListActivity : AppCompatActivity() {
    private lateinit var  rvRoutines: RecyclerView
    private lateinit var routinesAdapter: RoutinesAdapter
    private lateinit var btnAddRoutine: AppCompatButton

    private val routines = mutableListOf(
        Routine("utlizar protector Solar"),
        Routine("Aplicar Hidratante"),
        Routine("Aplicar Dermolimpiador")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routine_list)

        initComponent()
        initUI()
        initListeners()
    }

    private fun initListeners() {
        btnAddRoutine.setOnClickListener {
            showDialog()
        }
    }

    private fun initUI(){
        routinesAdapter = RoutinesAdapter(routines){pos -> onItemSelected(pos)}
        rvRoutines.layoutManager = LinearLayoutManager(this)
        rvRoutines.adapter = routinesAdapter

    }
    private fun initComponent(){
        rvRoutines = findViewById(R.id.rvRoutines)
        btnAddRoutine = findViewById(R.id.btnNewRoutine)

    }
    private fun showDialog(){
        val dialog = Dialog(this)

        dialog.setContentView(R.layout.dialog_routine)

        val btnAddRoutine: Button = dialog.findViewById(R.id.btnAddRoutine)
        val etRoutine: EditText = dialog.findViewById(R.id.etRoutine)
        btnAddRoutine.setOnClickListener {
            val currentRoutine = etRoutine.text.toString()

            if(currentRoutine.isNotEmpty()){
                routines.add(Routine(etRoutine.text.toString()))
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


}