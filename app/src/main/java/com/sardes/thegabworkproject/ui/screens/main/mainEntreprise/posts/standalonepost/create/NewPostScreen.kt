package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.standalonepost.create

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kiwi.orbit.compose.ui.controls.ButtonBundleMedium
import kiwi.orbit.compose.ui.controls.SegmentedSwitch
import kotlinx.coroutines.launch
import kiwi.orbit.compose.icons.Icons as OrbitIcons
import kiwi.orbit.compose.ui.controls.Icon as OrbitIcon
import kiwi.orbit.compose.ui.controls.Text as OrbitText
import kiwi.orbit.compose.ui.controls.TextField as OrbitTextField

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun NewPostScreen(
    postViewModel: NewPostViewModel?,
    onAdd: () -> Unit,
) {

    val postUiState = postViewModel?.postUiState

    val scope = rememberCoroutineScope()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        if (postUiState?.postAddedStatus == true) {
            scope.launch {
                postViewModel.resetPostAddedStatus()
                onAdd.invoke()
            }
        }


        item {
            OrbitText(
                "Je suis à la recherche d'un.e ${postUiState?.postName}",
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(20.dp))
        }

//        Nom du post
        item {
            val focusRequester = remember { FocusRequester() }
            OrbitTextField(
                value = postUiState?.postName ?: "",
                onValueChange = { postViewModel?.onPostNameChange(it) },
                label = { OrbitText("Nom du post") },
                info = { OrbitText("Quelle personne cherchez-vous ?") },
                leadingIcon = {
                    OrbitIcon(
                        OrbitIcons.Passenger,
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
                placeholder = { OrbitText("Ex: Développeur Kotlin") },
            )

            Spacer(modifier = Modifier.height(10.dp))
        }

//        Salaire
        item {
            val focusRequester = remember { FocusRequester() }
            OrbitTextField(
                value = postUiState?.salaire ?: "",
                onValueChange = { postViewModel?.onSalaireChange(it) },
                label = { OrbitText("Salaire") },
                info = { OrbitText("Salaire moyen mensuel pour ce post") },
                leadingIcon = {
                    OrbitIcon(
                        OrbitIcons.Money,
                        contentDescription = null
                    )
                },
                singleLine = false,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
            )

            Spacer(modifier = Modifier.height(10.dp))
        }

//        Adresse
        item {
            val focusRequester = remember { FocusRequester() }

            OrbitTextField(
                value = postUiState?.adresse ?: "",
                onValueChange = { postViewModel?.onAdresseChange(it) },
                label = { OrbitText("Adresse") },
                info = { OrbitText("Où le post se situe-t-il ?") },
                leadingIcon = {
                    OrbitIcon(
                        OrbitIcons.City,
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
                placeholder = { OrbitText("Ex: 42 Rue des légendes, Sardesville, Haut-Ogooué") },
            )

            Spacer(modifier = Modifier.height(10.dp))
        }

//        Ville
        item {
            val focusRequester = remember { FocusRequester() }

            OrbitTextField(
                value = postUiState?.ville ?: "",
                onValueChange = { postViewModel?.onVilleChange(it) },
                label = { OrbitText("Ville") },
                info = { OrbitText("Précisez de nouveau la ville") },
                leadingIcon = {
                    OrbitIcon(
                        OrbitIcons.Location,
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
                placeholder = { OrbitText("Ex: Sardesville") },
            )

            Spacer(modifier = Modifier.height(10.dp))
        }

//        Province
        item {
            val focusRequester = remember { FocusRequester() }

            OrbitTextField(
                value = postUiState?.province ?: "Précisez de nouveau la province",
                onValueChange = { postViewModel?.onProvinceChange(it) },
                label = { OrbitText("Province") },
                info = { OrbitText("") },
                leadingIcon = {
                    OrbitIcon(
                        OrbitIcons.Map,
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
                placeholder = { OrbitText("Ex: Haut-Ogooué") },
            )

            Spacer(modifier = Modifier.height(10.dp))
        }

//        Domaine
        item {
            val focusRequester = remember { FocusRequester() }

            OrbitTextField(
                value = postUiState?.domaine ?: "",
                onValueChange = { postViewModel?.onDomaineChange(it) },
                label = { OrbitText("Domaine") },
                info = { OrbitText("Domaine") },
                leadingIcon = {
                    OrbitIcon(
                        OrbitIcons.Ai,
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
                placeholder = { OrbitText("Ex: IT") },
            )

            Spacer(modifier = Modifier.height(10.dp))
        }


//            Experience
        item {

            var selectedIndex by remember { mutableStateOf<Int?>(null) }

            SegmentedSwitch(
                optionFirst = { OrbitText("Senior") },
                optionSecond = { OrbitText("Junior") },
                selectedIndex = selectedIndex,
                onOptionClick = { index ->
                    postViewModel?.onExperienceChange(if (index == 1) "Temps partiel" else "Temps plein")
                    selectedIndex = index.takeIf { index != selectedIndex }
                },
                label = { OrbitText("Type d'emploi") },
            )

            Spacer(modifier = Modifier.height(10.dp))
        }


//        Type d'emploi
        item {

            var selectedIndex by remember { mutableStateOf<Int?>(null) }

            SegmentedSwitch(
                optionFirst = { OrbitText("Temps plein") },
                optionSecond = { OrbitText("Temps partiel") },
                selectedIndex = selectedIndex,
                onOptionClick = { index ->
                    postViewModel?.onTypeEmploiChange(if (index == 1) "Temps partiel" else "Temps plein")
                    selectedIndex = index.takeIf { index != selectedIndex }
                },
                label = { OrbitText("Type d'emploi") },
            )

            Spacer(modifier = Modifier.height(10.dp))
        }

//        Emploi ou stage
        item {
            var selectedIndex by remember { mutableStateOf<Int?>(null) }
            SegmentedSwitch(
                optionFirst = { OrbitText("Emploi") },
                optionSecond = { OrbitText("Stage") },
                selectedIndex = selectedIndex,
                onOptionClick = { index ->
                    postViewModel?.onEmploiOuStagehange(if (index == 1) "Stage" else "Emploi")
                    selectedIndex = index.takeIf { index != selectedIndex }
                },
                label = { OrbitText("Emploi ou stage ?") },
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

//        Prérequis
        item {
            val focusRequester = remember { FocusRequester() }

            OrbitTextField(
                value = postUiState?.prerequis ?: "",
                onValueChange = { postViewModel?.onPrerequisChange(it) },
                label = { OrbitText("Prérequis") }, /* TODO: MAKE A LIST FOR <PREREQUIS>*/
                info = { OrbitText("Que voulez-vous pour ce post ?") },
                singleLine = false,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

//        Description du post
        item {
            val focusRequester = remember { FocusRequester() }

            OrbitTextField(
                value = postUiState?.descriptionEmploi ?: "",
                onValueChange = { postViewModel?.onDescriptionEmploiChange(it) },
                label = { OrbitText("Description") },
                info = { OrbitText("Description du post") },
                leadingIcon = {
                    OrbitIcon(
                        OrbitIcons.Document,
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
            )

        }


        item {
            ButtonBundleMedium(
                onClick = {
                    postViewModel?.addPost()
                },
                modifier = Modifier.padding(10.dp)
            ) {
                Text("Créer post")
            }
        }
    }
}

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@OptIn(ExperimentalMaterialApi::class)
//@Preview(showBackground = true)
@Composable
fun SelectFieldScreenInner() {
    val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            readOnly = true,
            value = selectedOptionText,
            onValueChange = { },
            label = { Text("Label") },
            trailingIcon = {
                TrailingIcon(
                    expanded = expanded
                )
            },
            modifier = Modifier
                .border(
                    BorderStroke(
                        width = 1.dp,
                        brush = Brush.horizontalGradient(listOf(Color.Blue, Color.Yellow))
                    ),
                    shape = RoundedCornerShape(50)
                )
                .padding(horizontal = 10.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = typography.body1
        )
        ExposedDropdownMenu(
            modifier = Modifier.background(Color.Transparent),
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                    }
                ) {
                    Text(text = selectionOption)
                }
            }
        }
    }
}