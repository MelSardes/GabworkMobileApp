package com.sardes.thegabworkproject.ui.screens.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.data.models.CompteStandard
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette
import kiwi.orbit.compose.ui.controls.Card
import kiwi.orbit.compose.ui.controls.Text as OrbitText

@Composable
fun ExperienceCard(
    cardModifier: Modifier = Modifier,
    experience: CompteStandard.Experience
) {

    Card(
        cardModifier,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            OrbitText(
                experience.position,
                style = GWTypography.h5,
                color = GWpalette.MaximumRed,
            )

            Spacer(modifier = Modifier.height(2.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {

                OrbitText(
                    experience.entreprise,
                    style = GWTypography.body1.copy(fontWeight = FontWeight.SemiBold),
                    color = GWpalette.LackCoral
                )

                OrbitText(text = "  -  ", textAlign = TextAlign.Center)

                OrbitText(
                    text = experience.typeDEmploi,
                    style = GWTypography.body1,
                    color = GWpalette.CoolGrey,
                )
            }

            OrbitText(
                text = "${experience.dateDebut} - ${experience.dateFin}",
                style = GWTypography.body2,
                color = GWpalette.CoolGrey
            )

            Spacer(modifier = Modifier.height(2.dp))

            OrbitText(
                text = "${experience.ville}",
                style = GWTypography.body2,
                color = GWpalette.CoolGrey
            )

            Spacer(modifier = Modifier.height(10.dp))

            OrbitText(
                text = experience.description,
                style = GWTypography.body1,
                color = GWpalette.Gunmetal,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Preview(name = "ExperienceCard", showSystemUi = false)
@Composable
private fun PreviewExperienceCard() {
    ExperienceCard(
        cardModifier = Modifier
            .width(400.dp),
        experience = CompteStandard.Experience(
            "SARDES CORP.",
            "Développeur Kotlin",
            "Temps plein",
            "wer tyuioa sdfg hjkl zxcvb nm qwert yui opasfghjk lz xcvbnm " +
                    "wer tyuioa sdfg hjkl zxcvb nm qwert yui opasfghjk lz xcvbnm " +
                    "qwert yui opa sdfg hjkl zxc vbnm",
            "Sardesville",
            "2040",
            "2042"
        )
    )
}