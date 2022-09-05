package com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.screens

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.net.Uri
import android.widget.DatePicker
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.data.ActivityAreaDataProvider
import com.sardes.thegabworkproject.test.surfaceColor
import com.sardes.thegabworkproject.ui.composition.FullscreenLoadingIndicator
import com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.EntrepriseAccountSignUpViewModel
import com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.SignupUiState
import com.sardes.thegabworkproject.ui.screens.signup.imageUri
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette
import com.sardes.thegabworkproject.ui.theme.TailwindCSSColor
import com.sardes.thegabworkproject.ui.theme.TailwindCSSColor.Green500
import kiwi.orbit.compose.icons.Icons
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.*
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "MaterialDesignInsteadOrbitDesign")
@Preview
@Composable
fun EntrepriseAccountSignUpScreen(
    viewModel: EntrepriseAccountSignUpViewModel? = null,
    navToEntrepriseInterface: () -> Unit = {},
    startSigningUp: () -> Unit = {},
    onNavToLoginPage: () -> Unit = {},
) {


    val entrepriseUiState = viewModel?.signUpUiState
    val isError = entrepriseUiState?.signUpError != null


    var stepIndicator by remember { mutableStateOf(1) }

    val focusRequester = remember { FocusRequester() }

    if (entrepriseUiState?.isLoading == true) {
        FullscreenLoadingIndicator(text = "Création du compte en cours ...")
    }


    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = surfaceColor
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.2f)
                    .padding(30.dp)
            ) {
//            HEADER
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                ) {
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            when (stepIndicator) {
                                1 -> "Étape 1/4 --  0%"
                                2 -> "Étape 2/4 --  25%"
                                3 -> "Étape 3/4 --  50%"
                                4 -> "Étape 4/4 --  75%"
                                else -> ""
                            },
                            color = White,
                            style = GWTypography.h5.copy(fontWeight = FontWeight.Bold)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            "complet",
                            color = Color.LightGray,
                            style = GWTypography.body2
                        )
                    }
                }
                Text(
                    text =
                    when (stepIndicator) {
                        1 -> "Details du compte"
                        2 -> "Informations sur l'entreprise 1/2"
                        3 -> "Informations sur l'entreprise 2/2"
                        4 -> "Logo de l'entreprise"
                        5 -> "Vérification des informations"
                        else -> ""
                    },
                    color = White,
                    style = GWTypography.h6
                )

