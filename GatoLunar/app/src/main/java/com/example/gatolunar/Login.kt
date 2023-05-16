package com.example.gatolunar

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Login : AppCompatActivity() {

    val fd by lazy {
        assets.openFd("Gatito.mp3")
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mp.start()
    }

    fun irMensaje(view: View){
        val intent = Intent(this, PruebaCom::class.java )
        startActivity(intent)
    }
    fun IrMas(view: View){
        val intent = Intent(this, Mas::class.java )
        startActivity(intent)
    }
    fun Adios(view: View){
        val intent = Intent(this, Salir::class.java )
        startActivity(intent)
    }
    fun Menu(view: View){
        val intent = Intent(this, Menu::class.java )
        startActivity(intent)
    }
    fun Videos(view: View){
        val intent = Intent(this, Videos::class.java )
        startActivity(intent)
    }
    fun Music(view: View){
        val intent = Intent(this, Music::class.java )
        startActivity(intent)
    }


}