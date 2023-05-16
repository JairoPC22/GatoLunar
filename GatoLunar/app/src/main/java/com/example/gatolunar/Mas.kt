package com.example.gatolunar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class Mas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mas)

    }
    fun irComentario(view: View){
        val intent = Intent(this, PruebaCom::class.java )
        startActivity(intent)
    }
    fun Prin(view: View){
        val intent = Intent(this, Login::class.java )
        startActivity(intent)
    }

}