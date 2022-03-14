package com.example.servicestraining

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.servicestraining.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("loger","main activity created")
        binding.serviceBtn.setOnClickListener {
            startService(MyService.newIntent(this))
        }

        binding.foregroundServiceBtn.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(MyForegroundService.newIntent(this))
            }
//            showNotification()
        }
    }

    private fun showNotification() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val notificationChannel = NotificationChannel(
                CHANNEL_ID,CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val notification = NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("Title")
            .setContentText("text")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()

        notificationManager.notify(1,notification)
    }

    companion object {
        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_name"
    }

    override fun onStart() {
        super.onStart()
        Log.d("loger","main activity started")
    }

    override fun onResume() {
        super.onResume()
        Log.d("loger","main activity resumed")
    }

    override fun onPause() {
        super.onPause()
        Log.d("loger","main activity paused")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("loger","main activity restarted")
    }

    override fun onStop() {
        super.onStop()
        Log.d("loger","main activity stopped")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("loger","main activity destroyed")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("loger","main activity save Instance")
    }

}