package com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.data.provider.ActivityAreaDataProvider
import com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.EntrepriseAccountSignUpViewModel
import com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.SignupUiStateEntreprise
import com.sardes.thegabworkproject.ui.theme.GWpalette
import kiwi.orbit.compose.icons.Icons
import kiwi.orbit.compose.ui.controls.Icon
import kiwi.orbit.compose.ui.controls.TextField
import kiwi.orbit.compose.ui.controls.Text as OrbitText

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EntrepriseInformations1(
    entrepriseUiState: SignupUiStateEntreprise?,
    viewModel: EntrepriseAccountSignUpViewModel?,
    focusRequester: FocusRequester = FocusRequester.Default,
) {
    var activityAreaexpanded by remember { mutableStateOf(false) }
    var selectedOptionactivityAreaText by remember { mutableStateOf(ActivityAreaDataProvider.secteurs[0].nom) }

    var city by remember { mutableStateOf("") }
    val selectedCities = remember { mutableStateListOf<String>() }


    LazyColumn(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(30.dp)
    ) {

//        # NAME
        item {
            TextField(
                value = entrepriseUiState?.entrepriseName ?: "",
                onValueChange = { viewModel?.onEntrerpiseNameChange(it) },
                label = { OrbitText("Nom") },
                info = { OrbitText("Le nom commercial de votre entreprise") },
                singleLine = false,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                placeholder = { OrbitText("Ex: Sardes Corp.") }
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
                    label = { OrbitText("Secteurs d'activités") },
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
                            OrbitText(text = selectionOption.nom)
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
                label = { OrbitText("Télephone") },
                info = { OrbitText("Numéro de téléphone principale de l'entreprise") },
                singleLine = false,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                ),
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .focusRequester(focusRequester),
                placeholder = { OrbitText("Ex: 066 15 77 70") }
            )
        }


//        # CITY
        item {
            TextField(
                value = city,
                onValueChange = { city = it },
                label = { OrbitText("Villes") },
                info = { OrbitText("Dans quelles villes êtes-vous présent ?") },
                trailingIcon = { Icon(Icons.Plus, contentDescription = null) },
                onTrailingIconClick = {
                    if (city.isNotEmpty()) {
                        selectedCities.add(city)
                        city = ""
                        viewModel?.onCityChange(selectedCities)
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

            selectedCities.forEach {
                OrbitText(
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
                label = { OrbitText("adresse") },
                info = { OrbitText("Adresse du siège social") },
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


@Preview(name = "EntrepriseInformations1")
@Composable
private fun PreviewEntrepriseInformations1() {
    EntrepriseInformations1(null, null)
}