package com.waqar.mathalarm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.*
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import android.app.PendingIntent


class StopWatchServices : Service() {

    var builder : NotificationCompat.Builder? = null

    var startTime = 0L
    var myHandler = Handler()
    var timeInMillies = 0L
    var timeSwap = 0L
    var finalTime = 0L
    var notificationtime = "00:00"

    var time : String = "00:00:00"
//    var number : Int = 0
    lateinit var mContext: Context
    private val binder = StopwatchServiceBinder()

    var updateTimerMethod : Runnable = object : Runnable {

        override fun run() {
            timeInMillies = SystemClock.uptimeMillis() - startTime
            finalTime = timeSwap + timeInMillies


            var seconds = (finalTime / 1000).toInt()
            var minutes = seconds / 60
            var hours   =  minutes /60
            seconds = seconds % 60
            minutes = minutes % 60
            hours = hours % 60
            //Send BroadCast
            var intent = Intent("Stopwatch")

            notificationtime = String.format("%02d", minutes)+ ":" + String.format("%02d", seconds)
            time = String.format("%02d", minutes)+ ":" + String.format("%02d", seconds) + ":"+ String.format("%02d", finalTime%60)
                intent.putExtra("TIME",time)
//                intent.putExtra("NUMBER",number)

            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent)



            myHandler.postDelayed(this, 0)


            ///To change Text/ properties of Running notification
            if(builder != null) {
                val manager = mContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                builder!!.setContentTitle("Timer")
                builder!!.setContentText(notificationtime)
                manager.notify(432, builder!!.build())
            }
        }
    };


    override fun onCreate() {
        super.onCreate()

        mContext = this

        Log.d("TAG1", "ON CREATE")
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //            val notification = getNotification()
            val notification = GenerateNotification("")
            if (notification != null) {
                startForeground(432, notification)
            }
//        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    fun GenerateNotification(Message: String): Notification? {
        try {


            val contentIntent = PendingIntent.getActivity(this, 0,
                Intent(this, MainActivity::class.java), 0)

            builder = NotificationCompat.Builder(this, "com.waqar.mathalarm")

            builder!!.setContentTitle(resources.getString(R.string.app_name))
                .setContentText(Message)
                .setAutoCancel(false)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_launcher_foreground))
                .setOngoing(true)
                .setOnlyAlertOnce(true)
                .setContentIntent(contentIntent)
                .setVibrate(null).priority = Notification.PRIORITY_MAX

            val manager = this.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = resources.getString(R.string.app_name)
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel("com.waqar.mathalarm", name, importance)
                channel.description = Message
                channel.setShowBadge(false)
                manager.createNotificationChannel(channel)
            }

            builder!!.setAutoCancel(false)
            val notification = builder!!.build()
//                        manager.notify(432,GenerateNotification(time))
            return notification
        } catch (e: Exception) {

            Log.d("Tag", "GenerateNotification Exp:\n" + e.message)

            return null
        }

    }


    override fun onBind(intent: Intent): IBinder? {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null) {

//            number = intent.getIntExtra("NUMBER",0)
            startTime = SystemClock.uptimeMillis()
            myHandler.postDelayed(updateTimerMethod, 0)


        }
        /* We want this service to continue running until it is explicitly
        * stopped, so return sticky.
        */
        return Service.START_STICKY
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)

        Log.d("TAG1","UNBIND")

        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG1","Service destroyed")

        val manager = mContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.cancel(432)
        stop()
    }


    fun stop() : String?{
//                timeSwap += timeInMillies
        myHandler.removeCallbacks(updateTimerMethod)
        return ""
    }

    inner class StopwatchServiceBinder : Binder() {
        val service: StopWatchServices
            get() = this@StopWatchServices
    }





}