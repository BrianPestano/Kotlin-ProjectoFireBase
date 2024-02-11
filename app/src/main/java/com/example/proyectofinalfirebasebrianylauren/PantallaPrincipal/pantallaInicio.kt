package com.example.proyectofinalfirebasebrianylauren.PantallaPrincipal

import androidx.compose.runtime.Composable
import androidx.compose.material3.Card
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.proyectofinalfirebasebrianylauren.R
import com.example.proyectofinalfirebasebrianylauren.Videojuego.infoArray
import androidx.compose.foundation.lazy.items

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun pantallaInicio(navController: NavHostController) {

    // Variables de estado
    var buscador by remember { mutableStateOf("") }
    var seleccionPlataforma by remember { mutableStateOf<String?>(null) }
    var menuDesplegado by remember { mutableStateOf(false) }
    var VJSeleccionado by remember { mutableStateOf(mutableSetOf<String>()) }

    // Lista de plataformas
    val plataformas = listOf("PS2", "PS3", "PS4", "PS5", "PC", "XB", "XB360", "XB1", "XBSS", "XBSX", "Android", "NSwitch", "IOS", "Wii")

    // Columna principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Barra de búsqueda y menú desplegable de plataformas
        SearchBar(
            query = buscador,
            onQueryChange = {
                buscador = it
                if (it.isEmpty()) {
                    menuDesplegado = false
                    seleccionPlataforma = null
                }
            },
            onSearch = {
                if (seleccionPlataforma == null) {
                    menuDesplegado = false
                }
            },
            active = menuDesplegado,
            onActiveChange = { menuDesplegado = !menuDesplegado }
        ) {
            // Crear elementos de menú desplegable
            plataformas.forEach { plataforma ->
                if (plataforma.startsWith(buscador, ignoreCase = true)) {
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

        // Mostrar lista de videojuegos según la plataforma seleccionada
        if (seleccionPlataforma != null) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                items(lista.filter { it.plataformas.contains(seleccionPlataforma!!, ignoreCase = true) }) { videojuego ->
                    // Llamar a la función PlataformaItem para cada elemento de la lista
                    PlataformaItem(
                        plataforma = videojuego,
                        isChecked = videojuego.nombre in VJSeleccionado,
                        onCheckedChange = { isChecked ->
                            if (isChecked) {
                                VJSeleccionado.add(videojuego.nombre)
                            } else {
                                VJSeleccionado.remove(videojuego.nombre)
                            }
                        },
                        onClick = {
                            navController.navigate("pantallaDetalles/${videojuego.nombre}")
                        }
                    )
                }
            }
        } else if (seleccionPlataforma == null) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                items(lista) { videojuego ->
                    // Llamar a la función PlataformaItem para cada elemento de la lista
                    PlataformaItem(
                        plataforma = videojuego,
                        isChecked = videojuego.nombre in VJSeleccionado,
                        onCheckedChange = { isChecked ->
                            if (isChecked) {
                                VJSeleccionado.add(videojuego.nombre)
                            } else {
                                VJSeleccionado.remove(videojuego.nombre)
                            }
                        },
                        onClick = {
                            navController.navigate("pantallaDetalles/${videojuego.nombre}")
                        }
                    )
                }
            }
        }

        // Fila de botones (Añadir y Borrar)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Botón Añadir
            Button(onClick = {
                navController.navigate("pantallaCreacionVJ/$seleccionPlataforma") {
                    launchSingleTop = true
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                }
            }) {
                Text("Añadir")
            }

            // Botón Borrar
            Button(onClick = {
                lista.removeAll { it.nombre in VJSeleccionado }
                VJSeleccionado.clear()
            }) {
                Text("Borrar")
            }
        }
    }
}

@Composable
fun PlataformaItem(plataforma: infoArray, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit, onClick: () -> Unit) {
    var checked by remember { mutableStateOf(isChecked) }

    // Card que contiene la información de un videojuego
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(8.dp)
    ) {
        // Row que contiene la información y la checkbox
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Sección de la imagen y el nombre del videojuego
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1f)
            ) {
                // Imagen del videojuego
                Image(
                    painter = painterResource(id = plataforma.imagenes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.primary)
                )

                // Separador
                Spacer(modifier = Modifier.width(16.dp))

                // Nombre del videojuego
                Text(text = plataforma.nombre, style = MaterialTheme.typography.titleMedium)
            }

            // Separador
            Spacer(modifier = Modifier.width(16.dp))

            // Checkbox para seleccionar/deseleccionar un videojuego
            Checkbox(
                checked = checked,
                onCheckedChange = {
                    checked = it
                    onCheckedChange(it)
                }
            )
        }
    }
}