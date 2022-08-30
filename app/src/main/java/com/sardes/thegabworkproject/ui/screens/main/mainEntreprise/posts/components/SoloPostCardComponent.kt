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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.standalonepost.PostUiState
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.standalonepost.StandalonePostViewModel
import com.sardes.thegabworkproject.ui.theme.NewBlue
import kiwi.orbit.compose.ui.controls.Card as OrbitCard
import kiwi.orbit.compose.ui.controls.Text as OrbitText

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun SoloPostCardComponent(
    standalonePostViewModel: StandalonePostViewModel?,
    postId: String,
    onClick: () -> Unit,
) {

    val postUiState = standalonePostViewModel?.postUiState ?: PostUiState()

    LaunchedEffect(key1 = Unit){
        standalonePostViewModel?.getPost(postId)
    }

    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(160.dp)
            .clickable { onClick.invoke() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(10.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = postUiState.postName,
                    style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
                    color = NewBlue,
                    modifier = Modifier.weight(1f),
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = postUiState.typeEmploi,
                    style = MaterialTheme.typography.body1,
                    color = NewBlue
                )

                Text(
                    text = postUiState.adresse,
                    style = MaterialTheme.typography.body2,
                    color = NewBlue
                )
            }

            Spacer(Modifier.width(10.dp))

            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = if (postUiState.actif) "Actif" else "Inactif",
                    color = if (postUiState.actif) Color(0xFF0F730C) else Color(0xFFDB1E1E),
                    modifier = if (postUiState.actif) {
                        Modifier
                            .background(Color(0xFFEDF9F0))
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                            .clip(RoundedCornerShape(20.dp))
                    } else {
                        Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color(0xFFFFEFEF))
                            .padding(5.dp)
                    },
                    style = MaterialTheme.typography.body1

                )

                Text(
                    text = "${postUiState.salaire} \n Fcfa/mois",
                    color = NewBlue,
                    style = MaterialTheme.typography.body1,
                )
            }
        }
    }
}

//@Preview
@Composable
fun TestCard() {
    OrbitCard {
        OrbitText(text = "Test")
    }
}

//@Preview
@Composable
fun PreviewSoloPostCard() {
    PostCardComponent(
        Post = CompteEntreprise.PostVacant(
            postName = "Developpeur Mobile",
            typeDEmploi = "Temps Plein",
            adresse = "23 Rue des Légendes",
            actif = true,
            salaire = "2000000"
        ),
        onCardClick = {}
    )
    
}