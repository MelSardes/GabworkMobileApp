package com.sardes.thegabworkproject.ui.screens.main.mainStandard.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette
import com.sardes.thegabworkproject.ui.theme.GWpalette.CoolGrey
import com.sardes.thegabworkproject.ui.theme.GWpalette.Gunmetal
import com.sardes.thegabworkproject.ui.theme.TailwindCSSColor
import kiwi.orbit.compose.icons.Icons
import kiwi.orbit.compose.ui.controls.IconButton
import kiwi.orbit.compose.ui.controls.Text as OrbitText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicablePostCard(
    post: CompteEntreprise.Post?,
    onCardClick: () -> Unit = {},
    onLogoClick: () -> Unit = {},
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .padding(20.dp)
//            .fillMaxWidth()
            .height(190.dp)
            .clickable { onCardClick.invoke() },
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                OrbitText(
                    text = post?.postName ?: "",
                    textAlign = TextAlign.Start,
                    style = GWTypography.h5,
                    color = GWpalette.ImperialRed,
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = { /*TODO*/ },
                    rippleRadius = 16.dp,
                ) {
                    Icon(painter = Icons.Bookmark, contentDescription = null, tint = CoolGrey)
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                OrbitText(
                    text = post?.domaine ?: "",
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    style = GWTypography.body2,
                    color = Color.White,
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .weight(1f)
                        .clip(RoundedCornerShape(20.dp))
                        .background(TailwindCSSColor.Pink900)
                        .padding(5.dp)
                )

                OrbitText(
                    text = post?.typeDEmploi ?: "",
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    style = GWTypography.body2,
                    color = Color.White,
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .weight(1f)
                        .clip(RoundedCornerShape(20.dp))
                        .background(TailwindCSSColor.Pink900)
                        .padding(5.dp)
                )

                OrbitText(
                    text = post?.experience ?: "",
                    textAlign = TextAlign.Center,
                    style = GWTypography.body2,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .weight(1f)
                        .clip(RoundedCornerShape(20.dp))
                        .background(TailwindCSSColor.Pink900)
                        .padding(5.dp)
                )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                OrbitText(
                    text = (post?.salaire ?: "") + "F/mois",
                    color = GWpalette.LackCoral,
                    style = GWTypography.body2,
                )

                OrbitText(
                    text = post?.ville + "/" + post?.province,
                    style = GWTypography.body2,
                    color = CoolGrey
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.weight(1f)
                ) {
                    Card(
                        modifier = Modifier.size(60.dp),
                        colors = CardDefaults.cardColors(Color.White),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 4.dp,
                            pressedElevation = 8.dp
                        ),
                        onClick = { onLogoClick.invoke() }
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(post?.urlLogo)
                                .crossfade(true)
                                .placeholder(R.drawable.ic_business_100)
                                .build(),
                            contentDescription = null,
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.width(20.dp))

                    OrbitText(
                        text = "Sardes Corp.",
                        style = GWTypography.subtitle1,
                        color = Gunmetal
                    )
                }

                OrbitText(text = "Il y a 3 jours", style = GWTypography.body2, color = CoolGrey)
            }
        }
    }
}


@Preview(name = "PostCardForSeeker")
@Composable
private fun PreviewApplicablePostCard() {
    ApplicablePostCard(
        CompteEntreprise.Post(
            postName = "Developpeur Mobile",
            typeDEmploi = "Temps Plein",
            adresse = "23 Rue des Légendes",
            ville = "Sardesville",
            province = "Haut-Ogooué",
            experience = "Junior",
            domaine = "IT",
            actif = true,
            salaire = "2000000"
        )
    ) {}
}