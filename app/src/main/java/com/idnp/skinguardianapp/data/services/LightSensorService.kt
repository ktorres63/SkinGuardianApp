package com.idnp.skinguardianapp.data.services
import android.app.*
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.*
import android.util.Log
import androidx.core.app.NotificationCompat

class LightSensorService : Service(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var lightSensor: Sensor? = null
    private var maxLux: Float = 0f
    private var thresholdLux: Float = 20f // Umbral de luz deseado
    private var aboveThresholdDuration: Long = 0
    private val thresholdDurationMillis: Long = 3000 // 5 segundos

    companion object {
        private const val NOTIFICATION_CHANNEL_ID = "LightSensorServiceChannel"
        private const val NOTIFICATION_ID = 12345
    }

    override fun onCreate() {
        super.onCreate()
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        createNotificationChannel()
        startForeground(NOTIFICATION_ID, createNotification())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_GAME)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(this)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_LIGHT) {
            val lux = event.values[0]
            Log.d("Light", "Luz: $lux")
            if (lux > maxLux) {
                maxLux = lux
            }

            if (lux > thresholdLux) {
                aboveThresholdDuration += event.timestamp - (SystemClock.elapsedRealtimeNanos() / 1000000)

                Log.d("Light", "Luz tiempo: $aboveThresholdDuration")
                if (aboveThresholdDuration >= thresholdDurationMillis) {
                    showNotification("Max light detected", "No olvides aplicarte protector solar")
//                    stopSelf()
                }
            } else {
                aboveThresholdDuration = 0
            }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "Light Sensor Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setContentTitle("Light Sensor Service")
            .setContentText("Running in the background")
            .setSmallIcon(android.R.drawable.ic_menu_info_details)
            .build()
    }

    private fun showNotification(title: String, content: String) {
        val notification = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .build()
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(NOTIFICATION_ID, notification)
    }
}