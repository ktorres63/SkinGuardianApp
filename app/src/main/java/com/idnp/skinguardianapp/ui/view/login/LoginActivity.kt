package com.idnp.skinguardianapp.ui.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView
import com.idnp.skinguardianapp.R
import com.idnp.skinguardianapp.data.model.User
import com.idnp.skinguardianapp.databinding.ActivityLoginBinding
import com.idnp.skinguardianapp.ui.view.home.BaseActivity
import com.idnp.skinguardianapp.ui.viewModel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
    }

    private fun initListeners() {
        binding.btnLogin.setOnClickListener {
            checkUserAccount()
        }
        binding.btnRegister.setOnClickListener {
            registerUser()
        }

    }

    private fun checkUserAccount() {

        if (binding.etUser.text.isNotEmpty() && binding.etPassw.text.isNotEmpty()) {
            val user = binding.etUser.text.toString()
            val passw = binding.etPassw.text.toString()

            val userObj = User(0, user, passw)
            if (loginViewModel.getSuccessfulLogin(userObj)) {
                val intent = Intent(this, BaseActivity::class.java)
                val nameUser:String = loginViewModel.getUser().user
                intent.putExtra("nameUser",nameUser)
                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    "Usuario o contraseña incorrectos",
                    Toast.LENGTH_SHORT
                ).show()
            }



        }
    }

    private fun registerUser() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, RegisterUserFragment())
        fragmentTransaction.commit()

        val lContainer: LinearLayout = findViewById(R.id.llContainer)
        lContainer.visibility = View.GONE

        findViewById<Button>(R.id.btnLogin).visibility = View.GONE
        findViewById<Button>(R.id.btnRegister).visibility = View.GONE
    }
}
