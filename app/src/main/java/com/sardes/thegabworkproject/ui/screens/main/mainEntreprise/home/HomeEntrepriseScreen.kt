package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sardes.thegabworkproject.R

@Composable
fun HomeEntrepriseScreen(
    homeEntrepriseViewModel: HomeEntrepriseViewModel? = HomeEntrepriseViewModel(),
) {


    val homeUiState = homeEntrepriseViewModel?.homeEntrepriseUiState ?: HomeEntrepriseUiState()

    LaunchedEffect(key1 = Unit){
        homeEntrepriseViewModel?.getEntrepriseInformations()
    }

    Column{
        TopBar(
            avatarUrl = homeUiState.entrepriseInformations?.urlLogoEntreprise!!,
            homeUiState.entrepriseInformations.nomEntreprise
        )
    }
}


@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun TopBar(avatarUrl: String, name: String?) {

    Surface{
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Bonjour $name")
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(avatarUrl)
                    .crossfade(true)
                    .placeholder(R.drawable.ic_placeholder)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
        }
    }
}


@Preview
@Composable
fun HomePreview() {
    HomeEntrepriseScreen(null)
}
