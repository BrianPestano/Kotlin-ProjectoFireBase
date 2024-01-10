package PantallaPrincipal

import androidx.compose.runtime.Composable
import Videojuego.infoArray
import androidx.compose.material3.Card
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.proyectofinalfirebasebrianylauren.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun pantallaPrincipal(navController: NavHostController) {

    var buscador by remember { mutableStateOf("") }
    var seleccionRegion by remember { mutableStateOf<String?>(null) }
    var menuDesplegado by remember { mutableStateOf(false) }
    var pokemonSeleccionado by remember { mutableStateOf(mutableSetOf<String>()) }

    val regiones = listOf("Kanto", "Johto", "Hoenn", "Sinnoh", "Teselia", "Kalos", "Alola")

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
                    seleccionRegion = null
                }
            },
            onSearch = {
                if (seleccionRegion == null) {
                    menuDesplegado = false
                }
            },
            active = menuDesplegado,
            onActiveChange = { menuDesplegado = !menuDesplegado }
        ) {
            regiones.forEach { region ->
                if (region.startsWith(buscador, ignoreCase = true)) {
                    DropdownMenuItem(
                        onClick = {
                            seleccionRegion = region
                            buscador = region
                            menuDesplegado = false
                        },
                        text = { Text(text = region) }
                    )
                }
            }
        }

        if (seleccionRegion != null) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                items(lista.filter { it.region.equals(seleccionRegion, ignoreCase = true) }) { pokemon ->
                    RegionItem(
                        region = pokemon,
                        isChecked = pokemon.nombre in pokemonSeleccionado,
                        onCheckedChange = { isChecked ->
                            if (isChecked) {
                                pokemonSeleccionado.add(pokemon.nombre)
                            } else {
                                pokemonSeleccionado.remove(pokemon.nombre)
                            }
                        },
                        onClick = {
                            navController.navigate("pantallaDetalles/${pokemon.nombre}")
                        }
                    )
                }
            }
        } else if (seleccionRegion == null) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                items(lista) { pokemon ->
                    RegionItem(
                        region = pokemon,
                        isChecked = pokemon.nombre in pokemonSeleccionado,
                        onCheckedChange = { isChecked ->
                            if (isChecked) {
                                pokemonSeleccionado.add(pokemon.nombre)
                            } else {
                                pokemonSeleccionado.remove(pokemon.nombre)
                            }
                        },
                        onClick = {
                            navController.navigate("pantallaDetalles/${pokemon.nombre}")
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
                navController.navigate("pantallaCreacionPokemon/$seleccionRegion") {
                    launchSingleTop = true
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                }
            }) {
                Text("Añadir")
            }

            Button(onClick = {
                lista.removeAll { it.nombre in pokemonSeleccionado }
                pokemonSeleccionado.clear()
            }) {
                Text("Borrar")
            }
        }
    }
}
@Composable
fun RegionItem(region: infoArray, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit, onClick: () -> Unit) {
    var checked by remember { mutableStateOf(isChecked) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                // Solo ejecuta el código onClick si se hace clic en la fila pero no en la casilla de verificación
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
                    painter = painterResource(id = region.imagenes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.primary)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(text = region.nombre, style = MaterialTheme.typography.titleMedium)
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