package com.example.servicestraining

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.os.Build
import android.util.Log
import kotlinx.coroutines.*

class MyJobService : JobService() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        Log.d("loger", "JobService onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("loger", "JobService onDestroy")
        coroutineScope.cancel()
    }


    override fun onStartJob(parameters: JobParameters?): Boolean {
        Log.d("loger", "JobService startJob")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            coroutineScope.launch {
                var workItem = parameters?.dequeueWork()
                while (workItem != null) {
                    val page = workItem.intent?.getIntExtra(PAGE, 0)

                    for (i in 0..8) {
                        delay(1000)
                        Log.d("loger", "JobService in $page timer $i sec")
                    }
                    parameters?.completeWork(workItem)
                    workItem = parameters?.dequeueWork()
                }
            }
        }
        jobFinished(parameters, true)

        return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        Log.d("loger", "JobService stopped")
        return true
    }

    companion object {
        const val JOB_ID = 11
        const val PAGE = "page"

        fun newIntent(page: Int): Intent {
            return Intent().apply {
                putExtra(PAGE, page)
            }
        }
    }

}