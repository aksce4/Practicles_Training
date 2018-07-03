package com.example.karshsoni.demoserviceproject

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.support.annotation.RequiresApi
import android.util.Log

class CountService : Service() {


    lateinit var notificationManager: NotificationManager
    lateinit var notificationchannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId= "com.example.karshsoni.demoserviceproject"
    private val name="testNotification"

    companion object {
        var count:Int = 0
    }

    var TAG: String = "Count Service"
    var isStopped = false


    override fun onDestroy() {
        isStopped = true
        Log.i(TAG, "On Destroy")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.i(TAG, "OnStartCommand")
        Thread(Runnable {
            Log.i(TAG, "In Thread")
            while (!isStopped) {
                Thread.sleep(1000)
                if (count > 100) {
                    count = 0
                }
                else {
                    count++
                    shownotification()
                }
                Log.i(TAG, ": In thread -> " + count.toString())
            }
        }).start()

        Log.i(TAG, ": Out thread -> " + count.toString())
        return Service.START_NOT_STICKY
    }

    @SuppressLint("PrivateResource")
    @RequiresApi(Build.VERSION_CODES.O)
    fun shownotification(){

        val notificationIntent = Intent(this, Main2Activity::class.java)
        notificationIntent.putExtra("IntValue", count)
        val pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT )


        notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationchannel= NotificationChannel(channelId, name, NotificationManager.IMPORTANCE_HIGH)
        notificationchannel.enableLights(true)
        notificationchannel.lightColor =  Color.BLUE
        notificationchannel.enableVibration(false)
        notificationManager.createNotificationChannel(notificationchannel)

        builder= Notification.Builder(this,channelId)
                .setContentTitle(count.toString())
                .setContentText("testNotification")
                .setSmallIcon(R.drawable.notification_icon_background)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.notification_icon_background))
                .setContentIntent(pendingIntent)

        notificationManager.notify(9999, builder.build())
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        Log.i(TAG, "On Task Removed")
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}
