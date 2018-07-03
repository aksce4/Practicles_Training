package com.example.akshay.module3image

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.ListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import com.example.akshay.*

class MainActivity : AppCompatActivity() {
    var imglist1: ArrayList<Int> = ArrayList<Int>()
    var textnames: ArrayList<String> = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imglist1.add(R.drawable.logo1)
        imglist1.add(R.drawable.logo2)
        imglist1.add(R.drawable.logo1)
        imglist1.add(R.drawable.logo2)
        imglist1.add(R.drawable.logo1)
        imglist1.add(R.drawable.logo2)
        imglist1.add(R.drawable.logo1)
        imglist1.add(R.drawable.logo2)
        imglist1.add(R.drawable.logo1)
        imglist1.add(R.drawable.logo2)

        textnames.add("Name1")
        textnames.add("Name2")
        textnames.add("Name3")
        textnames.add("Name4")
        textnames.add("Name5")
        textnames.add("Name6")
        textnames.add("Name7")
        textnames.add("Name8")
        textnames.add("Name9")
        textnames.add("Name10")

        var lay1 = LinearLayoutManager(this)
        var listada = ListAdapter1(this, imglist1)
        list1.layoutManager = lay1
        list1.adapter = listada



    }
}
