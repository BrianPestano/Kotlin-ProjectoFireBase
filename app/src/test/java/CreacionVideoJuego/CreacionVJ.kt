package CreacionVideoJuego

import Videojuego.infoArray
import PantallaPrincipal.lista
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.proyectofinalfirebasebrianylauren.R
import java.util.*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun pantallaCreacionPokemon(navController: NavHostController) {
    var nombre by remember { mutableStateOf("") }
    var tipoSeleccionado by remember { mutableStateOf(setOf<String>()) }
    var nivel by remember { mutableStateOf(1) }
    val regiones = listOf("Kanto", "Johto", "Hoenn", "Sinnoh", "Teselia", "Kalos", "Alola")
    var buscador by remember { mutableStateOf("") }
    var seleccionRegion by remember { mutableStateOf<String>("") }
    var menuDesplegado by remember { mutableStateOf(false) }
    val tipos = listOf("Eléctrico", "Fantasma", "Hada", "Tierra")
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            //.verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Cuadro de texto para el nombre
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del Pokémon") },
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
                    seleccionRegion = ""
                }
            },
            onSearch = {
            },
            active = menuDesplegado,
            onActiveChange = { menuDesplegado = it }
        ) {
            // 1 - Filtrar en base a "buscador"
            Column {
                regiones.filter { region -> region.startsWith(buscador, ignoreCase = true) }
                    .forEach { region ->
                        Log.d("pantallaCreacionPokemon", "Region: $region")
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

        // RadioGroup para los tipos
        Spacer(modifier = Modifier.height(16.dp))
        Text("Tipo(s):")
        tipos.forEach { tipo ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Checkbox(
                    checked = tipo in tipoSeleccionado,
                    onCheckedChange = {
                        tipoSeleccionado = if (tipoSeleccionado.contains(tipo)) {
                            tipoSeleccionado - tipo
                        } else {
                            tipoSeleccionado + tipo
                        }
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = tipo)
            }
        }

        // Slider para el nivel
        Spacer(modifier = Modifier.height(16.dp))
        Text("Nivel: $nivel")
        Slider(
            value = nivel.toFloat(),
            onValueChange = { nuevoNivel -> nivel = nuevoNivel.toInt() },
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
                lista.add(infoArray(nombre, imagenes = R.drawable.bulbasaur
                    , ruta = ""
                    , seleccionRegion, nivel))
                navController.popBackStack()
            }) {
                Icon(imageVector = Icons.Outlined.Check, contentDescription = "Back")
            }

            Spacer(modifier = Modifier.width(16.dp))

            IconButton(onClick = {
                // Guardar el Pokémon y navegar hacia atrás
                navController.navigate("pantallaDetalles/$nombre")
            }) {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Save")
            }
        }
    }
}