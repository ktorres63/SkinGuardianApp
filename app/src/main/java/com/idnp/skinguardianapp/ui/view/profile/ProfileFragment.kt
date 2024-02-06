package com.idnp.skinguardianapp.ui.view.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.idnp.skinguardianapp.R
import com.idnp.skinguardianapp.data.database.SkinGuardian
import com.idnp.skinguardianapp.databinding.FragmentProfileBinding
import com.idnp.skinguardianapp.ui.viewModel.LoginViewModel
import com.idnp.skinguardianapp.ui.viewModel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var idUser = (requireActivity().application as SkinGuardian).userId
        val name = profileViewModel.getUser(idUser).user
        binding.tvName.text =  name

        val newData = floatArrayOf(10f,10f,10f,70f)
        binding.cvPieChart.setData(newData)

        binding.btnCalculate.setOnClickListener {

            val pG1 = binding.etPercGrasa.text.toString()
            val pA2 = binding.etPercArrg.text.toString()
            val pL3 = binding.etPercLes.text.toString()
            val pU4 = binding.etPercUnif.text.toString()

            if (pG1.isNotEmpty() && pA2.isNotEmpty() && pL3.isNotEmpty() && pU4.isNotEmpty()) {
                val p1 = pG1.toFloat()
                val p2 = pA2.toFloat()
                val p3 = pL3.toFloat()
                val p4 = pU4.toFloat()

                val newData = floatArrayOf(p1, p2, p3, p4)
                binding.cvPieChart.setData(newData)
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