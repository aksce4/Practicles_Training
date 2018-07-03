package com.example.akshayparmar.practicle2customfont

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView

class customView: TextView {

    var msg: String? = null
    var textactivity: String? = null

    constructor(context: Context?) : super(context){
        intialize(null)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        intialize(attrs)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        intialize(attrs)
    }


    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes){
        intialize(attrs)
    }

    fun intialize(set: AttributeSet?){
       var typearr = context!!.obtainStyledAttributes(set, R.styleable.customView)
       msg = typearr.getString(R.styleable.customView_msg)
       typearr.recycle()
   }

    fun getMsges(): String?{
        return textactivity
    }

    fun setMsges(text: String){
        textactivity = text
    }

}