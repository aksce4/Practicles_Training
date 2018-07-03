package com.example.akshayparmar.brodcastappdemo

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.CountDownTimer
import android.telephony.SmsMessage
import android.widget.Toast


open class BrodcastReceiver : BroadcastReceiver() {
    lateinit var msgText: String

    override fun onReceive(context: Context?, intent: Intent?) {
       // Toast.makeText(context, "Brodcast Receiver Triggered", Toast.LENGTH_LONG).show()
        val data = intent!!.extras
        val msg = "Alaram set"

        if (data!=null){
            val sms = data.get("pdus") as Array<*>

            for (i in sms.indices ){
                val format = data.get("format")
                var smsMsg = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    SmsMessage.createFromPdu(sms[i] as ByteArray, format as String)
                }else{
                    SmsMessage.createFromPdu(sms[i] as ByteArray)
                }
                val phoneNo = smsMsg.originatingAddress
                msgText = smsMsg.messageBody.toString()

                Toast.makeText(context, "+"+msgText, Toast.LENGTH_LONG).show()
            }

        }

        if (msgText == msg){
           alarmSet(10000, context)
        }
    }

    fun alarmSet(timer: Long, context: Context?){
        val service = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val i = Intent(context, AlaramNotification::class.java)


        val pintent = PendingIntent.getBroadcast(context, 0, i, 0)

        //Alaram which is repeated in certain interval
        service.setRepeating(AlarmManager.RTC, timer, 5000000, pintent)

        Toast.makeText(context, "Alaram set", Toast.LENGTH_LONG).show()
    }
}
