package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.ui.theme.GWpalette
import com.sardes.thegabworkproject.ui.theme.TailwindCSSColor.Pink900

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun PostCardComponent(
    post: CompteEntreprise.Post?,
    onCardClick: () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .height(160.dp)
            .clickable { onCardClick.invoke() },
        elevation = CardDefaults.cardElevation(10.dp),
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                Text(
                    text = post?.postName ?: "",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h5,
                    color = GWpalette.ImperialRed,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = if (post?.actif == true) "Actif" else "Inactif",
                    color = if (post?.actif == true) Color(0xFF0F730C) else Color(0xFFDB1E1E),
                    modifier = if (post?.actif == true) {
                        Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color(0xFFEDF9F0))
                            .padding(vertical = 5.dp, horizontal = 20.dp)
                    } else {
                        Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color(0xFFFFEFEF))
                            .padding(vertical = 5.dp, horizontal = 20.dp)
                    },
                    style = MaterialTheme.typography.body1
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                Text(
                    text = post?.domaine ?: "",
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    style = MaterialTheme.typography.body2,
                    color = White,
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .weight(1f)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Pink900)
                        .padding(5.dp)
                )

                Text(
                    text = post?.typeDEmploi ?: "",
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    style = MaterialTheme.typography.body2,
                    color = White,
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .weight(1f)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Pink900)
                        .padding(5.dp)
                )

                Text(
                    text = post?.experience ?: "",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body2,
                    color = White,
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .weight(1f)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Pink900)
                        .padding(5.dp)
                )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                Text(
                    text = (post?.salaire ?: "") + "F/mois",
                    color = GWpalette.LackCoral,
                    style = MaterialTheme.typography.body2,
                )

                Text(
                    text = post?.ville + "/" + post?.province,
                    style = MaterialTheme.typography.body2,
                    color = GWpalette.CoolGrey
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewPostCard() {
    PostCardComponent(
        post = CompteEntreprise.Post(
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