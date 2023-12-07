package com.idnp.skinguardianapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.idnp.skinguardianapp.databinding.ActivityLoginBinding
import com.idnp.skinguardianapp.ui.home.BaseActivity

class LoginActivity : AppCompatActivity() {

    // Declarar los EditText
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener(View.OnClickListener {
            val user: String = binding.etUser.text.toString()
            val passw: String = binding.etPassw.text.toString()

            if (user == "carlos" && passw == "1234") {
                val intent = Intent(this, BaseActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                    applicationContext,
                    "Usuario o contraseña incorrectos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}