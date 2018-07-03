package com.example.akshayparmar.brodcastappdemo

import android.content.pm.PackageInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import kotlinx.android.synthetic.main.activity2_main.*
import kotlinx.android.synthetic.main.activity_main.*

class Main2Activity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2_main)

        var pkgName = applicationContext.packageName.toString()
        var label: String = applicationInfo.loadLabel(packageManager).toString()

        var pkgInfo: PackageInfo = packageManager.getPackageInfo(packageName, 0)
        var version:Int =pkgInfo.versionCode
        var version_name: String? = pkgInfo.versionName.toString()
        var version_os: String? = android.os.Build.VERSION.RELEASE

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        val height = displayMetrics.heightPixels
        var width = displayMetrics.widthPixels


        val deviceName = android.os.Build.MODEL

        textView2.text = "Package Name = "+ packageName +"\n"+
                "Label = " +label+"\n"+
                "Version = "+version+"\n"+
                "Version Name = "+version_name+"\n"+
                "Version Os = "+version_os+"\n"+
                "Device Name = "+deviceName+"\n"+
                "Height = "+height+"\n"+
                "Width = "+width


    }
}