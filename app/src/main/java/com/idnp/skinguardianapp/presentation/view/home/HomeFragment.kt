package com.idnp.skinguardianapp.presentation.view.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.idnp.skinguardianapp.R
import com.idnp.skinguardianapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newData = doubleArrayOf(0.10, 0.10, 0.10, 0.10, 0.10)
        binding.pentagonChart.setData(newData)

        binding.btnCalculate.setOnClickListener {

            val p1 = binding.sueno.text.toString()
            val p2 = binding.calidaddepiel.text.toString()
            val p3 = binding.alimentacion.text.toString()
            val p4 = binding.hidratacion.text.toString()
            val p5 = binding.estres.text.toString()

            if (p1.isNotEmpty() && p2.isNotEmpty() && p3.isNotEmpty() && p4.isNotEmpty() && p5.isNotEmpty()) {
                val p1 = p1.toDouble() / 100.0
                val p2 = p2.toDouble() / 100.0
                val p3 = p3.toDouble() / 100.0
                val p4 = p4.toDouble() / 100.0
                val p5 = p5.toDouble() / 100.0

                val newData = doubleArrayOf(p1, p2, p3, p4, p5)
                binding.pentagonChart.setData(newData)
            } else {
                Toast.makeText(
                    binding.btnCalculate.context,
                    "complete los campos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}