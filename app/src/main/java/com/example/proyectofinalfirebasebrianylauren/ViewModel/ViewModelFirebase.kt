package com.example.proyectofinalfirebasebrianylauren.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.proyectofinalfirebasebrianylauren.Videojuego.Juegos
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

class ViewModelFirebase : ViewModel() {

    private val conexion = FirebaseFirestore.getInstance()

    private var _listaJuegos = MutableStateFlow(mutableListOf<Juegos>())
    val listaJuegos = _listaJuegos.asStateFlow()

    private var listenerInitialized = false
    private var listener: ListenerRegistration? = null

    fun crearListener() {
        if (!listenerInitialized) {
            listener = conexion.collection("Juego").addSnapshotListener { datos, error ->
                if (error == null) {
                    datos?.documentChanges?.forEach { cambio ->
                        when (cambio.type) {
                            DocumentChange.Type.ADDED -> {
                                val jueguito1 = cambio.document.toObject<Juegos>().apply {
                                    idJuego = cambio.document.id
                                    // Asigna otros atributos si es necesario
                                }
                                _listaJuegos.value.add(jueguito1)
                            }
                            DocumentChange.Type.MODIFIED -> {
                                val jueguito2 = cambio.document.toObject<Juegos>()
                                _listaJuegos.value[cambio.newIndex] = jueguito2
                            }
                            DocumentChange.Type.REMOVED -> {
                                val jueguito3 = cambio.document.toObject<Juegos>()
                                _listaJuegos.value.remove(jueguito3)
                            }
                        }
                    }
                }
            }

            listenerInitialized = true
        }
    }

     suspend fun anyadirJuego(nombre: String, plataforma: String, tipo: String, imagenes: Int, valoracion: Int) {
        val newJuego = Juegos(nombre, plataforma, tipo, imagenes, valoracion)

        // Utiliza el contexto de suspensión
        try {
            val documentReference = conexion.collection("Juego").add(newJuego).await()
            val idJuego = documentReference.id

            // Actualiza el campo idJuego con el valor del documento ID recién generado
            conexion.collection("Juego").document(idJuego).update("idJuego", idJuego).await()
        } catch (e: Exception) {
            // Manejar excepciones si es necesario
            Log.e("ViewModelFirebase", "Error al añadir juego: ${e.message}")
        }
    }

    fun borrarJuego(nombreJuego: String) {
        // Realizar una consulta para obtener el ID del juego por su nombre
        val query = conexion.collection("Juego").whereEqualTo("nombre", nombreJuego)
        query.get().addOnSuccessListener { documents ->
            for (document in documents) {
                // Borrar el juego por su ID
                conexion.collection("Juego").document(document.id).delete()

                // Actualizar la lista local eliminando el juego
                _listaJuegos.value = _listaJuegos.value.filter { it.nombre != nombreJuego }.toMutableList()
            }
        }.addOnFailureListener { exception ->
            Log.w("ViewModelFirebase", "Error obteniendo el ID del juego", exception)
        }
    }



    override fun onCleared() {
        super.onCleared()
        // Eliminar el listener al desvincular el ViewModel
        listener?.remove()
    }
}