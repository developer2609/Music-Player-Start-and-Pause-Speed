package com.example.musicplayer

import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var runnable: Runnable
    private lateinit var handler: Handler

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val mediaplayer: MediaPlayer = MediaPlayer.create(this, R.raw.otile)

        sekbar.progress = 0
        sekbar.max = mediaplayer.duration

        buttun_start.setOnClickListener {
            mediaplayer.start()
        }
        buttun_pauze.setOnClickListener {
            mediaplayer.pause()
        }

        buttun_speed.setOnClickListener {
            if (mediaplayer.playbackParams.speed == 1.0f) {
                mediaplayer.playbackParams = mediaplayer.playbackParams.setSpeed(2.0f)
            }else{
                mediaplayer.playbackParams=mediaplayer.playbackParams.setSpeed(1.0f)
            }
        }

        sekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, changed: Boolean) {
                if (changed) {
                    mediaplayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })


        runnable = Runnable {


            sekbar.progress = mediaplayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }

        handler = Handler()
        handler.postDelayed(runnable, 1000)


    }
}