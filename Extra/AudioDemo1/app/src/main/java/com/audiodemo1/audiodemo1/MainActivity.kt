package com.audiodemo1.audiodemo1

import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import java.util.jar.Attributes

class MainActivity : AppCompatActivity() {

    lateinit var soundPool: SoundPool
    lateinit var soundPoolBuilder: SoundPool.Builder

    lateinit var attributes: AudioAttributes
    lateinit var attributesBuilder: AudioAttributes.Builder
    lateinit var radioGroup: RadioGroup

    lateinit var audioManager: AudioManager

    lateinit var mediaPlayer: MediaPlayer

    var soundID: Int = 0

    lateinit var btn_play: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        var intent: Intent = Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
        startActivity(intent)

//        attributesBuilder = AudioAttributes.Builder()
//        attributesBuilder.setUsage(AudioAttributes.USAGE_GAME)
//        attributesBuilder.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
//        attributes = attributesBuilder.build()

//        soundPoolBuilder = SoundPool.Builder()
//      soundPoolBuilder.setAudioAttributes(attributes)
//        soundPool = soundPoolBuilder.build()
//        soundID = soundPool.load(this, R.raw.audio3, 1)



        btn_play = findViewById(R.id.button_play)
        btn_play.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                //soundPool.play(soundID, 1F,1F,0,0,1F)
                mediaPlayer = MediaPlayer.create(this@MainActivity, R.raw.audio4)
                mediaPlayer.start()

            }

        })

        radioGroup = findViewById(R.id.radioGroup_profile)
        radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {

                when(checkedId){
                    R.id.radioButton_normal -> audioManager.ringerMode = AudioManager.RINGER_MODE_NORMAL

                    R.id.radioButton_silent -> audioManager.ringerMode = AudioManager.RINGER_MODE_SILENT

                    R.id.radioButton_vibrate -> audioManager.ringerMode = AudioManager.RINGER_MODE_VIBRATE

                    else -> Toast.makeText(this@MainActivity, "Not Working", Toast.LENGTH_SHORT).show()
                }

            }

        })


    }
}
