package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.repository.ressources.Ressources
import com.sardes.thegabworkproject.ui.composition.CircularButton
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.components.PostCardComponent
import com.sardes.thegabworkproject.ui.theme.AppBarCollapsedHeight
import com.sardes.thegabworkproject.ui.theme.AppBarExpendedHeight
import com.sardes.thegabworkproject.ui.theme.BlueFlag
import kotlin.math.max
import kotlin.math.min

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun HomeEntrepriseScreen(
    homeEntrepriseViewModel: HomeEntrepriseViewModel?,
    onPostClick: (id: String) -> Unit = {},
) {
//    val scaffoldState = rememberScaffoldState()

    val homeUiState = homeEntrepriseViewModel?.homeEntrepriseUiState ?: HomeEntrepriseUiState()
    val scrollState = rememberLazyListState()

    LaunchedEffect(key1 = Unit) {
        homeEntrepriseViewModel?.loadActivePosts()
    }

    Box(modifier = Modifier.background(Color.White)) {
        when (homeUiState.homePostList) {
            is Ressources.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }

            is Ressources.Success -> {
                LazyColumn(
                    contentPadding = PaddingValues(top = AppBarExpendedHeight),
                    state = scrollState
                ) {
                    items(homeUiState.homePostList.data ?: emptyList()) { post ->
                        PostCardComponent(post) { onPostClick(post.postId)}
                    }

/*
                item {
                    LazyRow(modifier = Modifier.padding(10.dp)) {
                        item {
                            SeekerCardComponent(
                                demandeur = CompteDemandeur(
                                    nom = "SARDES",
                                    prenom = "Mel",
                                    occupation = "Developpeur blockchain",
                                    urlPhotoProfil = R.drawable.black_businessman_in_blue_suit_waving_hello.toString()
                                )
                            )
                            Spacer(Modifier.width(10.dp))
                        }

                        item {
                            SeekerCardComponent(
                                demandeur = CompteDemandeur(
                                    nom = "MAKOSSO",
                                    prenom = "Loïck",
                                    occupation = "Developpeur android",
                                    urlPhotoProfil = R.drawable.business_3d.toString()
                                )
                            )
                        }
                    }
                }
*/
                }
            }

            else -> {
                Text(
                    text = homeUiState.homePostList.throwable?.localizedMessage
                        ?: "OOPS!\nUne erreur s'est produite",
                    color = Color.Red
                )
            }
        }

        ParallaxToolbar(scrollState)

    }
}


@SuppressLint("FrequentlyChangedStateReadInComposition", "MaterialDesignInsteadOrbitDesign")
@Composable
fun ParallaxToolbar(
    scrollState: LazyListState,
) {
    val imageHeight = AppBarExpendedHeight - AppBarCollapsedHeight

    val maxOffset =
        with(LocalDensity.current) { imageHeight.roundToPx() } - LocalWindowInsets.current.systemBars.layoutInsets.top

    val offset = min(scrollState.firstVisibleItemScrollOffset, maxOffset)

    val offsetProgress = max(0f, offset * 3f - 2f * maxOffset) / maxOffset

    TopAppBar(
        contentPadding = PaddingValues(),
        backgroundColor = Color.White,
        modifier = Modifier
            .height(AppBarExpendedHeight)
            .offset { IntOffset(x = 0, y = -offset) },
        elevation = if (offset == maxOffset) 4.dp else 0.dp
    ) {
        Column {
            Box(
                Modifier
                    .height(imageHeight)
                    .graphicsLayer {
                        alpha = 1f - offsetProgress
                    }
            ) {

                Image(
                    painter = painterResource(id = R.drawable.perroquet),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colorStops = arrayOf(
                                    Pair(0.5f, Color.Transparent),
                                    Pair(1f, Color.White)
                                )
                            )
                        )
                )

                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        "Péroquet gris du Gabon",
                        fontWeight = FontWeight.Medium,
                        color = BlueFlag,
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.LightGray)
                            .padding(vertical = 6.dp, horizontal = 16.dp)
                    )
                }
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .height(AppBarCollapsedHeight),
                verticalArrangement = Arrangement.Center
            ) {
                androidx.compose.material.Text(
                    "Bonjour",
                    style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
                    color = BlueFlag,
                    modifier = Modifier
                        .padding(horizontal = (16 + 28 * offsetProgress).dp)
                        .scale(1f - 0.25f * offsetProgress)
                )
            }
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(AppBarCollapsedHeight)
            .padding(horizontal = 16.dp)
    ) {
        CircularButton(R.drawable.ic_business_100)
        CircularButton(R.drawable.ic_search)
    }
}


@Preview
@Composable
fun HomePreview() {
    HomeEntrepriseScreen(null){}
}
