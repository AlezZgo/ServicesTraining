package com.example.servicestraining

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.PersistableBundle
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
        val page = parameters?.extras?.getInt(PAGE)?:0
        coroutineScope.launch {
            for (i in 0..8) {
                delay(1000)
                Log.d("loger", "JobService in $page timer $i sec")
            }
            jobFinished(parameters,true)
        }
        return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        Log.d("loger", "JobService stopped")
        return true
    }

    companion object{
        const val JOB_ID = 11
        const val PAGE = "page"

        fun newBundle(page:Int): PersistableBundle{
            return PersistableBundle().apply {
                putInt(PAGE,page)
            }
        }
    }

}