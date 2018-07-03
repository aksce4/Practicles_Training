package com.audiodemo1.audiodemo1

import android.content.Intent
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var btn_play: Button
    lateinit var btn_pause: Button
    lateinit var btn_stop: Button

    val TAG: String = "MainActivity"
 //   var player: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_play = findViewById(R.id.button_play)
        btn_play.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                Log.e(TAG, "setOnClickListener: on Start Click clicked")
//                if (player == null){
//                    player = MediaPlayer.create(this@MainActivity, R.raw.audio2)
//                }
//                player!!.start()

                var intent: Intent = Intent(this@MainActivity, MyService::class.java)
                startService(intent)
            }

        })

//        btn_pause = findViewById(R.id.button_pause)
//        btn_pause.setOnClickListener(object : View.OnClickListener{
//            override fun onClick(v: View?) {
////                if (player != null){
////                    player!!.pause()
////                }
//            }
//
//        })

        btn_stop = findViewById(R.id.button_stop)
        btn_stop.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                Log.e(TAG, "setOnClickListener: on Stop Click clicked")
//                if (player != null){
//                    player!!.release()
//                    player = null
//                    Toast.makeText(this@MainActivity, "MediaPlayer Released", Toast.LENGTH_SHORT).show()
//                }
                var intent: Intent = Intent(this@MainActivity, MyService::class.java)
                stopService(intent)
            }

        })






    }
}
