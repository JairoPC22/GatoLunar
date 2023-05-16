package com.example.gatolunar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CrearCuenta : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cuenta)

        val textnombre : TextView = findViewById(R.id.editNombre)
        val txtemail : TextView = findViewById(R.id.editEmailNuevo)
        val txtpassword1 : TextView = findViewById(R.id.editPassword1)
        val txtpassword2 : TextView = findViewById(R.id.editPasswordNuevo)
        val btnCrear : Button = findViewById(R.id.buttonCrearCuenta)
        btnCrear.setOnClickListener()
        {
            var pass1 = txtpassword1.text.toString()
            var pass2 = txtpassword2.text.toString()
            if(pass1.equals(pass2))
            {
                createAccount(txtemail.text.toString(), txtpassword1.text.toString())

            }
            else
            {
            Toast.makeText(baseContext, "Eror las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            txtpassword1.requestFocus()
            }

        }


        firebaseAuth = Firebase.auth

    }
    private fun createAccount(email: String, password:String)
    {
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){
                task ->
                if(task.isSuccessful)
                {
                    Toast.makeText(baseContext, "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java )
                        startActivity(intent)
                    }
                else
                {
                    Toast.makeText(baseContext, "Algo salió mal, Error :(" + task.exception, Toast.LENGTH_SHORT).show()

                }
                }
            }
    }
