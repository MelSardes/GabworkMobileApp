package com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.AreaChart
import androidx.compose.material.icons.rounded.Description
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sardes.thegabworkproject.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EntrepriseAccountSignUpScreen(
    viewModel: EntrepriseAccountSignUpViewModel? = null,
    onNavToHomePage:() -> Unit,
    onNavToLoginPage:() -> Unit,
) {

    val entrepriseUiState = viewModel?.signUpUiState
    val isError = entrepriseUiState?.signUpError != null


    var entrepriseEmail by remember { mutableStateOf("") }
    var entrepriseName by remember { mutableStateOf("") }
    var entreprisedDescription by remember { mutableStateOf("") }
    var entrepriseActivityArea by remember { mutableStateOf("") }
    var entrepriseCity by remember { mutableStateOf("") }
    var entreprisePhone by remember { mutableStateOf("") }
    var entrepriseAddress by remember { mutableStateOf("") }
    var entrepriseWebsite by remember { mutableStateOf("") }


    val context = LocalContext.current

    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    Scaffold(backgroundColor = MaterialTheme.colors.primary) {

        if (entrepriseUiState?.isLoading == true){
            CircularProgressIndicator()
        }

        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.gabwork_logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .weight(1f)
            )
            Card(
                Modifier
                    .weight(6f)
                    .padding(8.dp),
                shape = RoundedCornerShape(32.dp)
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {


                    Text(
                        text = "Rejoingnez la communauté Gabwork",
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        textAlign = TextAlign.Center
                    )

                    if (isError) Text(
                        entrepriseUiState?.signUpError ?: "Erreur inconnue",
                        color = Color.Red
                    )

                    LazyColumn(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        //MAIL
                        item { // MAIL
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = entrepriseUiState?.entrepriseMail ?: "",
                                onValueChange = { viewModel?.onEntrepriseEmailChangeSignUp(it) },
                                label = { Text(text = "Email *") },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Email,
                                    imeAction = ImeAction.Next
                                ),
                                singleLine = true,
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Email,
                                        contentDescription = null
                                    )
                                },
                                trailingIcon = {
                                    if (entrepriseEmail.isNotBlank())
                                        IconButton(onClick = { entrepriseEmail = "" }) {
                                            Icon(imageVector = Icons.Filled.Clear, contentDescription = "")
                                        }
                                },
                                isError = isError
                            )
                        }

                        //PASSWORD
                        item {
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = entrepriseUiState?.password ?: "",
                                onValueChange = { viewModel?.onPasswordChangeSignUp(it) },
                                label = { Text(text = "Mot de passe *") },
                                singleLine = true,
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Lock,
                                        contentDescription = null
                                    )
                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Password,
                                    imeAction = ImeAction.Next
                                ),
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

                        }

                        // CONFIRM PASSWORD
                        item {
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = entrepriseUiState?.confirmPassword ?: "",
                                onValueChange = { viewModel?.onConfirmPasswordChange(it) },
                                label = { Text(text = "Confirmer le mot de passe *") },
                                singleLine = true,
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Lock,
                                        contentDescription = null
                                    )
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
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
                        }

                        // NAME
                        item {
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = entrepriseUiState?.entrepriseName ?: "",
                                onValueChange = { viewModel?.onEntrerpiseNameChange(it) },
                                label = { Text(text = "Nom de l'entreprise *") },
                                singleLine = true,
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Business,
                                        contentDescription = null
                                    )
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
                                trailingIcon = {
                                    if (entrepriseName.isNotBlank())
                                        IconButton(onClick = { entrepriseName = "" }) {
                                            Icon(imageVector = Icons.Filled.Clear, contentDescription = "")
                                        }
                                },
                                isError = isError
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                        }

                        // ACTIVITY AREA
                        item {
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = entrepriseUiState?.activityArea ?: "",
                                onValueChange = { viewModel?.onEntrerpiseActivityAreaChange(it) },
                                label = { Text(text = "Secteur d'activité *") },
                                singleLine = true,
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Rounded.AreaChart,
                                        contentDescription = null
                                    )
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
                                trailingIcon = {
                                    if (entrepriseActivityArea.isNotBlank())
                                        IconButton(onClick = { entrepriseActivityArea = "" }) {
                                            Icon(imageVector = Icons.Filled.Clear, contentDescription = "")
                                        }
                                },
                                isError = isError
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                        }

                            // DESCRIPTION
                        item {
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = entrepriseUiState?.description ?: "",
                                onValueChange = { viewModel?.onEntrepriseDescriptionChange(it) },
                                label = { Text(text = "Description de l'entreprise *") },
                                singleLine = true,
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Rounded.Description,
                                        contentDescription = null
                                    )
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
                                trailingIcon = {
                                    if (entreprisedDescription.isNotBlank())
                                        IconButton(onClick = { entreprisedDescription = "" }) {
                                            Icon(imageVector = Icons.Filled.Clear, contentDescription = "")
                                        }
                                },
                                isError = isError
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                        }

                        // PHONE
                        item {
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = entrepriseUiState?.phone ?: "",
                                onValueChange = { viewModel?.onEntreprisePhoneChange(it) },
                                label = { Text(text = "Téléphone *") },
                                singleLine = true,
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Phone,
                                        contentDescription = null
                                    )
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next),
                                trailingIcon = {
                                    if (entreprisePhone.isNotBlank())
                                        IconButton(onClick = { entreprisePhone = "" }) {
                                            Icon(imageVector = Icons.Filled.Clear, contentDescription = "")
                                        }
                                },
                                isError = isError
                            )

                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        // CITY
                        item {
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = entrepriseUiState?.city ?: "",
                                onValueChange = { viewModel?.onEntrepriseCityChange(it) },
                                label = { Text(text = "Ville *") },
                                singleLine = true,
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.LocationCity,
                                        contentDescription = null
                                    )
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
                                trailingIcon = {
                                    if (entrepriseCity.isNotBlank())
                                        IconButton(onClick = { entrepriseCity = "" }) {
                                            Icon(imageVector = Icons.Filled.Clear, contentDescription = "")
                                        }
                                },
                                isError = isError
                            )

                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        // ADDRESS
                        item {
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = entrepriseUiState?.address ?: "",
                                onValueChange = { viewModel?.onAddressChange(it) },
                                label = { Text(text = "Adress *") },
                                singleLine = true,
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Place,
                                        contentDescription = null
                                    )
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
                                trailingIcon = {
                                    if (entrepriseAddress.isNotBlank())
                                        IconButton(onClick = { entrepriseAddress = "" }) {
                                            Icon(imageVector = Icons.Filled.Clear, contentDescription = "")
                                        }
                                },

                                isError = isError
                            )

                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        // WEBSITE
                        item {
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = entrepriseUiState?.website ?: "",
                                onValueChange = { viewModel?.onEntrepriseWebsitehange(it) },
                                label = { Text(text = "Site web de l'entreprise") },
                                singleLine = true,
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Web,
                                        contentDescription = null
                                    )
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
                                trailingIcon = {
                                    if (entrepriseWebsite.isNotBlank())
                                        IconButton(onClick = { entrepriseWebsite = "" }) {
                                            Icon(imageVector = Icons.Filled.Clear, contentDescription = "")
                                        }
                                },

                                isError = isError
                            )

                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        //BUTTON
                        item {
                            Button(
                                onClick = { viewModel?.createUser(context)},
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(162.dp)
                            ) {
                                Text(text = "J'inscit mon entreprise")
                            }

                            Spacer(modifier = Modifier.weight(1f))
                        }

                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                TextButton(onClick = {onNavToLoginPage.invoke()}) {
                                    Text(text = "Entreprise déjà inscrite ? \n Connexion")
                                }
                            }

                        }

                    }

                    LaunchedEffect(key1 = viewModel?.hasUser){
                        if (viewModel?.hasUser == true){
                            onNavToHomePage.invoke()
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun EntrepriseAccountSignUpScreenPreview() {
    EntrepriseAccountSignUpScreen(null, {}, {})
}
