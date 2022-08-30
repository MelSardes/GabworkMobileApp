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
import androidx.compose.ui.text.input.KeyboardType
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
    modifier: Modifier = Modifier,
    postViewModel: NewPostViewModel?,
    onAdd: () -> Unit
) {

    val postUiState = postViewModel?.postUiState

    val scope = rememberCoroutineScope()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        if (postUiState?.postAddedStatus == true){
            scope.launch {
                postViewModel.resetPostAddedStatus()
                onAdd.invoke()
            }
        }


        item {
            OrbitText("Je suis à la recherche d'un.e ${postUiState?.postName}")
        }
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

        item {
            val focusRequester = remember { FocusRequester() }

            OrbitTextField(
                value = postUiState?.prerequis ?: "",
                onValueChange = { postViewModel?.onPrerequisChange(it) },
                label = { OrbitText("Prérequis") },
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
                onClick = { /*postViewModel?.addPost()*/ },
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