package com.sardes.thegabworkproject.ui.screens.main.mainStandard.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.repository.ressources.Ressources
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.components.cards.ApplicablePostCard
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.home.components.StandardTopBar
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette.Gunmetal
import kiwi.orbit.compose.ui.controls.Scaffold

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun HomeStandardScreen(
    homeStandardViewModel: HomeStandardViewModel?,
    onPostClick: (id: String) -> Unit,
    onLogoClick: (entrepriseId: String) -> Unit,
) {

    val homeUiState = homeStandardViewModel?.homeStandardUiState ?: HomeStandardUiState()

    LaunchedEffect(Unit) {
        homeStandardViewModel?.getUserInformations()
    }
    LaunchedEffect(Unit) {
        homeStandardViewModel?.loadFiveLatestPosts()
    }

    when (homeUiState.fiveLatestPostsList
    ) {
        is Ressources.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
            )
        }

        is Ressources.Success -> {

            Scaffold(
                backgroundColor = Gunmetal,
                topBar = { StandardTopBar(homeUiState) },
                content = { padding ->
                    Box(
                        modifier = Modifier
                            .background(Gunmetal)
                            .clip(RoundedCornerShape(24.dp))
                            .fillMaxSize()
                            .padding(padding)
                            .background(Color.White)
                    ) {

                        LazyColumn(modifier = Modifier) {
                            item {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp)
                                ) {
                                    Column(modifier = Modifier.fillMaxSize()) {
                                        Text(text = "HOME")
                                        Text(text = homeUiState.userInformations?.isComplete.toString())
                                        Button(onClick = { /*TODO*/ }) {
                                            Text(text = "Rechercher ...")
                                        }
                                    }
                                }
                            }
                            item {
                                Text(
                                    text = "Denières offres d'emploi ${homeUiState.userInformations?.isComplete}",
                                    style = GWTypography.h6,
                                    color = Gunmetal,
                                    modifier = Modifier.padding(start = 30.dp)
                                )
                            }

                            homeUiState.fiveLatestPostsList.data?.forEach {
                                item {
                                    ApplicablePostCard(
                                        post = it,
                                        viewModel = homeStandardViewModel,
                                        onCardClick = { onPostClick(it.postId) },
                                        onLogoClick = { onLogoClick(it.entrepriseId) }
                                    )
                                }
                            }

                            item {
                                Text(
                                    text = "Les entreprises qui recrutent",
                                    style = GWTypography.h6,
                                    color = Gunmetal,
                                    modifier = Modifier.padding(start = 30.dp)
                                )
                            }
                        }
                    }
                }
            )
        }
        else -> {
            androidx.compose.material3.Text(
                text = "OOPS!\nUne erreur s'est produite",
                color = Color.Red
            )
        }

    }
}


@Preview(name = "HomeScreen", showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    HomeStandardScreen(null, {}) {}
}