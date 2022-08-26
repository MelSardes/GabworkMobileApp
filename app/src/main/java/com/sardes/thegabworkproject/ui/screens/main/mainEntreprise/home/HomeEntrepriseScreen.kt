package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.data.models.CompteDemandeur
import com.sardes.thegabworkproject.repository.ressources.Ressources
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.components.PostCardComponent
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.components.SeekerCardComponent
import com.sardes.thegabworkproject.ui.theme.BlueFlag

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeEntrepriseScreen(
    homeEntrepriseViewModel: HomeEntrepriseViewModel? = HomeEntrepriseViewModel(),
    onPostClick: () -> Unit
) {
//    val scaffoldState = rememberScaffoldState()

    val homeUiState = homeEntrepriseViewModel?.homeEntrepriseUiState ?: HomeEntrepriseUiState()

    LaunchedEffect(key1 = Unit){
        homeEntrepriseViewModel?.loadActivePosts()
    }

    Scaffold(
//        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                backgroundColor = BlueFlag,
                modifier = Modifier.clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)),
                elevation = 16.dp,
                navigationIcon = {},
                actions = {
                    IconButton(onClick = {}) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("${homeUiState.entrepriseInformations?.urlLogoEntreprise}")
                                .crossfade(true)
                                .placeholder(R.drawable.ic_placeholder)
                                .build(),
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp)
                        )
                    }
                },
                title = {
                    Text(
                        text =
                        if(homeUiState.entrepriseInformations?.nomEntreprise == null) "Bonjour"
                        else "Bonjour ${homeUiState.entrepriseInformations.nomEntreprise}",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h5,
                        color = Color.White
                    )
                }
            )
        }
    ) {padding ->
        when(homeUiState.postList){
            is Ressources.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }

            is Ressources.Success -> {
                LazyColumn(modifier = Modifier.padding(padding)){
                    item {
                        Column {
                            Text(text = "Posts Actif", textAlign = TextAlign.Start)
                            Spacer(Modifier.width(10.dp))
                        }
                    }

                    items(homeUiState.postList.data ?: emptyList()) {
                                post ->
                            PostCardComponent(post, onCardClick = { onPostClick.invoke() })
                    }

                    item{
                        Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
                            Text(text = "Postulants recents", textAlign = TextAlign.Start)

                            Spacer(Modifier.width(10.dp))

                            Column(modifier = Modifier.padding(10.dp)){
                                SeekerCardComponent(
                                    demandeur = CompteDemandeur(
                                        nom = "SARDES",
                                        prenom = "Mel",
                                        occupation = "Developpeur blockchain",
                                        urlPhotoProfil = "${R.drawable.black_businessman_in_blue_suit_waving_hello}"
                                    )
                                )

                                Spacer(Modifier.width(10.dp))

                                SeekerCardComponent(
                                    demandeur = CompteDemandeur(
                                        nom = "MAKOSSO",
                                        prenom = "Loïck",
                                        occupation = "Developpeur android",
                                        urlPhotoProfil = "${R.drawable.business_3d}"
                                    )
                                )
                            }
                        }
                    }
                }
            }

            else -> {
                Text(
                    text = homeUiState.postList.throwable?.localizedMessage ?: "OOPS!\nUne erreur s'est produite",
                    color = Color.Red
                )
            }
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    HomeEntrepriseScreen(null) {}
}
