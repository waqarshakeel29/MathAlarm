package com.waqar.mathalarm

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.IBinder
import android.util.Log

fun Activity.startStopwatchService() {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
        val intent = Intent(this, StopWatchServices::class.java)
        startService(intent)
    } else {
        val intent = Intent(this, StopWatchServices::class.java)
        startService(intent)
    }
}

fun Activity.stopStopwatchService() {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
        stopService(Intent(this, StopWatchServices::class.java))
    } else {
        val intent = Intent(this, StopWatchServices::class.java)
        stopService(intent)
//        unbindService(serviceConnection)
    }
}

val serviceConnection = object : ServiceConnection {
    override fun onServiceConnected(className: ComponentName, service: IBinder) {
        Log.d("TAG1","Connected")
    }

    override fun onServiceDisconnected(className: ComponentName) {
        Log.d("TAG1","Disconnected")
    }
}
