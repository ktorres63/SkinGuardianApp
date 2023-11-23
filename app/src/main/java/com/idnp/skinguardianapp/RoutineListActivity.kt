package com.idnp.skinguardianapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView

class RoutineListActivity : AppCompatActivity() {
    private lateinit var  rvRoutines: RecyclerView
    private lateinit var routinesAdapter: RoutinesAdapter

    private lateinit var btnAddRoutine: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routine_list)

        initComponent()
        initUI()
    }

    private fun initUI(){

    }
    private fun initComponent(){
        rvRoutines = findViewById(R.id.rvRoutines)
        btnAddRoutine = findViewById(R.id.btnNewRoutine)

    }
    private fun showDialog(){
        val dialog = Dialog(this)

        dialog.setContentView(R.layout.dialog_routine)
        

    }



}