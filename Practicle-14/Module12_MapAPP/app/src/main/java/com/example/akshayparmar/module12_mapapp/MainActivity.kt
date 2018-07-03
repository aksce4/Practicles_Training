package com.example.akshayparmar.module12_mapapp

import android.app.Dialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.maps.OnMapReadyCallback

class MainActivity : AppCompatActivity(){

    val TAG: String = "MainActivity"
    val ERROR_DIALOG_REQUEST: Int = 8778

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (isServiceOK()){
           init()
        }

    }

     private fun init(){
         var btn = findViewById<Button>(R.id.button)
         btn.setOnClickListener(){
             var intent: Intent = Intent(this, MapActivity::class.java )
             startActivity(intent)
         }
     }

    fun isServiceOK(): Boolean{
        Log.d(TAG,"isServiceOk: checking google services version")

        var available: Int = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this)

        if (available == ConnectionResult.SUCCESS){
            Log.d(TAG,"isServiceOk: Google Play Services is Working")
            return true
        }else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "isServiceok: an error occured but we can fix it")
            var dialog: Dialog = GoogleApiAvailability.getInstance().getErrorDialog(this, available, ERROR_DIALOG_REQUEST)
            dialog.show()
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_LONG).show()
        }
        return false
    }
}
