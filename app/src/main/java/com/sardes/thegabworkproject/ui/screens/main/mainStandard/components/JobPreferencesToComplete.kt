package com.sardes.thegabworkproject.ui.screens.main.mainStandard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.ui.composition.TextFieldAuthItem
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.home.HomeStandardUiState
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.home.HomeStandardViewModel
import com.sardes.thegabworkproject.ui.theme.GWTypography
import kiwi.orbit.compose.ui.controls.*

@Composable
fun JobPreferencesToComplete(
    viewModel: HomeStandardViewModel?,
    uiState: HomeStandardUiState?,
    focusRequester: FocusRequester,
    preferences: MutableList<String>
) {

    var preference by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
    ) {

//        # HQH
        TextFieldAuthItem(
            label = "Diplome le plus élevé *",
            info = "Diplôme le plus élevé obtenu lors de votre parcours scolaire",
            value = uiState?.HQH ?: "",
            valueChange = { viewModel?.onHQHChange(it) }
        )
        Spacer(modifier = Modifier.height(20.dp))

//        # POSITION
        TextFieldAuthItem(
            label = "Métier *",
            info = "Votre métier, celui que vous métriez sur votre CV",
            value = uiState?.preferredJob ?: "",
            valueChange = { viewModel?.onPreferredJobChange(it) }
        )

        Text(
            text = "Ajoutez des tags d'emplois qui vous intéressent\n" +
                    "pour un flux personalisé à vos gouts",
            style = GWTypography.body1.copy(fontWeight = FontWeight.Medium)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {

            TextField(
                value = preference,
                onValueChange = { preference = it },
                label = { Text("Préférence") },
                singleLine = false,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                ),
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(focusRequester),
            )


            ButtonLinkPrimary(
                onClick = {
                    if (preference.isNotEmpty()) {
                        preferences.add(preference)
                        viewModel?.onPreferencesDEmploiChange(preferences)
                        preference = ""
                    }
                },
                modifier = Modifier
            ) { Text(text = "Ajouter") }
        }


        LazyColumn {
            preferences.forEachIndexed { index, preference ->
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(1f),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        KeyValueLarge(
                            key = "Préférence no ${index + 1}",
                            value = preference,
                            modifier = Modifier
                                .background(Color.White)
                                .weight(1f)
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        )

                        ButtonLinkCritical(onClick = { preferences.remove(preference) }) {
                            Text(text = "Retirer")
                        }
                    }
                }
            }
        }
    }
}

@Preview(name = "SeekerPreferences", showBackground = true)
@Composable
private fun PreviewSeekerPreferences() {
    JobPreferencesToComplete(
        null,
        null,
        FocusRequester.Default,
        emptyList<String>().toMutableStateList()
    )
}