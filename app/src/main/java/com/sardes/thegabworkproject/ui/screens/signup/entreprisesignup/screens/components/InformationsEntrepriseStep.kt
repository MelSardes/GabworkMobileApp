package com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.EntrepriseAccountSignUpViewModel
import com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.SignupUiStateEntreprise
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.TailwindCSSColor
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.Card
import kiwi.orbit.compose.ui.controls.KeyValueLarge
import kiwi.orbit.compose.ui.controls.Text as OrbitText

@Composable
fun InformationsEntrepriseStep(
    isError: Boolean,
    entrepriseUiState: SignupUiStateEntreprise?,
    viewModel: EntrepriseAccountSignUpViewModel?,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TailwindCSSColor.Green500)
    ) {

        if (isError) OrbitText(
            entrepriseUiState?.signUpError ?: "Erreur inconnue",
            modifier = Modifier.padding(10.dp),
            color = Color.Red,
            style = GWTypography.h6.copy(fontWeight = FontWeight.Bold)
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
                KeyValueLarge(
                    key = "Nom de l'entreprise",
                    value = entrepriseUiState?.entrepriseName!!,
                    modifier = Modifier.background(Color.White)
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                KeyValueLarge(
                    key = "Email",
                    value = entrepriseUiState?.entrepriseMail!!,
                    modifier = Modifier.background(Color.White)
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                KeyValueLarge(
                    key = "Site web",
                    value = entrepriseUiState?.website!!,
                    modifier = Modifier.background(Color.White)
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                KeyValueLarge(
                    key = "Téléphone",
                    value = entrepriseUiState?.phone!!,
                    modifier = Modifier.background(Color.White)
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                KeyValueLarge(
                    key = "Adresse",
                    value = entrepriseUiState?.address!!,
                    modifier = Modifier.background(Color.White)
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                KeyValueLarge(
                    key = "Date de création de l'entreprise",
                    value = entrepriseUiState?.creationDate!!,
                    modifier = Modifier.background(Color.White)
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                KeyValueLarge(
                    key = "Secteur d'activité",
                    value = entrepriseUiState?.activityArea!!,
                    modifier = Modifier.background(Color.White)
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                KeyValueLarge(
                    key = "Nombre d'employés",
                    value = entrepriseUiState?.employes!!,
                    modifier = Modifier.background(Color.White)
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                KeyValueLarge(
                    key = "Desription",
                    value = entrepriseUiState?.description!!,
                    modifier = Modifier.background(Color.White)
                )
                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                entrepriseUiState?.city?.forEach {
                    OrbitText(it)
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f),
            backgroundColor = OrbitTheme.colors.primary.normal,
            onClick = { viewModel?.createUser(context) }
        ) {
            OrbitText(
                text = "Soumettre l'inscription",
                color = Color.White,
                style = GWTypography.h6,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview(name = "InformationsEntrepriseStep")
@Composable
private fun PreviewInformationsEntrepriseStep() {
    InformationsEntrepriseStep(false, null, null)
}