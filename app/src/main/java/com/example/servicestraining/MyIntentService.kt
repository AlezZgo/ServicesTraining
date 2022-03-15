package com.example.servicestraining

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.*

class MyIntentService : IntentService(NAME) {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        startForeground(NOTIFICATION_ID,createNotification())
        Log.d("loger", "MyIntentService onCreate")
    }
    override fun onHandleIntent(p0: Intent?) {
        Log.d("loger", "MyIntentService handle intent")
        for (i in 0..8) {
            Thread.sleep(1000)
            Log.d("loger", "MyIntentService timer $i sec")
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d("loger", "MyIntentService onDestroy")
    }

    private fun createNotificationChannel() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val notificationChannel = NotificationChannel(
                CHANNEL_ID, CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun createNotification(): Notification =
        NotificationCompat.Builder(this, CHANNEL_ID)
             .setContentTitle("Title")
             .setContentText("text")
             .setSmallIcon(R.drawable.ic_launcher_foreground)
             .build()

    companion object {

        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_name"
        private const val NOTIFICATION_ID = 1
        private const val NAME = "MyIntentService"

        fun newIntent(context: Context): Intent {
            return Intent(context, MyIntentService::class.java)
        }
    }
}