package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Message
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.data.models.CompteDemandeur

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun SeekerCardComponent(
    modifier: Modifier = Modifier,
    demandeur: CompteDemandeur
) {
    Box(modifier.fillMaxWidth()) {
        Card(
            elevation = 4.dp,
            backgroundColor = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .padding(8.dp)
                .clickable(onClick = {})
        ) {
            Column (verticalArrangement = Arrangement.SpaceBetween){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(demandeur.urlPhotoProfil)
                            .crossfade(true)
                            .placeholder(R.drawable.account_box_80)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Column {
                        Text(
                            text = demandeur.nom + " " + demandeur.prenom,
                            style = typography.h6,
                            modifier = Modifier.padding(horizontal = 8.dp),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                        Text(
                            text = demandeur.occupation,
                            color = Color.Black.copy(alpha = 0.5f),
                            style = typography.body2,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }

                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Filled.Message,
                            "Message Icon",
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color(0xFFEAF1FF))
                                .padding(10.dp),
                            tint = Color(0xFF6B9EFF)
                        )
                    }
                }

                Divider(
                    modifier
                        .padding(bottom = 5.dp)
                        .fillMaxWidth(0.6f)
                        .background(Color.Black.copy(alpha = 0.1f))
                        .align(CenterHorizontally)
                )

                Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround){
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(1f),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFF2972FE))
                    ) {
                        Text(
                            text = stringResource(id = R.string.seeCv),
                            color = Color.White,
                            style = typography.body2
                        )
                    }
                    OutlinedButton(
                        onClick = {},
                        modifier = Modifier.padding(8.dp).weight(1f),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF2972FE))
                    ) {
                        Text(
                            text = stringResource(id = R.string.seeProfile),
                            color = Color(0xFF2972FE),
                            style = typography.body2
                        )
                    }
                }
            }
        }
    }
}

@Preview(name = "SeekerCardComponent")
@Composable
private fun PreviewSeekerCardComponent() {
    SeekerCardComponent(demandeur = CompteDemandeur(
        nom = "SARDES",
        prenom = "Mel",
        occupation = "Developpeur Android"
    )
    )
}