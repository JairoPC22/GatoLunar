package com.example.gatolunar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

class Comentario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comentario)

        val db : FirebaseFirestore = FirebaseFirestore.getInstance()
        val btn : Button = findViewById(R.id.buttonIngresar2)
        val consu : TextView = findViewById(R.id.textView15)

        btn.setOnClickListener{
            var datos = ""
            db.collection("comentarios")
                .get()
                .addOnSuccessListener { resultado ->
                    for(documento in resultado){
                        datos += "${documento.id}: ${documento.data}\n"
                    }
                    consu.text = datos
                }
                .addOnFailureListener { exception ->
                    consu.text = "No se pudo"
                }
        }

    }
    fun agregar(view: View){
        val intent = Intent(this, Agregar::class.java )
        startActivity(intent)
    }
}