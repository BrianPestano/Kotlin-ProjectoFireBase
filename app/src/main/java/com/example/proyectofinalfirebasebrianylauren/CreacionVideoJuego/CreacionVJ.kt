package com.example.proyectofinalfirebasebrianylauren.CreacionVideoJuego

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
import androidx.navigation.NavHostController
import com.example.proyectofinalfirebasebrianylauren.ViewModel.ViewModelFirebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun pantallaCreacionVJ(navController: NavHostController) {
    // Declaración y inicialización de variables de estado con remember
    var nombre by remember { mutableStateOf("") }
    var tipoSeleccionado by remember { mutableStateOf("") }
    var puntuacion by remember { mutableStateOf(1) }
    var detalles by remember { mutableStateOf("") }
    var detallesConfirmados by remember { mutableStateOf<String?>(null) }

    // Lista de plataformas de videojuegos
    val plataformas = listOf("PS2", "PS3", "PS4", "PS5", "PC", "XB", "XB360", "XB1", "XBSS", "XBSX", "Android", "NSwitch", "IOS", "Wii")
    var buscador by remember { mutableStateOf("") }
    var seleccionPlataforma by remember { mutableStateOf("PC") }
    var menuDesplegado by remember { mutableStateOf(false) }

    // Lista de tipos de videojuegos
    val tipo = listOf("Accion", "RPG", "Aventura", "MMO")
    var viewModelF : ViewModelFirebase = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Texto para poner el nombre del juego
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del VideoJuego") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            )
        )
        //Menu desplegable para la plataforma
        SearchBar(
            query = buscador,
            onQueryChange = {
                buscador = it
                if (it.isEmpty()) {
                    seleccionPlataforma = ""
                }
            },
            onSearch = {
            },
            active = menuDesplegado,
            onActiveChange = { menuDesplegado = it }
        ) {
            // Filtrar plataformas según la búsqueda
            Column {
                plataformas.filter { plataforma -> plataforma.startsWith(buscador, ignoreCase = true) }
                    .forEach { plataforma ->
                        Log.d("pantallaCreacionVJ", "plataforma: $plataforma")
                        DropdownMenuItem(
                            onClick = {
                                seleccionPlataforma = plataforma
                                buscador = plataforma
                                menuDesplegado = false
                            },
                            text = { Text(text = plataforma) }
                        )
                    }
            }
        }
        // Grupo de opciones para los tipos de videojuegos
        Spacer(modifier = Modifier.height(16.dp))
        Text("Tipo(s):")
        tipo.forEach { tipo ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Checkbox(
                    checked = tipo in tipoSeleccionado,
                    onCheckedChange = {
                        tipoSeleccionado = tipo
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = tipo)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Slider para la valoración del videojuego
        Text("Valoracion: $puntuacion")
        Slider(
            value = puntuacion.toFloat(),
            onValueChange = { nuevaValoracion -> puntuacion = nuevaValoracion.toInt() },
            valueRange = 1f..100f,
            steps = 99
        )
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
        // Botón para confirmar los detalles
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
        // Botones de navegación
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = {
                // Lanzar una corrutina en el hilo principal
                viewModelF.viewModelScope.launch(Dispatchers.Main) {
                    // Llamar a la función suspendida anyadirJuego
                    viewModelF.anyadirJuego("",nombre, seleccionPlataforma, tipoSeleccionado, puntuacion, detalles)

                    navController.navigate("pantallaInicio")
                }
            }) {
                Icon(imageVector = Icons.Outlined.Check, contentDescription = "Save")
            }
            //Espaciador
            Spacer(modifier = Modifier.width(16.dp))
            // Botón para regresar (navegar hacia atrás)
            IconButton(onClick = {
                navController.navigate("pantallaInicio")
            }) {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Back")
            }
        }
    }
}