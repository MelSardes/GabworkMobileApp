package com.sardes.thegabworkproject.ui.screens.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.data.models.Education
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette
import kiwi.orbit.compose.ui.controls.Card
import kiwi.orbit.compose.ui.controls.Text


@Composable
fun EducationCard(
    cardModifier: Modifier = Modifier,
//    @DrawableRes logo: Int,
    education: Education,
) {

    Card(
        cardModifier.width(400.dp),
        backgroundColor = Color.White,
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            /*Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )*/

            Column() {
                Text(
                    education.etablissement,
                    style = GWTypography.h5,
                    color = GWpalette.MaximumRed,
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    education.ville,
                    style = GWTypography.body2,
                    textAlign = TextAlign.End
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = education.domaine,
                    style = GWTypography.body1.copy(fontWeight = FontWeight.SemiBold),
                    color = GWpalette.LackCoral,
                    textAlign = TextAlign.End
                )

/*
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.4f)
                ) {
                    Image(
                        painter = painterResource(
                            id =
                            when (education.etablissement) {
                                "INPTIC" -> R.drawable.logo_inptic
                                "USTM" -> R.drawable.logo_ustm
                                "UOB" -> R.drawable.logo_uob
                                else -> R.drawable.gabwork_logo
                            }

                        ),
                        contentDescription = R.string.school_logo.toString(),
                    )
                }
*/


                Text(
                    text = "${education.dateDebut} - ${education.dateFin}",
                    style = GWTypography.body2,
                    color = GWpalette.CoolGrey
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(text = education.diplome, style = GWTypography.h6, color = GWpalette.LackCoral)

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = education.description,
                    style = GWTypography.body1,
                    color = GWpalette.Gunmetal,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }
    }
}

    @Preview(name = "EducationCard", showBackground = false, showSystemUi = false)
    @Composable
    private fun PreviewEducationCard() {
        EducationCard(
            cardModifier = Modifier,
            education = Education(
                "INPTIC",
                "DUT Génnie Informatique",
                "Informatique",
                "wer tyuioa sdfg hjkl zxcvb nm qwert yui opasfghjk lz xcvbnm " +
                        "wer tyuioa sdfg hjkl zxcvb nm qwert yui opasfghjk lz xcvbnm " +
                        "qwert yui opa sdfg hjkl zxc vbnm",
                "Libreville",
                "2020",
                "2022"
            ),
        )
    }