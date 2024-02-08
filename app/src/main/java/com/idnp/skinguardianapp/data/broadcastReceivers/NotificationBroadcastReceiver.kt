package com.idnp.skinguardianapp.data.broadcastReceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.idnp.skinguardianapp.data.services.TimerForegroundService

class NotificationBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            TimerForegroundService.CANCEL_ACTION -> {
                val serviceIntent = Intent(context, TimerForegroundService::class.java)
                context.stopService(serviceIntent)
            }
        }
    }
}