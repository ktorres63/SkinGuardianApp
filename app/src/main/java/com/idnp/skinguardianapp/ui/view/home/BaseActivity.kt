package com.idnp.skinguardianapp.ui.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.idnp.skinguardianapp.R
import com.idnp.skinguardianapp.databinding.ActivityBaseBinding
import com.idnp.skinguardianapp.ui.view.login.RegisterUserFragment
import com.idnp.skinguardianapp.ui.view.profile.ProfileFragment
//import com.idnp.skinguardianapp.ui.view.profile.ProfileFragmentArgs

class BaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBaseBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initNavigation()

    }

    private fun initNavigation() {
        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHost.navController
        binding.bottomNavView.setupWithNavController(navController)

        val nameUser :String = intent.extras?.getString("nameUser").orEmpty()
        Log.i("Base?: ","hello $nameUser")


//        val action = BaseActivityDirections.actionBaseActivityToProfileFragment(nameUser)
//        val navController2 = findNavController(R.id.fragmentContainerView)
//        navController2.navigate(action)

//        val bundle = Bundle().apply {
//            putString("clave_string", "Hola desde la Activity")
//        }
//
//         // Crear una instancia del fragmento y asignarle el Bundle
//        val fragment = ProfileFragment()
//        fragment.arguments = bundle
//
//        // Iniciar la transacción para agregar el fragmento al contenedor
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragmentContainerView, fragment)
//            .commit()

    }
}