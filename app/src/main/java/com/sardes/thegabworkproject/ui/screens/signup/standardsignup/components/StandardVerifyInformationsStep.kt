package com.sardes.thegabworkproject.ui.screens.signup.standardsignup.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.ui.screens.signup.standardsignup.SignupUiStateStandard
import com.sardes.thegabworkproject.ui.screens.signup.standardsignup.StandardSignUpViewModel
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette.CoolGrey
import com.sardes.thegabworkproject.ui.theme.GWpalette.MaximumRed
import com.sardes.thegabworkproject.ui.theme.TailwindCSSColor.Green500
import kiwi.orbit.compose.ui.controls.Card
import kiwi.orbit.compose.ui.controls.KeyValueLarge
import kiwi.orbit.compose.ui.controls.Text as Orbittext

@Composable
fun StandardVerifyInformationsStep(
    isError: Boolean,
    uiStateSeeker: SignupUiStateStandard?,
    viewModel: StandardSignUpViewModel?,
    context: Context
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Green500)
    ) {

        if (isError) Orbittext(
            uiStateSeeker?.signUpError ?: "Erreur inconnue",
            modifier = Modifier.padding(10.dp),
            color = Color.Red,
            style = GWTypography.body1.copy(fontWeight = FontWeight.Bold)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White),
            contentPadding = PaddingValues(30.dp)
        ) {
            item {
                Text(
                    text = "Vérifiez que ces informations sont correctes\n " +
                        "avant de soumettre votre inscription",
                    style = GWTypography.body1.copy(fontWeight = FontWeight.Medium),
                    color = MaximumRed
                )
                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                Text(
                    "Informations personnelles et détails",
                    style = GWTypography.h6,
                    color = CoolGrey
                )
                Divider(thickness = 1.dp, color = CoolGrey)
                Spacer(modifier = Modifier.height(30.dp))
            }

            item {
                KeyValueLarge(key = "Nom", value = "${uiStateSeeker?.nom}")
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))
                KeyValueLarge(key = "Prénom", value = "${uiStateSeeker?.prenom}")
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))
                KeyValueLarge(key = "Email", value = "${uiStateSeeker?.email}")
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))
                KeyValueLarge(key = "Téléphone", value = "${uiStateSeeker?.telephone}")
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))
                KeyValueLarge(key = "Sexe", value = "${uiStateSeeker?.sexe}")
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))
                KeyValueLarge(key = "Ville", value = "${uiStateSeeker?.ville}")
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))
                KeyValueLarge(key = "Adresse", value = "${uiStateSeeker?.adresse}")
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))
                KeyValueLarge(key = "Nationalité", value = "${uiStateSeeker?.nationalite}")
            }

        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f),
            backgroundColor = Green500,
            onClick = { viewModel?.createUser(context) }
        ) {
            Orbittext(
                text = "Soumettre l'inscription",
                color = Color.White,
                style = GWTypography.h6,
                textAlign = TextAlign.Center
            )
        }

    }
}


@Preview
@Composable
fun StandardVerifyInformationsStepPreview() {
    StandardVerifyInformationsStep(false, null, null, context = LocalContext.current)

}
