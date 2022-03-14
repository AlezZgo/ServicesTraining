package com.example.servicestraining

import android.app.Notification
import android.app.NotificationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
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
            showNotification()
        }
    }

    private fun showNotification() {
        val notification = Notification.Builder(this)
            .setContentTitle("Title")
            .setContentText("text")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1,notification)
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