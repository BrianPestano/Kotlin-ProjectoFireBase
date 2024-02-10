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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectofinalfirebasebrianylauren.R
import com.example.proyectofinalfirebasebrianylauren.ViewModel.ViewModelLogin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun pantallaLogin(navController: NavHostController) {

    // Estado del correo electrónico, contraseña y botón de inicio de sesión
    var correoElectronico by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoginEnabled by remember { mutableStateOf(true) }

    // Estado para la visibilidad de la contraseña
    var isPasswordVisible by remember { mutableStateOf(false) }

    // ViewModel para la lógica de inicio de sesión
    var viewmodeldememoria : ViewModelLogin = viewModel()

    // Estados para manejar diálogos de error y éxito
    var error by remember { mutableStateOf(false) }
    var inicio by remember { mutableStateOf(false) }

    // Diseño de la pantalla
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
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                //Tamaño de la imagen
                .size(250.dp)
                //Esto es para que la imagen sea circular
                .clip(CircleShape)
        )

        // Espaciador entre la imagen y los campos de entrada
        Spacer(modifier = Modifier.height(20.dp))

        // Campos de entrada para correo electrónico y contraseña
        Column(
            modifier = Modifier
                .padding(16.dp),
        ) {
            // Campo de entrada para el correo electrónico
            OutlinedTextField(
                value = correoElectronico,
                onValueChange = {
                    correoElectronico = it
                    updateLoginButtonState(correoElectronico, password, isLoginEnabled)
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
                    updateLoginButtonState(correoElectronico, password, isLoginEnabled)
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

                    }
                )
            )
        }

        // Texto con efecto clickeable simulando un botón
        Text(
            text = "¿No tienes una cuenta? ¡Regístrate ahora!",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {
                navController.navigate("pantallaRegistro")
            }
        )

        // Botón de inicio de sesión
        Button(
            onClick = {
                if (updateLoginButtonState(correoElectronico, password, isLoginEnabled) == true) {
                    viewmodeldememoria.signIn(correoElectronico, password, navController)
                    inicio = true
                } else if (password == "" || correoElectronico == "" || !correoElectronico.contains("@gmail.com") || password.length < 8) {
                    error = true
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = isLoginEnabled
        ) {
            Text(text = "Iniciar sesión")
        }

        // Mostrar diálogo de error si hay un problema en el inicio de sesión
        if (error){
            DialogoAlerta(onDismissRequest = { error = false }, onConfirmation = { error = false })
        }

        // Mostrar diálogo de éxito si el inicio de sesión es exitoso
        if (inicio){
            DialogoExito(onDismissRequest = { inicio = false }, onConfirmation = { inicio = false })
        }
    }
}

// Función para actualizar el estado del botón de inicio de sesión
private fun updateLoginButtonState(correoElectronico: String, password: String, isLoginEnabled: Boolean) : Boolean {

    // Variable para validar cada campo
    var validar:Boolean = false

    // Verificar si todos los campos no están vacíos, que el correo termine en @gmail.com y la contraseña tenga al menos 8 caracteres
    validar = !(correoElectronico.isEmpty() && password.isEmpty() ||
            !correoElectronico.contains("@gmail.com") || password.length < 8)

    return validar
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogoAlerta(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String = "¡Alerta!",
    dialogText: String = "¡Introduce bien los datos, acuerdate de que el correo debe de terminar en @gmail.com y la contraseña debe tener al menos 8 caracteres!"
) {
    AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirmar")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Descartar")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogoExito(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String = "¡Inicio de sesión exitoso!",
    dialogText: String = ""
) {
    AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Descartar")
            }
        }
    )
}