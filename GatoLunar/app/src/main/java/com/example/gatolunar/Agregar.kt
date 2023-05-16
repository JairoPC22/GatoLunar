package com.example.gatolunar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class Agregar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar)

        val db : FirebaseFirestore = FirebaseFirestore.getInstance()
        val coment : TextView = findViewById(R.id.editTextTextMultiLine)
        val user : TextView = findViewById(R.id.editTextTextPersonName)
        val bton : Button = findViewById(R.id.buttonIngresar9)
        val borrar : Button = findViewById(R.id.Borrar)
        val Modi : Button = findViewById(R.id.Modificar)



        bton.setOnClickListener{
            if(coment.text.isNotBlank()&&
                    user.text.isNotBlank())
            {

                val dato = hashMapOf(
                            "comentario" to coment.text.toString(),
                            "usuario" to user.text.toString()
                )
            db.collection("comentarios")
                .document(coment.text.toString())
                .set(dato)
                .addOnSuccessListener {_ ->
                    Toast.makeText(baseContext,"Listo", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {_ ->
                    Toast.makeText(baseContext,"Error ", Toast.LENGTH_SHORT).show()
                }
        }
            Modi.setOnClickListener{
                if(coment.text.isNotBlank()&&
                    user.text.isNotBlank())
                {

                    val dato = hashMapOf(
                        "comentario" to coment.text.toString(),
                        "usuario" to user.text.toString()
                    )
                    db.collection("comentarios")
                        .document(coment.text.toString())
                        .set(dato)
                        .addOnSuccessListener {_ ->
                            Toast.makeText(baseContext,"Listo", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {_ ->
                            Toast.makeText(baseContext,"Error ", Toast.LENGTH_SHORT).show()
                        }
                }
        borrar.setOnClickListener {
            if(user.text.isNotBlank()){
                db.collection("comentarios")
                    .document(coment.text.toString())
                    .delete()
                    .addOnSuccessListener { _->
                        Toast.makeText(baseContext,"Eliminado", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { _->
                        Toast.makeText(baseContext,"Error", Toast.LENGTH_SHORT).show()
                    }
            }
        }



    }
}}}