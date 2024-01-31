package com.example.proyectofinalfirebasebrianylauren.CreacionVideoJuego

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyectofinalfirebasebrianylauren.PantallaPrincipal.lista
import com.example.proyectofinalfirebasebrianylauren.R
import com.example.proyectofinalfirebasebrianylauren.Videojuego.infoArray

import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun pantallaCreacionVJ(navController: NavHostController) {
    var nombre by remember { mutableStateOf("") }
    var fechaSeleccionado by remember { mutableStateOf("") }
    var puntuacion by remember { mutableStateOf(1) }
    val plataformas = listOf("PS2", "PS3", "PS4", "PS5", "PC", "XB", "XB360", "XB1", "XBSS", "XBSX", "Android", "NSwitch", "IOS", "Wii")
    var buscador by remember { mutableStateOf("") }
    var seleccionPlataforma by remember { mutableStateOf<String>("PC") }
    var menuDesplegado by remember { mutableStateOf(false) }
    val tipo = listOf("Accion", "RPG", "Aventura", "MMO")
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Cuadro de texto para el nombre
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
        // Cuadro de texto y menú desplegable para la región
        SearchBar(
            query = buscador,
            onQueryChange = {
                buscador = it
                // Cerrar el menú desplegable si la búsqueda está vacía
                if (it.isEmpty()) {
                    seleccionPlataforma = ""
                }
            },
            onSearch = {
            },
            active = menuDesplegado,
            onActiveChange = { menuDesplegado = it }
        ) {
            // 1 - Filtrar en base a "buscador"
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
        // RadioGroup para los tipos
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
                    checked = tipo in fechaSeleccionado,
                    onCheckedChange = {
                        fechaSeleccionado = tipo
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = tipo)
            }
        }

        // Slider para el nivel
        Spacer(modifier = Modifier.height(16.dp))
        Text("Valoracion: $puntuacion")
        Slider(
            value = puntuacion.toFloat(),
            onValueChange = { nuevaValoracion -> puntuacion = nuevaValoracion.toInt() },
            valueRange = 1f..100f,
            steps = 99
        )

        // Botones de navegación
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = {
                // Navegar hacia atrás
                lista.add(
                    infoArray(nombre, imagenes = R.drawable.kindomhearts
                    , fecha = ""
                    , seleccionPlataforma, puntuacion, fechaSeleccionado)
                )
                navController.navigate("pantallaInicio")
            }) {
                Icon(imageVector = Icons.Outlined.Check, contentDescription = "Save")
            }

            Spacer(modifier = Modifier.width(16.dp))

            IconButton(onClick = {
                // Guardar el Juego y navegar hacia atrás
                navController.navigate("pantallaInicio")
            }) {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Back")
            }
        }
    }
}