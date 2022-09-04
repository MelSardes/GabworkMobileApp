package com.sardes.thegabworkproject.ui.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sardes.thegabworkproject.R
import kiwi.orbit.compose.ui.controls.Scaffold


@SuppressLint(
    "UnrememberedMutableState", "UnusedMaterialScaffoldPaddingParameter",
    "MaterialDesignInsteadOrbitDesign"
)
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel? = null,
    navToEntrepriseInterface: () -> Unit = {},
    navToDemandeurInterface: () -> Unit = {},
    navToStandardInterface: () -> Unit = {},
    navToIndepedantInterface: () -> Unit = {},
    navToStudentInterface: () -> Unit = {},

    onNavToSelectSignUpPage: () -> Unit = {},
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

    Scaffold {
        Box {
            Image(
                painter = painterResource(id = R.drawable.perroquet),
                contentDescription = "perroquet",
                Modifier
                    .fillMaxSize(1f),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .fillMaxSize(),
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.Top,

                ) {
                Image(
                    painter = painterResource(id = R.drawable.gabwork_logo),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .weight(1f)
                        .size(150.dp),
                )
                Card(
                    Modifier
                        .weight(4f)
                        .padding(8.dp),
                    shape = RoundedCornerShape(32.dp),
                    backgroundColor = Color.White.copy(alpha = 0.7f)
                ) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(32.dp)
                            .align(CenterHorizontally)
                    ) {
                        Text(
                            text = "Bon retour parmi nous",
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp,
                            color = Color.Black
                        )

                        if (isError) Text(
                            loginUiState?.loginError ?: "Erreur inconnue",
                            color = Color.Red
                        )

                        Column(
                            Modifier.fillMaxSize(),
                            horizontalAlignment = CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Spacer(modifier = Modifier.weight(1f))
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = loginUiState?.userMail ?: "",
                                onValueChange = { loginViewModel?.onUserEmailChange(it) },
                                label = { Text(text = "Email") },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Email,
                                    imeAction = ImeAction.Next
                                ),
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
                                            Icon(
                                                imageVector = Icons.Filled.Clear,
                                                contentDescription = ""
                                            )
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
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Password,
                                    imeAction = ImeAction.Done
                                ),
                                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                                trailingIcon = {
                                    IconButton(onClick = {
                                        isPasswordVisible = !isPasswordVisible
                                    }) {
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
                                onClick = { loginViewModel?.loginUser(context) },
                                shape = RoundedCornerShape(162.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = "Connexion")
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                TextButton(onClick = { onNavToSelectSignUpPage.invoke() }) {
                                    Text(text = "Inscription")
                                }
                                TextButton(onClick = { }) {
                                    Text(text = "J'ai oublié mon mot de passe", color = Color.Gray)
                                }
                            }

                            if (loginUiState?.isLoading == true) {
                                CircularProgressIndicator()
                            }

                            if(loginUiState?.isSuccessLogin == true){
                                LaunchedEffect(key1 = Unit) {
                                    loginViewModel.hasUser
                                    loginViewModel.loadUserAccountType()
                                }
                            }

//                            -----------------------------------------------------------------------
//                            DETERMINE WHAT INTERFACE SHOW TO THE USER ACCORDING TO HIS ACCOUNT TYPE
//                            -----------------------------------------------------------------------
                            if (loginViewModel?.hasUser == true) {
                                LaunchedEffect(loginUiState?.userType?.account) {
                                    when (loginUiState?.userType?.account) {
                                        "Entreprise" -> navToEntrepriseInterface.invoke()
                                        "Demandeur" -> navToDemandeurInterface.invoke()
                                        "Standard" -> navToStandardInterface.invoke()
                                        "Etudiant" -> navToStudentInterface.invoke()
                                        "Independant" -> navToIndepedantInterface.invoke()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewLogIn(){
    LoginScreen()
}