//                STEPS
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.Center
                ) {
                    when (stepIndicator) {
                        5 -> {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(5.dp)
                                    .height(5.dp)
                                    .clip(CircleShape)
                                    .background(OrbitTheme.colors.primary.normal)
                            )
                        }
                        else -> {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(5.dp)
                                    .height(5.dp)
                                    .clip(CircleShape)
                                    .background(if (stepIndicator == 1) Color.White else TailwindCSSColor.Green500)
                            )
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(5.dp)
                                    .height(5.dp)
                                    .clip(CircleShape)
                                    .background(
                                        when (stepIndicator) {
                                            1 -> GWpalette.CoolGrey
                                            2 -> Color.White
                                            else -> TailwindCSSColor.Green500
                                        }
                                    )
                            )
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(5.dp)
                                    .height(5.dp)
                                    .clip(CircleShape)
                                    .background(
                                        when (stepIndicator) {
                                            1 -> GWpalette.CoolGrey
                                            2 -> GWpalette.CoolGrey
                                            3 -> Color.White
                                            else -> TailwindCSSColor.Green500
                                        }
                                    )
                            )
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(5.dp)
                                    .height(5.dp)
                                    .clip(CircleShape)
                                    .background(
                                        when (stepIndicator) {
                                            1 -> GWpalette.CoolGrey
                                            2 -> GWpalette.CoolGrey
                                            3 -> GWpalette.CoolGrey
                                            4 -> Color.White
                                            else -> TailwindCSSColor.Green500
                                        }
                                    )
                            )
                        }


                    }
                }
            }


            Column(
                modifier = Modifier
                    .fillMaxHeight(1f)
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(
                        when (stepIndicator) {
                            5 -> {
                                Brush.horizontalGradient(
                                    colorStops = arrayOf(
                                        Pair(0.3f, surfaceColor),
                                        Pair(1f, Color.LightGray)
                                    )
                                )
                            }
                            else -> {
                                Brush.horizontalGradient(
                                    colorStops = arrayOf(
                                        Pair(0.3f, surfaceColor),
                                        Pair(1f, Green500)
                                    )
                                )
                            }
                        }
                    )
            ) {

                Card(
                    modifier = Modifier
                        .weight(0.92f)
                        .fillMaxWidth(),
                    backgroundColor = Color.White,
                    shape = RoundedCornerShape(24.dp)
                ) {

                    //================================================
                    //      CONTENT
                    //================================================

                    if (stepIndicator == 1) AccountDetails(
                        focusRequester,
                        entrepriseUiState,
                        viewModel
                    )
                    if (stepIndicator == 2) EntrepriseInformations1(
                        focusRequester,
                        entrepriseUiState,
                        viewModel
                    )
                    if (stepIndicator == 3) EntrepriseInformations2(
                        focusRequester,
                        entrepriseUiState,
                        viewModel
                    )
                    if (stepIndicator == 4) EntrepriseInformations3Logo(
                        focusRequester,
                        viewModel
                    )
                    if (stepIndicator == 5) {
                        Informations(isError, entrepriseUiState, viewModel)

                    }
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.08f)
                ) {
                    when (stepIndicator){
                        1 -> {
                            Card(
                                backgroundColor = surfaceColor,
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .fillMaxHeight(),
                            ) {
                                Text(
                                    text = "Précédent",
                                    color = LightGray,
                                    style = GWTypography.h6,
                                    textAlign = TextAlign.Center
                                )
                            }

                        }
                        else -> {
                            Card(
                                backgroundColor = surfaceColor,
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .fillMaxHeight(),
                                onClick = { stepIndicator -= 1 },
                            ) {
                                Text(
                                    text = "Précédent",
                                    color = Color.White,
                                    style = GWTypography.h6,
                                    textAlign = TextAlign.Center
                                )
                            }

                        }
                    }

                    when (stepIndicator) {
                        5 -> {
                            Card(
                                backgroundColor = Color.LightGray,
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .fillMaxHeight(),
                            ) {
                                Text(
                                    text = "Suivant",
                                    color = Color.White,
                                    style = GWTypography.h6,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        else -> {
                            Card(
                                backgroundColor = Green500,
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .fillMaxHeight(),
                                onClick = { stepIndicator += 1 }
                            ) {
                                Text(
                                    text = "Suivant",
                                    color = White,
                                    style = GWTypography.h6,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = viewModel?.hasUser) {
        if (viewModel?.hasUser == true) {
            navToEntrepriseInterface.invoke()
        }
    }
}

@Composable
private fun Informations(
    isError: Boolean,
    entrepriseUiState: SignupUiState?,
    viewModel: EntrepriseAccountSignUpViewModel?,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Green500)
    ) {

        if (isError) Text(
            entrepriseUiState?.signUpError ?: "Erreur inconnue",
            modifier = Modifier.padding(10.dp),
            color = Color.Red,
            style = GWTypography.h6.copy(fontWeight = FontWeight.Bold)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .clip(RoundedCornerShape(24.dp))
                .background(White),
            contentPadding = PaddingValues(30.dp)
        ) {
            item {
                KeyValueLarge(
                    key = "Nom de l'entreprise",
                    value = entrepriseUiState?.entrepriseName!!,
                    modifier = Modifier.background(White)
                )
            }
            item {
                KeyValueLarge(
                    key = "Email",
                    value = entrepriseUiState?.entrepriseMail!!,
                    modifier = Modifier.background(White)
                )
            }
            item {
                KeyValueLarge(
                    key = "Site web",
                    value = entrepriseUiState?.website!!,
                    modifier = Modifier.background(White)
                )
            }
            item {
                KeyValueLarge(
                    key = "Téléphone",
                    value = entrepriseUiState?.phone!!,
                    modifier = Modifier.background(White)
                )
            }
            item {
                KeyValueLarge(
                    key = "Adresse",
                    value = entrepriseUiState?.address!!,
                    modifier = Modifier.background(White)
                )
            }
            item {
                KeyValueLarge(
                    key = "Date de création de l'entreprise",
                    value = entrepriseUiState?.creationDate!!,
                    modifier = Modifier.background(White)
                )
            }
            item {
                KeyValueLarge(
                    key = "Secteur d'activité",
                    value = entrepriseUiState?.activityArea!!,
                    modifier = Modifier.background(White)
                )
            }
            item {
                KeyValueLarge(
                    key = "Nombre d'employés",
                    value = entrepriseUiState?.employes!!,
                    modifier = Modifier.background(White)
                )
            }
            item {
                KeyValueLarge(
                    key = "Desription",
                    value = entrepriseUiState?.description!!,
                    modifier = Modifier.background(White)
                )
            }

            item {
                entrepriseUiState?.city?.forEach {
                    Text(it)
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f),
            backgroundColor = OrbitTheme.colors.primary.normal,
            onClick = { viewModel?.createUser(context) }
        ) {
            Text(
                text = "Soumettre",
                color = White,
                style = GWTypography.h6,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
private fun EntrepriseInformations3Logo(
    focusRequester: FocusRequester,
    viewModel: EntrepriseAccountSignUpViewModel?,
) {

    val selectImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri.value = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OrbitTheme.colors.surface.strong)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.LightGray)
        ) {
            if (imageUri.value != null) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = rememberAsyncImagePainter(model = imageUri.value),
                    contentScale = ContentScale.Crop,
                    contentDescription = "image",
                )
            } else {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.perroquet),
                    contentScale = ContentScale.Crop,
                    contentDescription = "image"
                )
            }
            viewModel?.onLogoChange(imageUri.value)
        }


        ButtonSecondary(
            onClick = { selectImage.launch("image/*") },
            modifier = Modifier
                .fillMaxWidth()
        )
        {
            Text(
                "Sélectionnez votre logo",
                style = GWTypography.h6,
                textAlign = TextAlign.Center
            )
        }
    }


}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun EntrepriseInformations2(
    focusRequester: FocusRequester,
    entrepriseUiState: SignupUiState?,
    viewModel: EntrepriseAccountSignUpViewModel?,
) {

    val employesOptions = listOf(
        "1 - 50",
        "51 - 100",
        "101 - 200",
        "201 - 500",
        "501 - 1000",
        "plus de 1000",
    )
    var employesExpanded by remember { mutableStateOf(false) }
    var selectedEmployesNumber by remember { mutableStateOf(employesOptions[0]) }


    val context = LocalContext.current
    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val date = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = "$dayOfMonth/$month/$year"
        }, year, month, day
    )


    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(30.dp)
    ) {

        LazyColumn(verticalArrangement = Arrangement.Center) {

//            # WEBSITE
            item {
                TextField(
                    value = entrepriseUiState?.website ?: "",
                    onValueChange = { viewModel?.onWebsiteChange(it) },
                    label = { Text("Site web") },
                    info = { Text("Site web officiel de l'entreprise s'il existe") },
                    singleLine = false,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                )

            }

            item {
                ExposedDropdownMenuBox(
                    expanded = employesExpanded,
                    onExpandedChange = {
                        employesExpanded = !employesExpanded
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        readOnly = true,
                        value = selectedEmployesNumber,
                        onValueChange = {  },
                        label = { Text("Nombre d'employés") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = employesExpanded
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        modifier = Modifier.background(Color.Transparent),
                        expanded = employesExpanded,
                        onDismissRequest = { employesExpanded = false }
                    ) {
                        employesOptions.forEach { selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedEmployesNumber = selectionOption
                                    employesExpanded = false
                                }
                            ) {
                                Text(text = selectionOption)
                                viewModel?.onEmployesChange(selectionOption)
                            }
                        }
                    }
                }

            }
//            # ENTREPRISE CREATION DATE
            item {
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                    Text(
                        text = "Date de création de l'entreprise",
                        fontWeight = FontWeight.SemiBold
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(text = date.value, fontWeight = FontWeight.Bold)

                        Spacer(modifier = Modifier.size(16.dp))

                        ButtonSecondary(onClick = { datePickerDialog.show() }) {
                            Text(text = "Sélectionner")
                        }
                        viewModel?.onCreationDateChange(date.value)
                    }
                }

            }


            item {

                TextField(
                    value = entrepriseUiState?.description ?: "",
                    onValueChange = { viewModel?.onEntrepriseDescriptionChange(it) },
                    label = { Text("Description") },
                    info = { Text("Décrivez en quelque phrases votre entreprise") },
                    singleLine = false,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                )

            }

        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun EntrepriseInformations1(
    focusRequester: FocusRequester,
    entrepriseUiState: SignupUiState?,
    viewModel: EntrepriseAccountSignUpViewModel?,
) {
    var activityAreaexpanded by remember { mutableStateOf(false) }
    var selectedOptionactivityAreaText by remember { mutableStateOf(ActivityAreaDataProvider.secteurs[0].nom) }

    var city by remember { mutableStateOf("") }
    val setCities = remember { mutableStateListOf<String>() }


    LazyColumn(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(30.dp)) {

//        # NAME
        item {
            TextField(
                value = entrepriseUiState?.entrepriseName ?: "",
                onValueChange = { viewModel?.onEntrerpiseNameChange(it) },
                label = { Text("Nom") },
                info = { Text("Le nom commercial de votre entreprise") },
                singleLine = false,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                placeholder = { Text("Ex: Sardes Corp.") }
            )
        }

//        # ACTIVITY AREA
        item {
            ExposedDropdownMenuBox(
                expanded = activityAreaexpanded,
                onExpandedChange = {
                    activityAreaexpanded = !activityAreaexpanded
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    readOnly = true,
                    value = selectedOptionactivityAreaText,
                    onValueChange = {  },
                    label = { Text("Secteurs d'activités") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = activityAreaexpanded
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                ExposedDropdownMenu(
                    modifier = Modifier.background(Color.Transparent),
                    expanded = activityAreaexpanded,
                    onDismissRequest = { activityAreaexpanded = false }
                ) {
                    ActivityAreaDataProvider.secteurs.forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                selectedOptionactivityAreaText = selectionOption.nom
                                activityAreaexpanded = false
                            }
                        ) {
                            Text(text = selectionOption.nom)
                            viewModel?.onActivityAreaChange(selectionOption.nom)
                        }
                    }
                }
            }
        }

//        # PHONE
        item {
            TextField(
                value = entrepriseUiState?.phone ?: "",
                onValueChange = { viewModel?.onEntreprisePhoneChange(it) },
                label = { Text("Télephone") },
                info = { Text("Numéro de téléphone principale de l'entreprise") },
                singleLine = false,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                ),
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .focusRequester(focusRequester),
                placeholder = { Text("Ex: 066 15 77 70") }
            )
        }


