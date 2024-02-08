package com.idnp.skinguardianapp.data.services
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.idnp.skinguardianapp.presentation.view.login.LoginActivity

class TimerForegroundService : Service() {

    private var notificationManager: NotificationManager? = null
    private var countDownTimer: CountDownTimer? = null
    private var finalTimer: Long = 0L

    private val builder by lazy {
        NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Timer")
            .setGroup("Timer")
            .setGroupSummary(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(getPendingIntent())
            .setSmallIcon(android.R.drawable.alert_light_frame)
            .setOngoing(false)
    }

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
                startTimer(finalTimer)
            }
            INVALID -> return
        }
    }

    private fun startTimer(timeInMillis: Long) {
        countDownTimer?.cancel()

        countDownTimer = object : CountDownTimer(timeInMillis, INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                updateNotification(millisUntilFinished)
            }

            override fun onFinish() {
                stopServiceAndNotification()
            }
        }.start()
    }

    private fun updateNotification(timeInMillis: Long) {
        notificationManager?.notify(
            NOTIFICATION_ID,
            getNotification(timeInMillis.displayTime().dropLast(3))
        )
    }

    private fun stopServiceAndNotification() {
        stopForeground(true)
        stopSelf()
    }

    private fun getNotification(content: String) = builder.setContentText(content).build()

    private fun getPendingIntent(): PendingIntent? {
        val resultIntent = Intent(this, LoginActivity::class.java)
        resultIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        return PendingIntent.getActivity(
            this,
            0,
            resultIntent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    companion object {
        private const val CHANNEL_ID = "Channel_ID"
        private const val NOTIFICATION_ID = 777
        private const val INTERVAL = 1000L
        const val STARTED_TIMER_TIME_MS = "STARTED_TIMER_TIME"
        const val INVALID = "INVALID"
        const val COMMAND_START = "COMMAND_START"
        const val COMMAND_ID = "COMMAND_ID"
    }

    fun Long.displayTime(): String {
        if (this <= 0L) {
            return "00:00:00:00"
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

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
        Log.d("Servicio", "OnDestroy: Deteniendo servicio")
    }
}