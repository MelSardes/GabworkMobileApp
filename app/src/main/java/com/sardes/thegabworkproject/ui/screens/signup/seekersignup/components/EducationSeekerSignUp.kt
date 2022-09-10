package com.sardes.thegabworkproject.ui.screens.signup.seekersignup.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.data.models.Education
import com.sardes.thegabworkproject.data.universities
import com.sardes.thegabworkproject.ui.composition.TextFieldAuthItem
import com.sardes.thegabworkproject.ui.screens.components.EducationCard
import com.sardes.thegabworkproject.ui.screens.signup.seekersignup.SeekerSignUpViewModel
import com.sardes.thegabworkproject.ui.screens.signup.seekersignup.SignupUiStateSeeker
import kiwi.orbit.compose.icons.Icons
import kiwi.orbit.compose.ui.controls.ButtonPrimary
import kiwi.orbit.compose.ui.controls.Icon
import kiwi.orbit.compose.ui.controls.Text
import kiwi.orbit.compose.ui.controls.TextField

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EducationSeekerSignUp(
    uiStateSeeker: SignupUiStateSeeker?,
    viewModel: SeekerSignUpViewModel?,
    focusRequester: FocusRequester,
    educations: MutableList<Education>
) {

    var expanded       by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(universities[0]) }



    var name        by remember { mutableStateOf("") }
    var degree      by remember { mutableStateOf("") }
    var domaine     by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var city        by remember { mutableStateOf("") }
    var begin       by remember { mutableStateOf("") }
    var end         by remember { mutableStateOf("") }





    LazyColumn(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxSize()
    ) {
        item {

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    readOnly = true,
                    value = selectedOption,
                    onValueChange = {  },
                    label = { Text("Nom de l'établissement") },
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
                    universities.forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                selectedOption = selectionOption
                                expanded = false
                            }
                        ) {
                            Text(text = selectionOption)
                            name = selectedOption
                        }
                    }
                }
            }

//            viewModel?.onActivityAreaChange(selectionOption.nom)
        }



        item {
            Column {
                TextFieldAuthItem(label = "Diplôme obtenu",
                    info = "Diplôme obtenu ... ",
                    value = degree,
                    valueChange = { degree = it })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        item {
            Column {
                TextFieldAuthItem(label = "Domaine d'étude",
                    info = "",
                    value = domaine,
                    valueChange = { domaine = it })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        item {
            Column {
                TextFieldAuthItem(label = "Description et informations supplémentaires",
                    info = "",
                    value = description,
                    valueChange = { description = it })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        item {

            TextField(
                value = city,
                onValueChange = { city = it },
                label = { Text("Ville") },
                info = { Text("Ville où se situe l'établissement") },
                leadingIcon = {
                    Icon(
                        Icons.Location, contentDescription = null
                    )
                },
                singleLine = false,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                placeholder = { Text("Ex: Sardesville") },
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            Column {
                TextFieldAuthItem(label = "Année de début",
                    info = "",
                    value = begin,
                    valueChange = { begin = it })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        item {
            Column {
                TextFieldAuthItem(label = "Année de fin",
                    info = "",
                    value = end,
                    valueChange = { end = it })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))

            ButtonPrimary(
                onClick = {
                    educations.add(
                        Education(
                            name,
                            degree,
                            domaine,
                            description,
                            city,
                            begin,
                            end
                        )
                    )

                    viewModel?.onEducationChange(educations)

                    name = ""
                    degree = ""
                    domaine = ""
                    description = ""
                    city = ""
                    begin = ""
                    end = ""
                }
            ) { Text(text = "Ajouter") }
            Spacer(modifier = Modifier.height(50.dp))
        }


        uiStateSeeker?.education?.forEach { education ->
            item {
                EducationCard(
                    Modifier.fillMaxWidth(),
                    education
                )

                Spacer(modifier = Modifier.height(30.dp))
            }
        }

        item {
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Preview(name = "EducationSeekerSignUp", showBackground = true)
@Composable
private fun PreviewEducationSeekerSignUp() {
    EducationSeekerSignUp(uiStateSeeker = null, viewModel = null, focusRequester = FocusRequester.Default,
        emptyList<Education>() as MutableList<Education>
    )
}