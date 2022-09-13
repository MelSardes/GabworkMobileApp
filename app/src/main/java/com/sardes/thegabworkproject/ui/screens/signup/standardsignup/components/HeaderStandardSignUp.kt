package com.sardes.thegabworkproject.ui.screens.signup.standardsignup.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.ui.theme.GWTypography
import kiwi.orbit.compose.ui.controls.Text as OrbitText

@Composable
fun HeaderStandardSignUp(stepIndicator: Int) {
    Column(
        modifier = Modifier
            .fillMaxHeight(0.14f)
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
//            HEADER
        Column (
            horizontalAlignment = Alignment.Start
                ){
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                OrbitText(
                    when (stepIndicator) {
                        1 -> "Étape 1/5   --    0%"
                        2 -> "Étape 2/5   --    20%"
                        3 -> "Étape 3/5   --    40%"
                        4 -> "Étape 4/5   --    60%"
                        5 -> "Étape 5/5   --    80%"
                        else -> "100%"
                    },
                    color = Color.White,
                    style = GWTypography.body1.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.width(5.dp))
                OrbitText(
                    "complet",
                    color = Color.LightGray,
                    style = GWTypography.body2
                )
            }


            OrbitText(
                text =
                when (stepIndicator) {
                    1 -> "Details du compte"
                    2 -> "Informations personnelles"
                    3 -> "Autres informations"
                    4 -> "Photo de profil"
                    5 -> "Vérification des informations"
                    else -> ""
                },
                color = Color.White,
                style = GWTypography.body1
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

//                STEPS
        StepsBarsStandardSignUp(stepIndicator)
    }
}



@Preview(name = "Header", showBackground = true, showSystemUi = true, backgroundColor = 0xFFDD3C3C)
@Composable
private fun PreviewHeader() {
    HeaderStandardSignUp(5)
}