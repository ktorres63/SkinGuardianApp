package com.idnp.skinguardianapp.presentation.view.products

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast

class ConnectionModeReceiver() : BroadcastReceiver() {
    private var registered = false
    override fun onReceive(context: Context?, intent: Intent?) {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo

        val isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnected

        // Notificar a la aplicación sobre el cambio en la conexión a internet
        if (isConnected) {
            // La aplicación está conectada a internet
        } else {
            // La aplicación no está conectada a internet
        }
    }
    interface ConnectivityReceiverListener {
        fun onNetworkConnectionChanged(isConnected: Boolean)
    }
}