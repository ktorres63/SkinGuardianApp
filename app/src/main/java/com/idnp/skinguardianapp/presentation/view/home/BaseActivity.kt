package com.idnp.skinguardianapp.presentation.view.home

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.idnp.skinguardianapp.R
import com.idnp.skinguardianapp.data.services.LightSensorService
import com.idnp.skinguardianapp.databinding.ActivityBaseBinding
import dagger.hilt.android.AndroidEntryPoint

//import com.idnp.skinguardianapp.ui.view.profile.ProfileFragmentArgs
@AndroidEntryPoint
class BaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBaseBinding
    private lateinit var navController: NavController
    private val PERMISSION_REQUEST_CODE = 123
    private var startTime = 0L
    private var uniqueNotificationId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (!checkPermissions()) {
                requestPermissions()
            }
        } else {
        }
        val serviceIntent = Intent(this, LightSensorService::class.java)
        startService(serviceIntent)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun checkPermissions(): Boolean {
        val permissionCheck = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.POST_NOTIFICATIONS
        )
        return permissionCheck == PackageManager.PERMISSION_GRANTED
    }
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.POST_NOTIFICATIONS),
            PERMISSION_REQUEST_CODE
        )
    }


    private fun initUI() {
        initNavigation()
    }

    private fun initNavigation() {

        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHost.navController
        binding.bottomNavView.setupWithNavController(navController)

    }

    override fun onStop() {
        super.onStop()
        detenerServicio(this)
    }
    fun detenerServicio(context: Context) {
        // Crea un Intent que haga referencia al servicio que deseas detener
        val intent = Intent(context, LightSensorService::class.java)
        // Detén el servicio utilizando el método stopService() y pasando el Intent
        context.stopService(intent)
    }
}