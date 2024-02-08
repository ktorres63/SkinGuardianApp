package com.idnp.skinguardianapp.presentation.view.routines

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.idnp.skinguardianapp.R
import com.idnp.skinguardianapp.presentation.adapters.RoutinesAdapter
import com.idnp.skinguardianapp.domain.model.Routine
import com.idnp.skinguardianapp.databinding.FragmentRoutinesBinding


class RoutinesFragment : Fragment() {
    private var _binding: FragmentRoutinesBinding? = null
    private val binding get() = _binding!!
    private lateinit var  rvRoutines: RecyclerView
    private lateinit var routinesAdapter: RoutinesAdapter
    private lateinit var btnAddRoutine: AppCompatButton

    private val routines = mutableListOf(
        Routine("protector Solar","utilizar protector solar todos los dias"),
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
        routinesAdapter = RoutinesAdapter(routines){pos -> onItemSelected(pos)}
        rvRoutines.layoutManager = LinearLayoutManager(context)
        rvRoutines.adapter = routinesAdapter

    }

    private fun showDialog(){
        val dialog = Dialog(requireContext())

        dialog.setContentView(R.layout.dialog_routine)

        val btnAddRoutine: Button = dialog.findViewById(R.id.btnAddRoutine)
        val etRoutineTitle: EditText = dialog.findViewById(R.id.etRoutineTitle)
        val etRoutineDescription: EditText = dialog.findViewById(R.id.etRoutineDescription)
        btnAddRoutine.setOnClickListener {
            val currentRoutineTitle = etRoutineTitle.text.toString()
            val currentRoutineDesc = etRoutineDescription.text.toString()

            if(currentRoutineTitle.isNotEmpty() && currentRoutineDesc.isNotEmpty()){
                routines.add(Routine(etRoutineTitle.text.toString(),etRoutineDescription.text.toString()))
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