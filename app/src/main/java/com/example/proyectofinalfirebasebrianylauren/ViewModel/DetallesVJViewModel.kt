package com.example.proyectofinalfirebasebrianylauren.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyectofinalfirebasebrianylauren.PantallaDetalles.DetallesVJ
import com.example.proyectofinalfirebasebrianylauren.PantallaDetalles.obtenerDetallesVJ

class DetallesVJViewModel : ViewModel() {
    private val _detallesVJ = MutableLiveData<DetallesVJ?>()
    val detallesVJ: MutableLiveData<DetallesVJ?> get() = _detallesVJ

    fun cargarDetallesVJ(nombreVJ: String?) {
        _detallesVJ.value = obtenerDetallesVJ(nombreVJ)
    }
}