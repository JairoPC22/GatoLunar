package com.example.gatolunar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Salir : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salir)
    }
    fun IrPrue(view: View){
        val intent = Intent(this, MainActivity::class.java )
        startActivity(intent)
    }
}