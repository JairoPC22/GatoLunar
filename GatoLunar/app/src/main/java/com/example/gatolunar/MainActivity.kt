package com.example.gatolunar


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(androidx.appcompat.R.style.Theme_AppCompat_DayNight_NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonIngresar : Button = findViewById(R.id.buttonIngresar)
        val editTextTextEmailAddress : TextView = findViewById(R.id.editTextTextEmailAddress)
        val editTextTextPassword : TextView = findViewById(R.id.editTextTextPassword)

        firebaseAuth = Firebase.auth
        buttonIngresar.setOnClickListener()
        {if (editTextTextEmailAddress.text.isNotBlank() && editTextTextPassword.text.isNotBlank()) {
            signIn(editTextTextEmailAddress.text.toString(), editTextTextPassword.text.toString())
        }

        }

        }
    fun irCuenta(view: View) {
        val intent = Intent(this, CrearCuenta::class.java)
        startActivity(intent)
    }
    fun irOlvide(view: View) {
        val intent = Intent(this, OlvideContra::class.java)
        startActivity(intent)
    }
        private fun signIn(email: String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener (this){
            task ->
            if(task.isSuccessful){
                val user = firebaseAuth.currentUser
                Toast.makeText(baseContext,"Exito", Toast.LENGTH_SHORT).show()
                //Aqui vamos a ir a la segunda Actividad
                val i = Intent(this, Login::class.java)
                startActivity(i)
            }
            else{
                Toast.makeText(baseContext,"Error de Email y/o Contraseña", Toast.LENGTH_SHORT).show()

            }
        }
}
}
