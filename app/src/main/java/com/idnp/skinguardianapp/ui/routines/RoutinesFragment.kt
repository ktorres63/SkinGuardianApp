package com.idnp.skinguardianapp.ui.routines

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentResultListener
import com.idnp.skinguardianapp.R
import com.idnp.skinguardianapp.databinding.FragmentRoutinesBinding


class RoutinesFragment : Fragment() {
    private var _binding: FragmentRoutinesBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentRoutinesBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
}