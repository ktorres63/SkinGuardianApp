package com.idnp.skinguardianapp.ui.view.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.idnp.skinguardianapp.R
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
        _binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val name:String = profileViewModel.getUser(0).toString()
//
//       Log.i("ProfileF",name)
//        val args = ProfileFragmentArgs.fromBundle(requireArguments())
//        val dataString = args.userName
//
//        binding.tvName.text = dataString
//        val miString = arguments?.getString("clave_string")
//        Log.i("Message","$miString")


    }

}