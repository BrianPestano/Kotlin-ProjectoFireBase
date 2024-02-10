package com.example.proyectofinalfirebasebrianylauren.ViewModel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth

//TODO ESTO DE AQUI ES DEL AUTENTICADOR PARA INICIO Y REGISTRO
class ViewModelLogin() : ViewModel() {

    // [START declare_auth]
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    // [END declare_auth]

     fun createAccount(email: String, password: String,navController: NavHostController) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    navController.navigate("pantallaLogin")
                } else {
                    println(task.exception)
                    println("Introduzca bien sus datos como se le pide")
                }
            }
        // [END create_user_with_email]
    }

     fun signIn(email: String, password: String,navController: NavHostController) {
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    navController.navigate("pantallaInicio")
                } else {
                    println(task.exception)
                    println("Introduzca bien sus datos como se le pide")
                }
            }
        // [END sign_in_with_email]
    }
}