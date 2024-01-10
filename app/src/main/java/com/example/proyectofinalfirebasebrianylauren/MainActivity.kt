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
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            proyectofinalfirebasebrianylauren {
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
fun SetupNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "pantallaPrincipal") {
        composable("pantallaPrincipal") { pantallaPrincipal (navController) }
        composable(
            "pantallaDetalles/{pokemonNombre}",
            arguments = listOf(navArgument("pokemonNombre") { type = NavType.StringType })
        ) { backStackEntry ->
            val pokemonNombre = backStackEntry.arguments?.getString("pokemonNombre")
            pantallaDetalles(navController,pokemonNombre)

        }
        composable("CreacionVJ/{region}") { backStackEntry ->
            val region = backStackEntry.arguments?.getString("region")
            CreacionVJ(navController)
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
    proyectofinalfirebasebrianylauren {
        Greeting("Android")
    }
}