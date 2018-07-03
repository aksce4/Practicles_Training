package com.example.akshay.infiniteloopdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Messenger
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var recievemsg: Messenger? = null
    var sendmsg:Messenger? = null
    lateinit var handler: Handler

    var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler = Handler()

        var i: Unit = Thread(Runnable {

            while (true){
                if(count<=99){
                    count++
                }else{
                    count = 0
                    count++
                }
                Thread.sleep(500)

                Log.i("Count",count.toString())
                handler.post(Runnable {
                    txt.text = count.toString()
                })
            }

        }).start()
    }
}
