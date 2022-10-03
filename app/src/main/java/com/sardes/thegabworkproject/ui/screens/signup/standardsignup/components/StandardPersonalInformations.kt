package com.sardes.thegabworkproject.ui.screens.signup.standardsignup.components

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.data.provider.CountryDataProvider.countries
import com.sardes.thegabworkproject.ui.composition.TextFieldAuthItem
import com.sardes.thegabworkproject.ui.screens.signup.standardsignup.SignupUiStateStandard
import com.sardes.thegabworkproject.ui.screens.signup.standardsignup.StandardSignUpViewModel
import com.sardes.thegabworkproject.ui.theme.GWTypography
import kiwi.orbit.compose.ui.controls.ButtonSecondary
import kiwi.orbit.compose.ui.controls.SegmentedSwitch
import kiwi.orbit.compose.ui.controls.Text
import kiwi.orbit.compose.ui.controls.TextField

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StandardPersonalInformations(
    uiState: SignupUiStateStandard?,
    viewModel: StandardSignUpViewModel?,
    focusRequester: FocusRequester,
    date: MutableState<String>,
    datePickerDialog: DatePickerDialog,
) {

    var selectedIndex by remember { mutableStateOf<Int?>(null) }

    var expanded by remember { mutableStateOf(false) }






    LazyColumn(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(30.dp)
    ) {
//        # NAME
        item {
            TextFieldAuthItem(
                label = "Nom *",
                info = "Votre nom de famille complet",
                value = uiState?.nom ?: "",
                valueChange = { viewModel?.onNomChange(it) }
            )
            Spacer(modifier = Modifier.height(20.dp))
        }

//        FORENAME
        item {
            TextFieldAuthItem(
                label = "Prénom",
                info = "Votre/vos prénom(s), ce champ peut être vide",
                value = uiState?.prenom ?: "",
                valueChange = { viewModel?.onPrenomChange(it) }
            )
            Spacer(modifier = Modifier.height(20.dp))
        }


//        # SEX
        item {

            Column {
                SegmentedSwitch(
                    optionFirst = { Text("Masculin") },
                    optionSecond = { Text("Feminin") },
                    selectedIndex = selectedIndex,
                    onOptionClick = { index ->
                        viewModel?.onSexeChange(if (index == 1) "Feminin" else "Masculin")
                        selectedIndex = index.takeIf { index != selectedIndex }
                    },
                    label = { Text("Sexe *") },
                )

                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "${uiState?.sexe}")
            }

            Spacer(modifier = Modifier.height(20.dp))

        }

//        # NATIONALITY
        item {
            Column {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        readOnly = true,
                        value = uiState?.nationalite ?: "",
                        onValueChange = { },
                        label = { Text("Nationalité *") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = expanded
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        modifier = Modifier.background(Color.Transparent),
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        countries.forEach { selectionOption ->
                            DropdownMenuItem(
                                onClick = {
//                                    selectedOption = selectionOption.nationality
                                    viewModel?.onNationaliteChange(selectionOption.nationality)
                                    expanded = false
                                }
                            ) {
                                Text(text = selectionOption.nationality)
                            }
                        }
                    }
                }


                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "${uiState?.nationalite}")
                Spacer(modifier = Modifier.height(20.dp))

            }
        }


        item {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text(
                    text = "Date de naissance",
                    style = GWTypography.body1.copy(fontWeight = FontWeight.Medium)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(text = date.value, style = GWTypography.body1.copy(fontWeight = FontWeight.Medium))

                    Spacer(modifier = Modifier.size(16.dp))

                    ButtonSecondary(onClick = { datePickerDialog.show() }) {
                        Text(text = "Sélectionner")
                    }
                    viewModel?.onDateNaissanceChange(date.value)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

    }

}

/*
@Preview(name = "PersonalInformations1SeekerSignUp", showSystemUi = true)
@Composable
private fun PreviewPersonalInformations1SeekerSignUp() {
    SeekerPersonalInformations()
}*/
