package com.example.servicestraining

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.servicestraining.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("loger", "main activity created")
        binding.serviceBtn.setOnClickListener {
            startService(MyService.newIntent(this))
        }

        binding.foregroundServiceBtn.setOnClickListener {
            ContextCompat.startForegroundService(
                this,
                MyForegroundService.newIntent(this))

        }
        binding.intentServiceBtn.setOnClickListener {
            ContextCompat.startForegroundService(
                this,
                MyIntentService.newIntent(this))

        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("loger", "main activity started")
    }

    override fun onResume() {
        super.onResume()
        Log.d("loger", "main activity resumed")
    }

    override fun onPause() {
        super.onPause()
        Log.d("loger", "main activity paused")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("loger", "main activity restarted")
    }

    override fun onStop() {
        super.onStop()
        Log.d("loger", "main activity stopped")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("loger", "main activity destroyed")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("loger", "main activity save Instance")
    }

}