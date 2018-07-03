package com.example.akshayparmar.brodcastappdemo

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi

internal class AlaramNotification: BrodcastReceiver(){
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder:Notification.Builder

    private var text = "test"
    private var chid = "com.example.akshayparmar.brodcastappdemo"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {

        val notificationIntent = Intent(context, Main2Activity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        notificationManager = context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationChannel = NotificationChannel(chid, text, NotificationManager.IMPORTANCE_HIGH)
        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.GREEN
        notificationChannel.enableVibration(false)
        notificationManager.createNotificationChannel(notificationChannel)

        builder = Notification.Builder(context,chid)
                .setContentTitle("Alaram Ringing")
                .setContentText("Ringing")
                .setSmallIcon(R.drawable.notification_icon_background)
                .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.notification_icon_background))
                .setContentIntent(pendingIntent)

        notificationManager.notify(111,builder.build())


    }

}

