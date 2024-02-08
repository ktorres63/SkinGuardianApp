package com.idnp.skinguardianapp.presentation.view.login

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.idnp.skinguardianapp.R
import com.idnp.skinguardianapp.data.database.SkinGuardian
import com.idnp.skinguardianapp.domain.model.User
import com.idnp.skinguardianapp.databinding.ActivityLoginBinding
import com.idnp.skinguardianapp.presentation.view.home.BaseActivity
import com.idnp.skinguardianapp.presentation.viewModel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.idnp.skinguardianapp.data.services.LightSensorService

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(2000)
        installSplashScreen()

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

                val indexUserS = loginViewModel.getIndexUser()
                (application as SkinGuardian).userId = indexUserS

                Log.i("login->", indexUserS.toString())
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