//        # CITY
        item {
            TextField(
                value = city,
                onValueChange = { city = it },
                label = { Text("Villes") },
                info = { Text("Dans quelles villes êtes-vous présent ?") },
                trailingIcon = { Icon(Icons.Plus, contentDescription = null) },
                onTrailingIconClick = {
                    if (city.isNotEmpty()) {
                        setCities.add(city)
                        city = ""
                        viewModel?.onCityChange(setCities)
                    }
                },
                singleLine = false,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
            )

            setCities.forEach {
                Text(
                    it,
                    color = GWpalette.Gunmetal,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .border(width = 0.5.dp, color = GWpalette.Gunmetal)
                        .background(GWpalette.EauBlue)
                        .padding(vertical = 5.dp, horizontal = 15.dp),
                )
            }
        }

//        # ADDRESS
        item {
            TextField(
                value = entrepriseUiState?.address ?: "",
                onValueChange = { viewModel?.onAddressChange(it) },
                label = { Text("adresse") },
                info = { Text("Adresse du siège social") },
                singleLine = false,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
            )
        }
    }
}

@Composable
private fun AccountDetails(
    focusRequester: FocusRequester,
    entrepriseUiState: SignupUiState?,
    viewModel: EntrepriseAccountSignUpViewModel?,
) {
    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(30.dp)) {
        //       # EMAIL
        TextField(
            value = entrepriseUiState?.entrepriseMail ?: "",
            onValueChange = { viewModel?.onEntrepriseEmailChangeSignUp(it) },
            label = { Text("Email") },
            info = { Text("Ce mail ne peut être utilisée qu'une seule fois sur la plateforme") },
            leadingIcon = {
                Icon(
                    Icons.Email,
                    contentDescription = null
                )
            },
            singleLine = false,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            placeholder = { Text("Ex: exemple@exemple.com") }
        )

        Spacer(modifier = Modifier.height(30.dp))

//        # PASSWORD
        PasswordTextField(
            value = entrepriseUiState?.password ?: "",
            onValueChange = { viewModel?.onPasswordChangeSignUp(it) },
            label = { Text("Mot de passe") },
            info = { Text("8 caractères minimum") },
            leadingIcon = {
                Icon(
                    Icons.Lock,
                    contentDescription = null
                )
            },
            singleLine = false,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
        )

        Spacer(modifier = Modifier.height(30.dp))

//        # CONFRIM PASSWORD
        PasswordTextField(
            value = entrepriseUiState?.confirmPassword ?: "",
            onValueChange = { viewModel?.onConfirmPasswordChange(it) },
            label = { Text("Confirmer le mot de passe") },
            info = { Text("Saisissez de nouveau votre mot de passe") },
            leadingIcon = {
                Icon(
                    Icons.Lock,
                    contentDescription = null
                )
            },
            singleLine = false,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
        )
    }
}

