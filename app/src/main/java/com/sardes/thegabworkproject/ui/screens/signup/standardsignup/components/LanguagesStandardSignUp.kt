package com.sardes.thegabworkproject.ui.screens.signup.standardsignup.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.data.models.languagesList
import com.sardes.thegabworkproject.ui.screens.signup.standardsignup.SignupUiStateStandard
import com.sardes.thegabworkproject.ui.screens.signup.standardsignup.StandardSignUpViewModel
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette
import kiwi.orbit.compose.ui.controls.ButtonLinkCritical
import kiwi.orbit.compose.ui.controls.Text
import kiwi.orbit.compose.ui.controls.TextField

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun LanguagesStandardSignUp(
    uiStateStandard: SignupUiStateStandard?,
    viewModel: StandardSignUpViewModel?,
    selectedLanguages: MutableList<String>
) {
//    var language by remember { mutableStateOf("") }
    var itemExpanded by remember { mutableStateOf(false) }
    var selectedLanguageAreaText by remember { mutableStateOf(languagesList[0]) }


    Column(modifier = Modifier.padding(30.dp)) {
        ExposedDropdownMenuBox(
            expanded = itemExpanded,
            onExpandedChange = {
                itemExpanded = !itemExpanded
            },
            modifier = Modifier.fillMaxWidth().padding(end = 20.dp)
        ) {
            TextField(
                readOnly = true,
                value = selectedLanguageAreaText,
                onValueChange = { },
                label = { Text("Langues *") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = itemExpanded
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
            ExposedDropdownMenu(
                modifier = Modifier.background(Color.Transparent),
                expanded = itemExpanded,
                onDismissRequest = { itemExpanded = false }
            ) {
                languagesList.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedLanguageAreaText = selectionOption
                            itemExpanded = false

                            selectedLanguages.add(selectedLanguageAreaText)
                            viewModel?.onLanguesChange(selectedLanguages)
                        }
                    ) {
                        Text(text = selectionOption)
                    }
                }
            }
        }


        LazyColumn(
            verticalArrangement = Arrangement.Center,
        ) {


            item {
                Spacer(modifier = Modifier.height(30.dp))

                if (selectedLanguages.isEmpty()) {
                    Text(
                        text = "Veuillez sélectionner une langue",
                        style = GWTypography.body1.copy(fontWeight = FontWeight.Bold),
                        color = GWpalette.EauBlue
                    )
                }

                uiStateStandard?.langues?.forEach { current ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(GWpalette.EauBlue)
                            .height(40.dp)
                            .padding(start = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(current)
                        ButtonLinkCritical(
                            onClick = {
                                selectedLanguages.remove(current)
                                viewModel?.onLanguesChange(selectedLanguages)
                            }
                        ) {
                            Text(text = "Retirer")
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }


    viewModel?.onLanguesChange(selectedLanguages)
}

@Preview(name = "LanguagesStandardSignUp", showBackground = true)
@Composable
private fun PreviewLanguagesStandardSignUp() {
    LanguagesStandardSignUp(null, null,selectedLanguages = emptyList<String>().toMutableStateList())
}