package com.sardes.thegabworkproject.ui.screens.signup.seekersignup.components

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
import com.sardes.thegabworkproject.ui.screens.components.EducationCard
import com.sardes.thegabworkproject.ui.screens.components.ExperienceCard
import com.sardes.thegabworkproject.ui.screens.signup.seekersignup.SeekerSignUpViewModel
import com.sardes.thegabworkproject.ui.screens.signup.seekersignup.SignupUiStateSeeker
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette.CoolGrey
import com.sardes.thegabworkproject.ui.theme.GWpalette.LackCoral
import com.sardes.thegabworkproject.ui.theme.GWpalette.MaximumRed
import com.sardes.thegabworkproject.ui.theme.TailwindCSSColor.Green500
import kiwi.orbit.compose.ui.controls.Card
import kiwi.orbit.compose.ui.controls.KeyValue
import kiwi.orbit.compose.ui.controls.KeyValueLarge
import kiwi.orbit.compose.ui.controls.Text as Orbittext

@Composable
fun SeekerVerifyInformationsStep(
    isError: Boolean,
    uiStateSeeker: SignupUiStateSeeker?,
    viewModel: SeekerSignUpViewModel?,
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

            item {
                Spacer(modifier = Modifier.height(30.dp))
                KeyValueLarge(key = "Diplôme de plus haut degré", value = "${uiStateSeeker?.HQH}")
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))
                KeyValueLarge(key = "Métier", value = "${uiStateSeeker?.metier}")
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))
                Divider(thickness = 3.dp, color = LackCoral)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Langues", style = GWTypography.h6, color = CoolGrey)
                Divider(thickness = 1.dp, color = CoolGrey)
                Spacer(modifier = Modifier.height(30.dp))
            }

            uiStateSeeker?.langues?.forEachIndexed { i, e ->
                item {
                    KeyValue(key = "Langue $i", value = e)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))
                Divider(thickness = 3.dp, color = LackCoral)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Compétences", style = GWTypography.h6, color = CoolGrey)
                Divider(thickness = 1.dp, color = CoolGrey)
                Spacer(modifier = Modifier.height(30.dp))
            }



            uiStateSeeker?.competences?.forEach {
                item {
                    KeyValue(key = it.level, value = it.title)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }


            item {
                Spacer(modifier = Modifier.height(30.dp))
                Divider(thickness = 3.dp, color = LackCoral)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Éduacation", style = GWTypography.h6, color = CoolGrey)
                Divider(thickness = 1.dp, color = CoolGrey)
                Spacer(modifier = Modifier.height(30.dp))
            }

            uiStateSeeker?.education?.forEach {
                item {
                    EducationCard(education = it, cardModifier = Modifier.width(400.dp))
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))
                Divider(thickness = 3.dp, color = LackCoral)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Expérience", style = GWTypography.h6, color = CoolGrey)
                Divider(thickness = 1.dp, color = CoolGrey)
                Spacer(modifier = Modifier.height(30.dp))
            }


            uiStateSeeker?.experience?.forEach {
                item {
                    ExperienceCard(experience = it)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }


            item {
                Spacer(modifier = Modifier.height(30.dp))
                Divider(thickness = 3.dp, color = LackCoral)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Compétences", style = GWTypography.h6, color = CoolGrey)
                Divider(thickness = 1.dp, color = CoolGrey)
                Spacer(modifier = Modifier.height(30.dp))
            }



            uiStateSeeker?.preferencesDEmploi?.forEachIndexed {i, e ->
                item {
                    KeyValue(key = "Préférence #$i", value = e)
                    Spacer(modifier = Modifier.height(10.dp))
                }
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
fun SeekerVerifyInformationsStepPreview() {
    SeekerVerifyInformationsStep(false, null, null, context = LocalContext.current)

}
