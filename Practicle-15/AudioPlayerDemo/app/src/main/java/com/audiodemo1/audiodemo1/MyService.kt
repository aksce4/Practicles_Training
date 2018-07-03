package com.audiodemo1.audiodemo1

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log

class MyService : Service(){

    var player: MediaPlayer? = null
    var TAG: String = "Service Activity"

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "onStartCommand: media player activity will start")
        if(player == null){
            player = MediaPlayer.create(this,R.raw.audio3)
        }
//        player!!.isLooping = true
        player!!.start()
        return START_STICKY
    }

    override fun onDestroy() {
        Log.e(TAG, "onStartCommand: media player activity will stop")
        super.onDestroy()
        player!!.stop()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }



}