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
import androidx.compose.material.icons.filled.Email
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
fun pantallaRegistro(navController: NavHostController) {

    var nombreUsuario by remember { mutableStateOf("") }
    var correoElectronico by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isRegistrationEnabled by remember { mutableStateOf(false) }
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
            text = "¡Regístrate en la aplicación para empezar!",
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
                    updateRegistrationButtonState(nombreUsuario, correoElectronico, password, isTermsAccepted)
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

            // Campo de entrada para el correo electrónico
            OutlinedTextField(
                value = correoElectronico,
                onValueChange = {
                    correoElectronico = it
                    updateRegistrationButtonState(nombreUsuario, correoElectronico, password, isTermsAccepted)
                },
                label = { Text(text = "Correo Electrónico") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
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
                    updateRegistrationButtonState(nombreUsuario, correoElectronico, password, isTermsAccepted)
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
                        // Puedes manejar la acción de registro aquí
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
                    updateRegistrationButtonState(nombreUsuario, correoElectronico, password, isTermsAccepted)
                },
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = "Acepto los términos y condiciones de la aplicación",
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Botón de registro
        Button(
            onClick = {
                // Puedes manejar la acción de registro aquí
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = isRegistrationEnabled
        ) {
            Text(text = "Registrarse")
        }
    }
}

private fun updateRegistrationButtonState(nombreUsuario: String, correoElectronico: String, password: String, isTermsAccepted: Boolean) {
    // Verificar si todos los campos no están vacíos
    val isNotEmpty = nombreUsuario.isNotEmpty() && correoElectronico.isNotEmpty() && password.isNotEmpty()

    // Verificar que el nombre de usuario no tenga números ni caracteres especiales
    val isUsernameValid = !nombreUsuario.matches(".*\\d.*".toRegex()) && nombreUsuario.matches("[a-zA-Z]+".toRegex())

    // Verificar que la contraseña tenga una longitud mínima de 8 caracteres
    val isPasswordValid = password.length >= 8
}
