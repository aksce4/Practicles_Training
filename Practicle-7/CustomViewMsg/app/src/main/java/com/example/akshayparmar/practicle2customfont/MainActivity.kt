package com.example.akshayparmar.practicle2customfont

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var customview: customView? = null

        var font1: String = "fonts/stocky.ttf"
        var font2: String = "fonts/Shella.ttf"
        var font3: String = "fonts/Pacifico.ttf"
        var font4: String = "fonts/Kaush.otf"


        var mTypeFace1:Typeface = Typeface.createFromAsset(assets, font1)
        var mTypeFace2:Typeface = Typeface.createFromAsset(assets, font2)
        var mTypeFace3:Typeface = Typeface.createFromAsset(assets, font3)
        var mTypeface4:Typeface = Typeface.createFromAsset(assets, font4)

        customView1.typeface = mTypeFace1
        customView2.typeface = mTypeFace2
        customView3.typeface = mTypeFace3
        customView4.typeface = mTypeface4

        button.setOnClickListener({
            var text: String = editText.text.toString()
            customView1!!.text = text
            customView2!!.text = text
            customView3!!.text = text
            customView4!!.text = text
            customView5!!.text = text
            customView6!!.text = text
            Toast.makeText(this, customView3!!.getMsges(), Toast.LENGTH_LONG).show()
        })
    }

    fun onClick(view: View){
        var view1: customView = view as customView
        view1.setMsges(view.text as String)
        Toast.makeText(this, view1.getMsges(), Toast.LENGTH_LONG).show()
    }
}
