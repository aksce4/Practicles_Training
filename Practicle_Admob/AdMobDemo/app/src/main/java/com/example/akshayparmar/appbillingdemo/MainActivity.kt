package com.example.akshayparmar.appbillingdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this, "ca-app-pub-8383100298434911~8011041526")

        //For Banner ADD
//        var adRequest: AdRequest = AdRequest.Builder().build()
//        adView.loadAd(adRequest)

        var mInterstitialAd: InterstitialAd = InterstitialAd(this)

        mInterstitialAd.adUnitId = "ca-app-pub-8383100298434911/8749408120"
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        button.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                if(mInterstitialAd.isLoaded){
                    mInterstitialAd.show()
                }else{
                    Log.e("TAG","Ad not loaded")
                }
            }

        })
    }
}
