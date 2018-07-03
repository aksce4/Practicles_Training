package com.example.karshsoni.demoserviceproject

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.annotation.RequiresApi
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    open var handler: Handler = Handler(applicationContext.mainLooper)
    var TAG: String = "Main Activity"
    var isStarted: Boolean = false
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "In Main Activity")
        val intent: Intent = Intent(this, CountService::class.java)

//        handler.handleMessage()

        button.setOnClickListener {
            if (!isStarted){
                isStarted = true
                startService(intent)
            }else{
                intent.action = Intent.ACTION_USER_FOREGROUND
                Toast.makeText(this, "Already Started...", Toast.LENGTH_LONG).show()
            }
        }

        button2.setOnClickListener {
            if (isStarted){
                isStarted = false
                this.stopService(intent)
            }else{
                Toast.makeText(this, "Service is not started yet...", Toast.LENGTH_LONG).show()
            }
        }

    }


}
