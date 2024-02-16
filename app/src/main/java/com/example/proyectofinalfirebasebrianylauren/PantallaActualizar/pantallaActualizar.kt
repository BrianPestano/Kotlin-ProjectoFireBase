package com.example.proyectofinalfirebasebrianylauren.PantallaActualizar

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyectofinalfirebasebrianylauren.ViewModel.ViewModelFirebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun pantallaDetalles(navController: NavController) {
    var viewModelF: ViewModelFirebase = viewModel()
    var detalles by remember { mutableStateOf("") }
    var detallesConfirmados by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Actualiza la plataforma del juego seleccionado:", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Cuadro de texto para los detalles del videojuego
        OutlinedTextField(
            value = detalles,
            onValueChange = { detalles = it },
            label = { Text("Detalles del VideoJuego") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            )
        )

        //confirmar los detalles
        Button(
            onClick = {
                detallesConfirmados = detalles
            },
            modifier = Modifier
                .align(Alignment.End)
        ) {
            Text("Confirmar Detalles")
        }

        // Mostrar los detalles confirmados
        detallesConfirmados?.let { detalles ->
            Spacer(modifier = Modifier.height(8.dp))
            Text("Detalles confirmados: $detalles", style = MaterialTheme.typography.titleMedium)
        }

        // Botones
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = {
                viewModelF.viewModelScope.launch(Dispatchers.Main) {
                    val juegoSeleccionado = viewModelF.juegoSeleccionado

                    if (juegoSeleccionado != null && juegoSeleccionado.idJuego.isNotEmpty()) {
                        viewModelF.actualizarDescripcion(detallesConfirmados ?: "")

                        navController.navigate("pantallaInicio")
                    } else {
                        Log.w("pantallaDetalles", "El juego seleccionado o su ID es nulo o vacío.")
                    }
                }
            }) {
                Icon(imageVector = Icons.Outlined.Check, contentDescription = "Actualizar")
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Botón para regresar a pantallaInicio
            IconButton(onClick = {
                navController.navigate("pantallaInicio")
            }) {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Back")
            }
        }
    }
}