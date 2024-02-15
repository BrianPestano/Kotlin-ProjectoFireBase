package com.example.proyectofinalfirebasebrianylauren.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.proyectofinalfirebasebrianylauren.Videojuego.Juegos
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

class ViewModelFirebase : ViewModel() {

    private val conexion = FirebaseFirestore.getInstance()

    private var _listaJuegos = MutableStateFlow(mutableListOf<Juegos>())
    val listaJuegos = _listaJuegos.asStateFlow()

    // Nueva lista para almacenar juegos
    private var juegos = mutableListOf<Juegos>()

    private var listenerInitialized = false
    private var listener: ListenerRegistration? = null

    // Nuevo atributo para almacenar el juego seleccionado
    var juegoSeleccionado: Juegos? = null

    fun crearListener() {
        if (!listenerInitialized) {
            listener = conexion.collection("Juego").addSnapshotListener { datos, error ->
                if (error == null) {
                    datos?.documentChanges?.forEach { cambio ->
                        when (cambio.type) {
                            DocumentChange.Type.ADDED -> {
                                val jueguito1 = cambio.document.toObject<Juegos>().apply {
                                    idJuego = cambio.document.id
                                }
                                _listaJuegos.value.add(jueguito1)
                                juegos.add(jueguito1)
                            }
                            DocumentChange.Type.MODIFIED -> {
                                val jueguito2 = cambio.document.toObject<Juegos>()
                                _listaJuegos.value[cambio.newIndex] = jueguito2
                                juegos[cambio.newIndex] = jueguito2
                            }
                            DocumentChange.Type.REMOVED -> {
                                val jueguito3 = cambio.document.toObject<Juegos>()
                                _listaJuegos.value.remove(jueguito3)
                                juegos.remove(jueguito3)
                            }
                        }
                    }
                }
            }

            listenerInitialized = true
        }
    }

    suspend fun anyadirJuego(idJuego: String, nombre: String, plataforma: String, tipo: String, valoracion: Int, descripcion: String) {
        val newJuego = Juegos(idJuego, nombre, plataforma, tipo, valoracion, descripcion)

        try {
            val documentReference = conexion.collection("Juego").add(newJuego).await()
            val idJuego = documentReference.id

            // Actualiza el campo idJuego con el valor del documento ID recién generado
            conexion.collection("Juego").document(idJuego).update("idJuego", idJuego).await()

            // Agrega el nuevo juego a la lista local
            juegos.add(newJuego)
        } catch (e: Exception) {
            Log.e("ViewModelFirebase", "Error al añadir juego: ${e.message}")
        }
    }
    // Dentro de tu ViewModel
    fun asignarJuegoSeleccionado(juego: Juegos) {
        juegoSeleccionado = juego
    }

    suspend fun actualizarDescripcion(detalles: String) {
        try {
            val idJuego = juegoSeleccionado?.idJuego

            if (!idJuego.isNullOrEmpty()) {
                // Actualiza la descripción del juego utilizando el ID proporcionado
                conexion.collection("Juego").document(idJuego).update("descripcion", detalles).await()

                // Actualizar la lista local si es necesario
                val index = juegos.indexOfFirst { it.idJuego == idJuego }
                if (index != -1) {
                    juegos[index].descripcion = detalles
                    _listaJuegos.value = juegos.toMutableList()
                }
            } else {
                Log.w("ViewModelFirebase", "El ID del juego seleccionado es nulo o vacío.")
            }
        } catch (e: Exception) {
            Log.e("ViewModelFirebase", "Error al actualizar la descripción del juego: ${e.message}")
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
                juegos = juegos.filter { it.nombre != nombreJuego }.toMutableList()
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
