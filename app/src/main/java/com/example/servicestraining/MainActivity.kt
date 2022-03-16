package com.example.servicestraining

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.app.job.JobWorkItem
import android.content.ComponentName
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.servicestraining.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var page = 0

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
        binding.jobServiceBtn.setOnClickListener {
            val componentName = ComponentName(this,MyJobService::class.java)
            val jobInfo = JobInfo.Builder(MyJobService.JOB_ID,componentName)
                .setRequiresCharging(true)
                .build()
            val jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val intent =MyJobService.newIntent(page++)
                jobScheduler.enqueue(jobInfo, JobWorkItem(intent))
            }
        }

        binding.jobIntentServiceBtn.setOnClickListener {
            MyJobIntentService.enqueue(this,page++)
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