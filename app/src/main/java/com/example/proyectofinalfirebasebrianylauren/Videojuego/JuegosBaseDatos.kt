package com.example.proyectofinalfirebasebrianylauren.Videojuego

import com.google.firebase.firestore.PropertyName

data class Juegos(
    @get:PropertyName("idJuego")
    @set:PropertyName("idJuego")
    var idJuego: String = "",
    var nombre: String = "",
    var plataforma: String = "",
    var tipo: String = "",
    val valoracion: Int,
    var descripcion : String = ""
) {
    // Constructor sin argumentos requerido por Firestore
    constructor() : this("", "", "", "", 0,"")
}
