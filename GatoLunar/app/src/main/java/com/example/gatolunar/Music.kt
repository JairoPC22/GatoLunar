package com.example.gatolunar

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.button.MaterialButton

class Music : AppCompatActivity() {
    val fd by lazy {
        assets.openFd("relajante.mp3")
    }
    val mp by lazy {
        val m = MediaPlayer()
        m.setDataSource(
            fd.fileDescriptor,
            fd.startOffset,
            fd.length
        )
        fd.close()
        m.prepare()
        m
    }




    val icono by lazy {
        findViewById<Button>(R.id.botonpausa)

    }
    val icono2 by lazy {
        findViewById<Button>(R.id.botonrepro)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
        mp.start()



    }


    fun playClicked(v: View) {
        if (!mp.isPlaying) {
            mp.start()
            icono.visibility = View.VISIBLE
            icono2.visibility = View.INVISIBLE
            Toast.makeText(this, "Reproduciendo", Toast.LENGTH_SHORT).show()
        } else {
            mp.pause()
            icono.visibility = View.INVISIBLE
            icono2.visibility = View.VISIBLE
            Toast.makeText(this, "Pausa", Toast.LENGTH_SHORT).show()
        }
    }

    fun stopClicked(v: View) {
        if (!mp.isPlaying) {
            mp.pause()

        }
        mp.seekTo(0)
        mp.pause()
        Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show()


    }
    override fun onPause() {
        mp.pause()
        super.onPause()
    }
    override fun onRestart() {
        mp.start()
        super.onRestart()
    }
}