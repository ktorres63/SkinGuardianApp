package com.idnp.skinguardianapp.presentation.view.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.idnp.skinguardianapp.domain.model.User
import com.idnp.skinguardianapp.databinding.FragmentRegisterUserBinding
import com.idnp.skinguardianapp.presentation.viewModel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterUserFragment : Fragment() {

    var _binding: FragmentRegisterUserBinding? = null
    private val binding get() = _binding!!
    private val registerViewModel: RegisterViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()

    }

    private fun initListeners(){
        binding.btnAdd.setOnClickListener {
            if(passwordMatch()){
                saveUser()
                backToLogin()
            }
        }
        binding.btnCancel.setOnClickListener{
            backToLogin()
        }
    }
    private fun passwordMatch(): Boolean {
        val pasw = binding.etPassword.text
        val paswC = binding.etPasswordConf.text
        val name = binding.etUsername

        if (binding.etPassword.text.toString() == binding.etPasswordConf.text.toString()) {
            return true
        }
        Toast.makeText(
            binding.etPassword.context,
            "las contraseñas no coinciden",
            Toast.LENGTH_SHORT
        ).show()

        return false
    }

    private fun saveUser() {
        if (binding.etUsername.text.isNotEmpty() && binding.etPassword.text.isNotEmpty()) {
            val name = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            val user = User(0, name, password)
            registerViewModel.insertUserInDatabase(user)
            Toast.makeText(binding.etPassword.context, "Usuario guardado", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(binding.etPassword.context, "Completa todos los campos", Toast.LENGTH_SHORT).show()
        }

    }

    private fun backToLogin() {
        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
    }

}
















