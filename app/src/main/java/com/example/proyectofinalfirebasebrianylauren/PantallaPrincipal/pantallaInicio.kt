package com.example.proyectofinalfirebasebrianylauren.PantallaPrincipal

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.material3.Card
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.Color
import com.example.proyectofinalfirebasebrianylauren.Videojuego.Juegos
import com.example.proyectofinalfirebasebrianylauren.ViewModel.ViewModelFirebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun pantallaInicio(navController: NavHostController, viewModel: ViewModelFirebase) {
    var buscador by remember { mutableStateOf("") }
    var seleccionPlataforma by remember { mutableStateOf<String?>(null) }
    var menuDesplegado by remember { mutableStateOf(false) }
    var VJSeleccionado by remember { mutableStateOf(mutableSetOf<String>()) }

    val plataformas = listOf(
        "PS2", "PS3", "PS4", "PS5", "PC", "XB", "XB360", "XB1", "XBSS", "XBSX",
        "Android", "NSwitch", "IOS", "Wii"
    )

    //Llamamos a la función crearListener para escuchar cambios en la base de datos(gracias profe)
    viewModel.crearListener()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
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

        // Utilizamos el flujo listaJuegos del ViewModel
        val videojuegos by viewModel.listaJuegos.collectAsState()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            val filteredList = if (seleccionPlataforma != null) {
                videojuegos.filter {
                    it.plataforma.contains(
                        seleccionPlataforma!!,
                        ignoreCase = true
                    )
                }
            } else {
                videojuegos
            }

            items(filteredList) { videojuego ->
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
                        // Asignamos el juego seleccionado al ViewModel antes de navegar
                        viewModel.asignarJuegoSeleccionado(videojuego)
                        navController.navigate("pantallaDetalles/${videojuego.nombre}")
                    }
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
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

            Button(onClick = {
                navController.navigate("pantallaCreditos") {}
            }) {
                Text("Creditos")
            }

            Button(onClick = {
                VJSeleccionado.forEach { nombreJuego ->
                    viewModel.borrarJuego(nombreJuego)
                }
                VJSeleccionado.clear()
            }) {
                Text("Borrar")
            }
        }
    }
}

@Composable
fun <T : Any> PlataformaItem(
    plataforma: T,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClick: () -> Unit
) {
    var checked by remember { mutableStateOf(isChecked) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1f)
            ) {
                // Nombre del videojuego
                Text(text = obtenerNombreSegunTipo(plataforma), style = MaterialTheme.typography.titleMedium)
            }

            Spacer(modifier = Modifier.width(16.dp))

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
private fun obtenerNombreSegunTipo(juego: Any): String {
    return when (juego) {
        is Juegos -> juego.nombre
        else -> "Nombre Desconocido"
    }
}