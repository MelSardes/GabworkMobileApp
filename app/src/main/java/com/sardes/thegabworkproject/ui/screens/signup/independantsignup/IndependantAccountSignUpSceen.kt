package com.sardes.thegabworkproject.ui.screens.signup.independantsignup

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import coil.compose.rememberAsyncImagePainter
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.ui.screens.signup.Utils
import com.sardes.thegabworkproject.ui.screens.signup.components.SexItem
import com.sardes.thegabworkproject.ui.screens.signup.imageUri
import com.sardes.thegabworkproject.ui.theme.YellowFlag

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "MaterialDesignInsteadOrbitDesign")
@Composable
fun IndependantAccountSignUpSceen(
    viewModel: IndependantAccountSignUpViewModel? = null,
    onNavToMainPage:() -> Unit,
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
    var userForename by remember { mutableStateOf("") }
    var userSkills by remember { mutableStateOf("") }
    var userNationality by remember { mutableStateOf("") }
    var userCity by remember { mutableStateOf("") }
    var userPhone by remember { mutableStateOf("") }
    var userAddress by remember { mutableStateOf("") }
    var userWebsite by remember { mutableStateOf("") }

    val selectImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri.value = uri
    }


    Scaffold(backgroundColor = MaterialTheme.colors.primary) {

        Box {

            Image(
                painter = painterResource(id = R.drawable.perroquet),
                contentDescription = "perroquet",
                Modifier
                    .fillMaxSize(1f),
                contentScale = ContentScale.Crop
            )

            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                ){
                    Box(modifier = Modifier
                        .size(102.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .align(Alignment.Center)
                        .border(width = 2.dp, color = Color.White, shape = CircleShape)
                    ) {

                        if (imageUri.value != null) {
                            TextButton(
                                onClick = { selectImage.launch("image/*") },
                                modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.2f))
                            ) {
                                Text("Choisir une photo de profil", color = YellowFlag, textAlign = TextAlign.Center)

                            }
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                painter = rememberAsyncImagePainter(model = imageUri.value),
                                contentScale = ContentScale.Crop,
                                contentDescription = "image",
                            )
                        }else{
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                painter = painterResource(id = R.drawable.ic_placeholder),
                                contentScale = ContentScale.Crop,
                                contentDescription = "image"
                            )
                            TextButton(
                                onClick = { selectImage.launch("image/*") },
                                modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.2f))
                            ) {
                                Text("Choisir une image", color = YellowFlag, textAlign = TextAlign.Center)
                            }
                        }

                        viewModel?.onPhotoChange(imageUri.value)

                    }
                }


                Card(
                    Modifier
                        .weight(6f)
                        .padding(8.dp),
                    shape = RoundedCornerShape(32.dp),
                    backgroundColor = Color.White.copy(alpha = 0.8f)

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
                                    value = signUpUiState?.foreName ?: "",
                                    onValueChange = { viewModel?.onForeNameChange(it) },
                                    label = { Text(text = "Prénom(s)") },
                                    singleLine = true,
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.Person,
                                            contentDescription = null
                                        )
                                    },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
                                    trailingIcon = {
                                        if (userForename.isNotBlank())
                                            IconButton(onClick = { userForename = "" }) {
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
                                    label = { Text(text = "Adresse *") },
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

                        if (signUpUiState?.isLoading == true){
                            CircularProgressIndicator()
                        }

                        LaunchedEffect(key1 = viewModel?.hasUser){
                            if (viewModel?.hasUser == true){
                                onNavToMainPage.invoke()
                            }
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