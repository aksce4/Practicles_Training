package com.twitterappdemo.twitterappdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.identity.TwitterLoginButton

class MainActivity : AppCompatActivity() {

    lateinit var loginButton:TwitterLoginButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Twitter.initialize(this)
        setContentView(R.layout.activity_main)

        loginButton = findViewById(R.id.login_button)

        loginButton.callback(object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun failure(exception: TwitterException?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })




    }
}

