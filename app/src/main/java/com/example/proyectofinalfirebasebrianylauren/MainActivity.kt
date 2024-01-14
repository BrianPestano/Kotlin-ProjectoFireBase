package com.example.proyectofinalfirebasebrianylauren

import android.os.Bundle
import com.example.proyectofinalfirebasebrianylauren.ui.theme.ProyectoFinalFireBaseBrianyLaurenTheme
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinalfirebasebrianylauren.PantallaPrincipal.pantallaInicio
import com.example.proyectofinalfirebasebrianylauren.PantallaPrincipal.pantallaRegistro

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoFinalFireBaseBrianyLaurenTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Crea el NavController
                    val navController = rememberNavController()

                    // Configuración del sistema de navegación
                    SetupNavigation(navController)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProyectoFinalFireBaseBrianyLaurenTheme {
        Greeting("Android")
    }
}

@Composable
fun SetupNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "pantallaInicio") {
        composable("pantallaInicio") {
            // Llamada a la función  directamente con el navController
            pantallaInicio(navController)
        }
        composable("pantallaRegistro") {
            pantallaRegistro(navController)
        }
        composable("pantallaInicio") {
            pantallaInicio(navController)
        }
    }
}