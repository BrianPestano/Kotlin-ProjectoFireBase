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

    var buscador by remember { mutableStateOf("") }
    var seleccionPlataforma by remember { mutableStateOf<String?>(null) }
    var menuDesplegado by remember { mutableStateOf(false) }
    var VJSeleccionado by remember { mutableStateOf(mutableSetOf<String>()) }

    val plataformas = listOf("PS2", "PS3", "PS4", "PS5", "PC", "XB", "XB360", "XB1", "XBSS", "XBSX", "Android", "NSwitch", "IOS", "Wii")

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

        if (seleccionPlataforma != null) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                items(lista.filter { it.plataformas.equals(seleccionPlataforma, ignoreCase = true) }) { videojuego ->
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
                Image(
                    painter = painterResource(id = plataforma.imagenes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.primary)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(text = plataforma.nombre, style = MaterialTheme.typography.titleMedium)
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