package com.example.servicestraining

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class MyService : Service() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        Log.d("loger","service onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("loger","service startCommand")
        coroutineScope.launch {
            for(i in 0 .. 8){
               delay(1000)
                Log.d("loger","timer $i sec")
            }
        }

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("loger","service onDestroy")
        coroutineScope.cancel()
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    companion object{
        fun newIntent(context: Context): Intent{
            return Intent(context,MyService::class.java)
        }
    }
}