package com.sardes.thegabworkproject

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sardes.thegabworkproject.view.login.LoginViewModel


@SuppressLint("UnrememberedMutableState", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StandardLogin(
    loginViewModel: LoginViewModel? = null,
    onNavToHomePage:() -> Unit,
    onNavToStandardSignUpPage:() -> Unit,
//    navController: NavController
) {

    val loginUiState = loginViewModel?.loginUiState
    val isError = loginUiState?.loginError != null
    val context = LocalContext.current

    var userEmail by remember {
        mutableStateOf("")
    }

    var isPasswordVisible by remember {
        mutableStateOf(false)
    }
//    val isFormValid by derivedStateOf {
//        userEmail.isNotBlank() && password.length >= 7
//    }

    Scaffold(backgroundColor = MaterialTheme.colors.primary) {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
            Image(
                painter = painterResource(id = R.drawable.gabwork_logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .weight(1f)
                    .size(200.dp),
            )
            Card(
                Modifier
                    .weight(2f)
                    .padding(8.dp),
                shape = RoundedCornerShape(32.dp)
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    Text(text = "Bon retour parmi nous", fontWeight = FontWeight.Bold, fontSize = 32.sp)

                    if (isError) Text(
                        loginUiState?.loginError ?: "Erreur inconnue",
                        color = Color.Red
                    )

                    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Spacer(modifier = Modifier.weight(1f))
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = loginUiState?.userName ?: "",
                            onValueChange = { loginViewModel?.onUserNameChange(it) },
                            label = { Text(text = "Email") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                            singleLine = true,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = null
                                )
                            },
                            trailingIcon = {
                                if (userEmail.isNotBlank())
                                    IconButton(onClick = { userEmail = "" }) {
                                        Icon(imageVector = Icons.Filled.Clear, contentDescription = "")
                                    }
                            },
                            isError = isError
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = loginUiState?.password ?: "",
                            onValueChange = { loginViewModel?.onPasswordChange(it) },
                            label = { Text(text = "Mot de passe") },
                            singleLine = true,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = null
                                )
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                                    Icon(
                                        imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                        contentDescription = "Password Toggle"
                                    )
                                }
                            },
                            isError = isError
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { loginViewModel?.loginUser(context)

/*                                auth.signInWithEmailAndPassword(
                                    userEmail.trim(),
                                    password.trim()
                                )
                                    .addOnCompleteListener(){ task->
                                        if (task.isSuccessful){
                                            Log.d("AUTH", "Success!")
                                            navController.navigate("${Screen.Home.route}/$mel")

                                        } else {
                                            Log.d("AUTH", "Failed: ${task.exception}")
                                        }
                                    }*/
                            },
//                            enabled = isFormValid,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(162.dp)
                        ) {
                            Text(text = "Connexion")
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            TextButton(onClick = {onNavToStandardSignUpPage.invoke()}) {
                                Text(text = "Inscription")
                            }
                            TextButton(onClick = { }) {
                                Text(text = "J'ai oublié mon mot de passe", color = Color.Gray)
                            }
                        }

                        if (loginUiState?.isLoading == true){
                            CircularProgressIndicator()
                        }
                        
                        LaunchedEffect(key1 = loginViewModel?.hasUser){
                            if (loginViewModel?.hasUser == true){
                                onNavToHomePage.invoke()
                            }
                        }
                    }
                }
            }
        }
    }
}