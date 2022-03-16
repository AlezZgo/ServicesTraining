package com.example.servicestraining

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.*

class MyWorker(context: Context,private val params: WorkerParameters): Worker(context,params) {
    override fun doWork(): Result {
        Log.d("loger","worker doWork")
        val page = params.inputData.getInt(PAGE,0)
        for(i in 0 until 5){
            Thread.sleep(1000)
            Log.d("loger","worker timer $i $page")
        }
        return Result.success()
    }

    companion object {
        const val PAGE = "page"
        const val NAME = "workName"

        fun makeRequest(page: Int): OneTimeWorkRequest{
            return OneTimeWorkRequestBuilder<MyWorker>().apply {
                setInputData(workDataOf(PAGE to page))
                makeConstraints()
            }.build()
        }

        private fun makeConstraints(): Constraints{
            return Constraints.Builder()
                .setRequiresCharging(true)
                .build()
        }
    }

}