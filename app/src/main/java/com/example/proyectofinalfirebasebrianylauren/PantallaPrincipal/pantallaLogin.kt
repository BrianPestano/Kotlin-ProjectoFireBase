package com.example.proyectofinalfirebasebrianylauren.PantallaPrincipal

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.navigation.NavHostController
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Lock
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.proyectofinalfirebasebrianylauren.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun pantallaLogin(navController: NavHostController) {

    var nombreUsuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoginEnabled by remember { mutableStateOf(true) }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isTermsAccepted by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Espaciador para empujar el contenido hacia abajo
        Spacer(modifier = Modifier.height(16.dp))

        // Texto de bienvenida
        Text(
            text = "¡Bienvenido a la aplicación, introduce tus datos para acceder!",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Espaciador para separar el mensaje y la imagen
        Spacer(modifier = Modifier.height(20.dp))

        // Imagen debajo del texto de bienvenida
        Image(
            painter = painterResource(id = R.drawable.logo), // Reemplaza con el recurso de tu imagen
            contentDescription = null,
            modifier = Modifier
                .size(250.dp) // Ajusta el tamaño de la imagen según tus necesidades
                .clip(CircleShape) // Opcional: si quieres que la imagen sea circular
        )

        // Espaciador entre la imagen y los campos de entrada
        Spacer(modifier = Modifier.height(20.dp))

        // Campo de entrada para el nombre de usuario
        Column(
            modifier = Modifier
                .padding(16.dp),
        ) {
            OutlinedTextField(
                value = nombreUsuario,
                onValueChange = {
                    nombreUsuario = it
                    updateLoginButtonState(nombreUsuario, password, isTermsAccepted,isLoginEnabled)
                },
                label = { Text(text = "Nombre de Usuario") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        // Mover el foco al siguiente campo o ejecutar una acción
                    }
                )
            )

            // Espaciador entre elementos
            Spacer(modifier = Modifier.height(8.dp))

            // Campo de entrada para la contraseña
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    updateLoginButtonState(nombreUsuario, password, isTermsAccepted,isLoginEnabled)
                },
                label = { Text(text = "Contraseña") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            isPasswordVisible = !isPasswordVisible
                        }
                    ) {
                        Icon(
                            imageVector = if (isPasswordVisible) Icons.Default.Done else Icons.Default.Close,
                            contentDescription = null
                        )
                    }
                },
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // Puedes manejar la acción de inicio de sesión aquí
                    }
                )
            )
        }

        // Checkbox para aceptar términos
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isTermsAccepted,
                onCheckedChange = {
                    isTermsAccepted = it
                    updateLoginButtonState(nombreUsuario, password, isTermsAccepted,isLoginEnabled)
                },
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = "Acepto los términos y condiciones de la aplicación",
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Espaciador entre elementos
        Spacer(modifier = Modifier.height(8.dp))

        // Texto con efecto clickeable simulando un botón
        Text(
            text = "¿No tienes una cuenta? ¡regístrate ahora!",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {
                navController.navigate("pantallaRegistro")
            }
        )

        // Botón de inicio de sesión
        Button(
            onClick = {
                navController.navigate("pantallaInicio")
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = isLoginEnabled
        ) {
            Text(text = "Iniciar sesión")
        }
    }
}

private fun updateLoginButtonState(nombreUsuario: String, password: String, isTermsAccepted: Boolean, isLoginEnabled: Boolean) {
    // Verificar si ambos campos no están vacíos
    val isNotEmpty = nombreUsuario.isNotEmpty() && password.isNotEmpty()

    // Verificar que el nombre de usuario no tenga números ni caracteres especiales
    val isUsernameValid = !nombreUsuario.matches(".*\\d.*".toRegex()) && nombreUsuario.matches("[a-zA-Z]+".toRegex())

    // Verificar que la contraseña tenga una longitud mínima de 8 caracteres
    val isPasswordValid = password.length >= 8

    // Actualizar el estado del botón de inicio de sesión
    val isLoginEnabled = isNotEmpty && isUsernameValid && isPasswordValid && isTermsAccepted
}