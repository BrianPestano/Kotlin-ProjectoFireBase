package com.example.proyectofinalfirebasebrianylauren

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.proyectofinalfirebasebrianylauren.PantallaPrincipal.pantallaLogin
import com.example.proyectofinalfirebasebrianylauren.PantallaPrincipal.pantallaInicio
import com.example.proyectofinalfirebasebrianylauren.PantallaPrincipal.pantallaRegistro
import com.example.proyectofinalfirebasebrianylauren.PantallaDetalles.pantallaDetalles
import com.example.proyectofinalfirebasebrianylauren.CreacionVideoJuego.pantallaCreacionVJ
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinalfirebasebrianylauren.ui.theme.ProyectoFinalFireBaseBrianyLaurenTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoFinalFireBaseBrianyLaurenTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
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
    NavHost(navController = navController, startDestination = "pantallaLogin") {
        composable("pantallaLogin") {
            pantallaLogin(navController) }
        composable("pantallaRegistro"){
            pantallaRegistro(navController)
        }
        composable("pantallaInicio"){
            pantallaInicio(navController)
        }
        composable("pantallaDetalles/{VJNombre}",
            arguments = listOf(navArgument("VJNombre") { type = NavType.StringType })
        ) { backStackEntry ->
            val VJNombre = backStackEntry.arguments?.getString("VJNombre")
            pantallaDetalles(navController,VJNombre)
        }
        composable("pantallaCreacionVJ/{region}") { backStackEntry ->
            val region = backStackEntry.arguments?.getString("plataforma")
            pantallaCreacionVJ(navController)
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