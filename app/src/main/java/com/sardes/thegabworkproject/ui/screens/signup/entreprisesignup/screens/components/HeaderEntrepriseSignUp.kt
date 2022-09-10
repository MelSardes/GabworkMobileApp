package com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.screens.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.ui.theme.GWTypography

@Composable
fun HeaderEntrepriseSignUp(
    stepIndicator: Int) {
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
                kiwi.orbit.compose.ui.controls.Text(
                    when (stepIndicator) {
                        1 -> "Étape 1/4 --  0%"
                        2 -> "Étape 2/4 --  25%"
                        3 -> "Étape 3/4 --  50%"
                        4 -> "Étape 4/4 --  75%"
                        else -> "100%"
                    },
                    color = Color.White,
                    style = GWTypography.h5.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.width(5.dp))
                kiwi.orbit.compose.ui.controls.Text(
                    "complet",
                    color = Color.LightGray,
                    style = GWTypography.body2
                )
            }
        }
        kiwi.orbit.compose.ui.controls.Text(
            text =
            when (stepIndicator) {
                1 -> "Details du compte"
                2 -> "Informations sur l'entreprise 1/2"
                3 -> "Informations sur l'entreprise 2/2"
                4 -> "Logo de l'entreprise"
                5 -> "Vérification des informations"
                else -> ""
            },
            color = Color.White,
            style = GWTypography.h6
        )

//                STEPS
        StepsBarsSignUpEntreprise(stepIndicator)
    }
}
@Preview(name = "HeaderEntrepriseSignUp")


@Composable
private fun PreviewHeaderEntrepriseSignUp() {
    HeaderEntrepriseSignUp(3)
}