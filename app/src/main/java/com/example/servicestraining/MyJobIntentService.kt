package com.example.servicestraining

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.JobIntentService
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.*

class MyJobIntentService : JobIntentService() {

    override fun onCreate() {
        super.onCreate()
        Log.d("loger", "MyJobIntentService onCreate")
    }

    override fun onHandleWork(intent: Intent) {
        Log.d("loger", "MyJobIntentService handle intent")
        val page = intent.getIntExtra(PAGE,0)
        for (i in 0..8) {
            Thread.sleep(1000)
            Log.d("loger", "MyJobIntentService $page timer $i sec")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("loger", "MyJobIntentService onDestroy")
    }

    companion object {

        const val PAGE = "page"
        const val JOB_ID = 12

        fun enqueue(context: Context,page: Int){
            JobIntentService.enqueueWork(
                context,
                MyJobIntentService::class.java,
                JOB_ID,
                newIntent(context,page)
            )
        }

        private fun newIntent(context: Context,page: Int): Intent {
            return Intent(context, MyJobIntentService::class.java).apply {
                putExtra(PAGE,page)
            }
        }
    }
}