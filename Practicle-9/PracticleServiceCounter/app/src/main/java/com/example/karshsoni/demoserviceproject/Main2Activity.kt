package com.example.karshsoni.demoserviceproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class Main2Activity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        onNewIntent(intent)

        var intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }

    override fun onNewIntent(intent: Intent?) {
        var count: Int = intent!!.extras["IntValue"] as Int
        CountService.count = count
    }
}
