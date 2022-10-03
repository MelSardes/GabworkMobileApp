package com.sardes.thegabworkproject.ui.screens.main.mainStandard.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
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
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.home.HomeStandardUiState
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.home.HomeStandardViewModel
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette
import com.sardes.thegabworkproject.ui.theme.TailwindCSSColor
import kiwi.orbit.compose.ui.controls.Card
import kiwi.orbit.compose.ui.controls.KeyValue
import kiwi.orbit.compose.ui.controls.KeyValueLarge

@Composable
fun InformationsVerification(
//    isError: Boolean,
    uiState: HomeStandardUiState?,
    viewModel: HomeStandardViewModel?,
    context: Context,
    onFinish: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TailwindCSSColor.Green500)
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                .background(Color.White),
            contentPadding = PaddingValues(30.dp)
        ) {
            item {
                Text(
                    text = "Vérifiez que ces informations sont correctes\n " +
                            "avant de soumettre votre inscription",
                    style = GWTypography.subtitle1.copy(fontWeight = FontWeight.Medium),
                    color = GWpalette.MaximumRed
                )
                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))
                KeyValueLarge(
                    key = "Diplôme de plus haut degré",
                    value = uiState?.HQH ?: "Encore vide"
                )
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))
                KeyValueLarge(key = "Métier", value = uiState?.preferredJob ?: "Encore vide")
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))
                Divider(thickness = 3.dp, color = GWpalette.LackCoral)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Langues", style = GWTypography.h6, color = GWpalette.CoolGrey)
                Divider(thickness = 1.dp, color = GWpalette.CoolGrey)
                Spacer(modifier = Modifier.height(30.dp))
            }

            uiState?.languages?.forEachIndexed { i, e ->
                item {
                    KeyValue(key = "Langue ${i + 1}", value = e)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))
                Divider(thickness = 3.dp, color = GWpalette.LackCoral)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Compétences", style = GWTypography.h6, color = GWpalette.CoolGrey)
                Divider(thickness = 1.dp, color = GWpalette.CoolGrey)
                Spacer(modifier = Modifier.height(30.dp))
            }



            uiState?.skills?.forEach {
                item {
                    KeyValueLarge(key = it.level, value = it.title)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }


            item {
                Spacer(modifier = Modifier.height(30.dp))
                Divider(thickness = 3.dp, color = GWpalette.LackCoral)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Éduacation", style = GWTypography.h6, color = GWpalette.CoolGrey)
                Divider(thickness = 1.dp, color = GWpalette.CoolGrey)
                Spacer(modifier = Modifier.height(30.dp))
            }

            uiState?.education?.forEach {
                item {
                    EducationCard(education = it, modifier = Modifier.width(400.dp))
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))
                Divider(thickness = 3.dp, color = GWpalette.LackCoral)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Expérience", style = GWTypography.h6, color = GWpalette.CoolGrey)
                Divider(thickness = 1.dp, color = GWpalette.CoolGrey)
                Spacer(modifier = Modifier.height(30.dp))
            }


            uiState?.experience?.forEach {
                item {
                    ExperienceCard(experience = it)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }


            item {
                Spacer(modifier = Modifier.height(30.dp))
                Divider(thickness = 3.dp, color = GWpalette.LackCoral)
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Liste de souhaits",
                    style = GWTypography.h6,
                    color = GWpalette.CoolGrey
                )
                Divider(thickness = 1.dp, color = GWpalette.CoolGrey)
                Spacer(modifier = Modifier.height(30.dp))
            }



            uiState?.wishJobs?.forEachIndexed { i, e ->
                item {
                    KeyValueLarge(key = "Préférence #${i + 1}", value = e)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

        }

        Card(
            onClick = {
                viewModel?.addAdditionalInformations(context = context)
                if(uiState?.additionalInformationsAddedStatus == true){
                    onFinish.invoke()
                }
                      },
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            backgroundColor = TailwindCSSColor.Green500,
        ) {
            when (uiState?.isLoading) {
                true -> CircularProgressIndicator(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                )
                else ->
                    Text(
                        text = "Soumettre les informations",
                        color = Color.White,
                        style = GWTypography.h6,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 7.dp)
                    )
            }

        }

    }
}

@Preview
@Composable
fun StandardVerifyInformationsStepPreview() {
    InformationsVerification(null, null, context = LocalContext.current)
}
