package com.example.gatolunar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class OlvideContra : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_olvide_contra)

        val email : TextView = findViewById(R.id.editTextTextEmailAddress3)
        val botonR :Button = findViewById(R.id.buttonCrearCuenta2)

        botonR.setOnClickListener {
            sendPasswordResetEmail(email.text.toString())

        }
        firebaseAuth = Firebase.auth

    }
    private fun sendPasswordResetEmail(email:String){
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener(){task ->
                if (task.isSuccessful){
                    Toast.makeText(baseContext, "Exito se cambio :)", Toast.LENGTH_SHORT).show()

                }

                else{
                    Toast.makeText(baseContext, "Error :(", Toast.LENGTH_SHORT).show()
                }

            }
    }
}