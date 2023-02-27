package com.example.servicestraining

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.app.job.JobWorkItem
import android.content.ComponentName
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.os.HandlerCompat.postDelayed
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.servicestraining.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var page = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("loger", "main activity created")

        var mediaPlayer = MediaPlayer.create(this, R.raw.yellow_taxy)

        CoroutineScope(Dispatchers.Main).launch {
            while (true){
                if (mediaPlayer != null) {
                    val mCurrentPosition: Int = mediaPlayer.getCurrentPosition() / 1000
                    binding.seekBar.progress = mCurrentPosition
                }
                delay(1000)
            }

        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if(mediaPlayer != null && p2){
                    mediaPlayer.seekTo(p1 * 1000);
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })



        binding.startBtn.setOnClickListener {
            mediaPlayer.start()
        }

        binding.btnStop.setOnClickListener {
            mediaPlayer.pause()
        }

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
            val componentName = ComponentName(this, MyJobService::class.java)
            val jobInfo = JobInfo.Builder(MyJobService.JOB_ID, componentName)
                .setRequiresCharging(true)
                .build()
            val jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val intent = MyJobService.newIntent(page++)
                jobScheduler.enqueue(jobInfo, JobWorkItem(intent))
            }
        }

        binding.jobIntentServiceBtn.setOnClickListener {
            MyJobIntentService.enqueue(this, page++)
        }

        binding.workManagerBtn.setOnClickListener {
            val workManager = WorkManager.getInstance(applicationContext)
            workManager.enqueueUniqueWork(
                MyWorker.NAME,
                ExistingWorkPolicy.APPEND,
                MyWorker.makeRequest(page++)
            )
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