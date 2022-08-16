package com.sardes.thegabworkproject.ui.screens.signup.independantsignup

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.DataUsage
import androidx.compose.material.icons.rounded.Flag
import androidx.compose.material.icons.rounded.Web
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
import com.sardes.thegabworkproject.ui.screens.signup.Utils
import com.sardes.thegabworkproject.ui.screens.signup.components.SexItem

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun IndependantAccountSignUpSceen(
    viewModel: IndependantAccountSignUpViewModel? = null,
    onNavToHomePage:() -> Unit,
    onNavToLoginPage:() -> Unit
) {

    val signUpUiState = viewModel?.signUpUiState
    val isError = signUpUiState?.signUpError != null
    val context = LocalContext.current


    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    var userEmail by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var userSkills by remember { mutableStateOf("") }
    var userNationality by remember { mutableStateOf("") }
    var userCity by remember { mutableStateOf("") }
    var userPhone by remember { mutableStateOf("") }
    var userAddress by remember { mutableStateOf("") }
    var userWebsite by remember { mutableStateOf("") }


    Scaffold(backgroundColor = MaterialTheme.colors.primary) {

        if (signUpUiState?.isLoading == true){
            CircularProgressIndicator()
        }

        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = com.sardes.thegabworkproject.R.drawable.gabwork_logo),
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
                        signUpUiState?.signUpError ?: "Erreur inconnue",
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
                                value = signUpUiState?.userMail ?: "",
                                onValueChange = { viewModel?.onUserEmailChangeSignUp(it) },
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
                                    if (userEmail.isNotBlank())
                                        IconButton(onClick = { userEmail = "" }) {
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
                                value = signUpUiState?.password ?: "",
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
                                value = signUpUiState?.confirmPassword ?: "",
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
                                value = signUpUiState?.userName ?: "",
                                onValueChange = { viewModel?.onUserNameChange(it) },
                                label = { Text(text = "Nom *") },
                                singleLine = true,
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Person,
                                        contentDescription = null
                                    )
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
                                trailingIcon = {
                                    if (userName.isNotBlank())
                                        IconButton(onClick = { userName = "" }) {
                                            Icon(imageVector = Icons.Filled.Clear, contentDescription = "")
                                        }
                                },
                                isError = isError
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                        }

                        //FORENAME
                        item {
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = signUpUiState?.userName ?: "",
                                onValueChange = { viewModel?.onForeNameChange(it) },
                                label = { Text(text = "Prénom *") },
                                singleLine = true,
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Person,
                                        contentDescription = null
                                    )
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
                                trailingIcon = {
                                    if (userName.isNotBlank())
                                        IconButton(onClick = { userName = "" }) {
                                            Icon(imageVector = Icons.Filled.Clear, contentDescription = "")
                                        }
                                },
                                isError = isError
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                        }

                        //SEX
                        item {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Text(text = "Genre", textAlign = TextAlign.Center)
                                LazyRow(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly,

                                    contentPadding = PaddingValues(
                                        vertical = 10.dp,
                                        horizontal = 10.dp
                                    )
                                ) {
                                    itemsIndexed(Utils.sex) { _, sex ->
                                        SexItem(sexItem = sex) {
                                            viewModel?.onUserSexChange(sex)
                                        }
                                    }
                                }

                            }
                            Spacer(modifier = Modifier.height(16.dp))
                        }


                        // SKILLS
                        item {
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = signUpUiState?.skills ?: "",
                                onValueChange = { viewModel?.onSkillsChange(it) },
                                label = { Text(text = "Compétences *") },
                                singleLine = true,
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Rounded.DataUsage,
                                        contentDescription = null
                                    )
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
                                trailingIcon = {
                                    if (userSkills.isNotBlank())
                                        IconButton(onClick = {userSkills = "" }) {
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
                                value = signUpUiState?.webisite ?: "",
                                onValueChange = { viewModel?.onWebsiteChange(it) },
                                label = { Text(text = "Site web") },
                                singleLine = true,
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Rounded.Web,
                                        contentDescription = null
                                    )
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
                                trailingIcon = {
                                    if (userWebsite.isNotBlank())
                                        IconButton(onClick = { userWebsite = "" }) {
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
                                value = signUpUiState?.phone ?: "",
                                onValueChange = { viewModel?.onUserPhoneChange(it) },
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
                                    if (userPhone.isNotBlank())
                                        IconButton(onClick = { userPhone = "" }) {
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
                                value = signUpUiState?.city ?: "",
                                onValueChange = { viewModel?.onUserCityChange(it) },
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
                                    if (userCity.isNotBlank())
                                        IconButton(onClick = { userCity = "" }) {
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
                                value = signUpUiState?.address ?: "",
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
                                    if (userAddress.isNotBlank())
                                        IconButton(onClick = { userAddress = "" }) {
                                            Icon(imageVector = Icons.Filled.Clear, contentDescription = "")
                                        }
                                },

                                isError = isError
                            )

                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        // NATIONALITY
                        item {
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = signUpUiState?.nationality ?: "",
                                onValueChange = { viewModel?.onNationalityChange(it) },
                                label = { Text(text = "Nationalité *") },
                                singleLine = true,
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Rounded.Flag,
                                        contentDescription = null
                                    )
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
                                trailingIcon = {
                                    if (userNationality.isNotBlank())
                                        IconButton(onClick = { userNationality = "" }) {
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
                                Text(text = "Je m'inscrit")
                            }

                            Spacer(modifier = Modifier.weight(1f))
                        }

                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                TextButton(onClick = {onNavToLoginPage.invoke()}) {
                                    Text(text = "J'ai déjà un compte\n Je me connecte")
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

@Preview(name = "IndependantAccountSignUpSceen")
@Composable
private fun PreviewIndependantAccountSignUpSceen() {
    IndependantAccountSignUpSceen(null, {}, {})
}