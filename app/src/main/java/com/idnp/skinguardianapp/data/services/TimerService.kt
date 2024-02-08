package com.idnp.skinguardianapp.data.services

import com.idnp.skinguardianapp.presentation.view.login.LoginActivity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.idnp.skinguardianapp.data.broadcastReceivers.NotificationBroadcastReceiver

class TimerForegroundService : Service() {

    private var notificationManager: NotificationManager? = null
    private var timer: CountDownTimer? = null
    private var finalTimer: Long = 0L
    private var notificationBuilder: NotificationCompat.Builder? = null

    override fun onCreate() {
        super.onCreate()
        notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        processCommand(intent)
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private fun processCommand(intent: Intent?) {
        when (intent?.extras?.getString(COMMAND_ID) ?: INVALID) {
            COMMAND_START -> {
                finalTimer = intent?.extras?.getLong(STARTED_TIMER_TIME_MS) ?: return
                startTimer()
                startForegroundAndShowNotification()
            }
            INVALID -> return
        }
    }

    private fun startTimer() {
        timer?.cancel()
        timer = object : CountDownTimer(finalTimer, INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                updateNotification(millisUntilFinished.displayTime().dropLast(3))
            }

            override fun onFinish() {
                stopForegroundAndRemoveNotification()
                stopSelf()
            }
        }
        timer?.start()
    }

    private fun updateNotification(content: String) {
        notificationBuilder?.setContentText(content)
        notificationManager?.notify(NOTIFICATION_ID, notificationBuilder?.build())
    }

    private fun startForegroundAndShowNotification() {
        createChannel()
        val cancelIntent = Intent(
            this,
            NotificationBroadcastReceiver::class.java
        ).apply {
            action = CANCEL_ACTION
        }
        val cancelPendingIntent: PendingIntent =
            PendingIntent.getBroadcast(
                this,
                0,
                cancelIntent,
                PendingIntent.FLAG_IMMUTABLE
            )
        notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Temporizador")
            .setGroup("Temporizadores")
            .setGroupSummary(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setContentIntent(getPendingIntent())
            .setSmallIcon(android.R.drawable.alert_light_frame)
            .setOngoing(true)
            .addAction(android.R.drawable.ic_delete, "Cancelar", cancelPendingIntent)
        startForeground(NOTIFICATION_ID, notificationBuilder?.build())
    }

    private fun stopForegroundAndRemoveNotification() {
        stopForeground(STOP_FOREGROUND_REMOVE)
        notificationManager?.cancel(NOTIFICATION_ID)
    }

    private fun createChannel() {
        val channelName = "pomodoro"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val notificationChannel = NotificationChannel(
            CHANNEL_ID, channelName, importance
        )
        notificationManager?.createNotificationChannel(notificationChannel)
    }

    private fun getPendingIntent(): PendingIntent? {
        val resultIntent = Intent(this, LoginActivity::class.java)
        resultIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        return PendingIntent.getActivity(
            this,
            0,
            resultIntent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }

    companion object {

        private const val CHANNEL_ID = "Channel_ID"
        private const val NOTIFICATION_ID = 777
        private const val INTERVAL = 1000L

        const val START_TIME = "00:00:00:00"
        const val INVALID = "INVALID"
        const val COMMAND_START = "COMMAND_START"


        const val COMMAND_ID = "COMMAND_ID"
        const val STARTED_TIMER_TIME_MS = "STARTED_TIMER_TIME"

        const val CANCEL_ACTION = "com.idnp.skinguardianapp.data.broadcastReceivers.ACTION_CANCEL"
    }

    fun Long.displayTime(): String {
        if (this <= 0L) {
            return START_TIME
        }
        val h = this / 1000 / 3600
        val m = this / 1000 % 3600 / 60
        val s = this / 1000 % 60
        val ms = this % 1000 / 10

        return "${displaySlot(h)}:${displaySlot(m)}:${displaySlot(s)}:${displaySlot(ms)}"
    }

    private fun displaySlot(count: Long): String {
        return if (count / 10L > 0) {
            "$count"
        } else {
            "0$count"
        }
    }
}