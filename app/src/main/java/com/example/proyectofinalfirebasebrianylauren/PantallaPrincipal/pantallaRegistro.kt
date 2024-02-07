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

    var correoElectronico by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isRegistrationEnabled by remember { mutableStateOf(true) }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isTermsAccepted by remember { mutableStateOf(false) }
    var viewmodeldememoria : ViewModelLogin = viewModel()
    var error by remember { mutableStateOf(false) }

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
                }else{
                    error = true
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = isRegistrationEnabled
        ) {
            Text(text = "Registrarse")
        }
        if(error){
            DialogoAlertaRegistro(onDismissRequest = { error = false }, onConfirmation = { error = false })
        }
        }
    }
private fun  updateRegistrationButtonState(correoElectronico: String
                                    , password: String
                                    , isTermsAccepted: Boolean,
                                           isRegistrationEnabled: Boolean) : Boolean {

    //Variable para validar cada campo
    var validar:Boolean = false

    // Verificar si todos los campos no están vacíos, que el correo termine en @gmail.com y la contraseña tenga minimo 8 caracteres
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
    dialogText: String = "¡Introduce bien los datos, acuerdate de que el correo debe de terminar en @gmail.com y la contraseña minimo 8 caracteres!"
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
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}