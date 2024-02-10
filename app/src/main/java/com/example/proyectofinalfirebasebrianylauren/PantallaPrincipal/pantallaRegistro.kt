package com.example.proyectofinalfirebasebrianylauren.PantallaPrincipal

import androidx.compose.foundation.Image
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectofinalfirebasebrianylauren.R
import com.example.proyectofinalfirebasebrianylauren.ViewModel.ViewModelLogin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun pantallaRegistro(navController: NavHostController) {

    // Estados para correo electrónico, contraseña, habilitación de registro, visibilidad de la contraseña,
    // aceptación de términos, y estados de registro y error
    var correoElectronico by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isRegistrationEnabled by remember { mutableStateOf(true) }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isTermsAccepted by remember { mutableStateOf(false) }
    var registro by remember { mutableStateOf(false) }
    var viewmodeldememoria : ViewModelLogin = viewModel()
    var error by remember { mutableStateOf(false) }

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
            text = "¡Regístrate en la aplicación para empezar!",
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
                //Para el tamaño de la imagen
                .size(250.dp)
                //Esto es para que la imagen se vea circular
                .clip(CircleShape)
        )

        // Espaciador entre la imagen y los campos de entrada
        Spacer(modifier = Modifier.height(20.dp))

        // Columna para los campos de entrada de correo electrónico y contraseña
        Column(
            modifier = Modifier
                .padding(16.dp),
        ) {
            // Campo de entrada para el correo electrónico
            OutlinedTextField(
                value = correoElectronico,
                onValueChange = {
                    correoElectronico = it
                    updateRegistrationButtonState(correoElectronico, password, isTermsAccepted, isRegistrationEnabled)
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
                    updateRegistrationButtonState(correoElectronico, password, isTermsAccepted, isRegistrationEnabled)
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

        // Checkbox para aceptar términos
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isTermsAccepted,
                onCheckedChange = {
                    isTermsAccepted = it
                    updateRegistrationButtonState(correoElectronico, password, isTermsAccepted, isRegistrationEnabled)
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
                if(updateRegistrationButtonState(correoElectronico, password, isTermsAccepted,isRegistrationEnabled) == true){
                    viewmodeldememoria.createAccount(correoElectronico,password,navController)
                    registro = true
                }else if(password == "" || correoElectronico == "" || !correoElectronico.contains("@gmail.com") || password.length <8){
                    error = true
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = isRegistrationEnabled
        ) {
            Text(text = "Registrarse")
        }

        // Mostrar diálogo de error si hay un problema en el registro
        if(error){
            DialogoAlertaRegistro(onDismissRequest = { error = false }, onConfirmation = { error = false })
        }

        // Mostrar diálogo de éxito si el registro es exitoso
        if (registro){
            DialogoRegistro(onDismissRequest = { registro = false }, onConfirmation = { registro = false })
        }
    }
}

// Función para actualizar el estado del botón de registro
private fun updateRegistrationButtonState(correoElectronico: String, password: String, isTermsAccepted: Boolean, isRegistrationEnabled: Boolean) : Boolean {

    // Variable para validar cada campo
    var validar:Boolean = false

    // Verificar si todos los campos no están vacíos, que el correo termine en @gmail.com y la contraseña tenga al menos 8 caracteres
    validar = !(correoElectronico.isEmpty() && password.isEmpty() ||
            !correoElectronico.contains("@gmail.com") || password.length < 8)

    return validar
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogoAlertaRegistro(
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
fun DialogoRegistro(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String = "¡Registro exitoso!",
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