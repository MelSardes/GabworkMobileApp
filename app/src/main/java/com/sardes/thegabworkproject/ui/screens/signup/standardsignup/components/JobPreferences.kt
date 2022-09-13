package com.sardes.thegabworkproject.ui.screens.signup.standardsignup.components

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
import com.sardes.thegabworkproject.ui.screens.signup.standardsignup.StandardSignUpViewModel
import com.sardes.thegabworkproject.ui.theme.GWTypography
import kiwi.orbit.compose.ui.controls.ButtonLinkCritical
import kiwi.orbit.compose.ui.controls.ButtonLinkPrimary
import kiwi.orbit.compose.ui.controls.KeyValueLarge
import kiwi.orbit.compose.ui.controls.TextField
import kiwi.orbit.compose.ui.controls.Text as OrbitText

@Composable
fun JobPreferences(
    viewModel: StandardSignUpViewModel?,
    focusRequester: FocusRequester,
    preferences: MutableList<String>
) {

    var preference by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        OrbitText(
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
                label = { OrbitText("Préférence") },
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
            ) { OrbitText(text = "Ajouter") }
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
                            OrbitText(text = "Retirer")
                        }
                    }
                }
            }
        }


    }


}

@Preview(name = "SeekerPreferences")
@Composable
private fun PreviewSeekerPreferences() {
    JobPreferences(
        null,
        FocusRequester.Default,
        emptyList<String>() as MutableList<String>
    )
}