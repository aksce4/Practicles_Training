 package com.facebookappdemo.facebookappdemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton

 class MainActivity : AppCompatActivity() {

     lateinit var text_name:TextView
     lateinit var login_button: LoginButton
     lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FacebookSdk.sdkInitialize(applicationContext)
        setContentView(R.layout.activity_main)

        //Intialization of the controls
        initializeControls()

        //LoginManager callback
        loginWithFB()

    }

     private fun initializeControls(){
         text_name = findViewById(R.id.text_name)
         login_button = findViewById(R.id.login_button)
         callbackManager = CallbackManager.Factory.create()

     }

     private fun loginWithFB(){
         LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult>{
             override fun onSuccess(result: LoginResult?) {
                text_name.setText("Login suuccessful\n" + result!!.accessToken)
             }

             override fun onCancel() {
                text_name.setText("Login Cancelled")
             }

             override fun onError(error: FacebookException?) {
                 text_name.setText("Login Error ${error!!.message}")
             }

         })
     }


     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         super.onActivityResult(requestCode, resultCode, data)
         callbackManager.onActivityResult(requestCode, resultCode, data)
     }




 }
