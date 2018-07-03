package com.example.akshayparmar.brodcastappdemo

import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.widget.Button


class MainActivity : AppCompatActivity() {

  //  private lateinit var broadcastReceiver: BrodcastReceiver
  //      lateinit var localbrodcast: LocalBroadcastManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var button = findViewById<Button>(R.id.button)
//
//        broadcastReceiver = BrodcastReceiver()
//        localbrodcast = LocalBroadcastManager.getInstance(applicationContext)
//
//        button.setOnClickListener {
//            var intent: Intent = Intent()
//            intent.addCategory(Intent.CATEGORY_DEFAULT)
//            intent.setAction("my.custom.action.tag.fordemo")
//            //sendBroadcast(intent)
//            localbrodcast.sendBroadcast(intent)
//        }
    }

//    override fun onStart() {
//        super.onStart()
////        var intentFilter: IntentFilter = IntentFilter("android.provider.Telephony.SMS_RECEIVED")
////        registerReceiver(broadcastReceiver, intentFilter)
//    }
//
//    override fun onStop() {
//        super.onStop()
//        unregisterReceiver(broadcastReceiver)
//    }

}
