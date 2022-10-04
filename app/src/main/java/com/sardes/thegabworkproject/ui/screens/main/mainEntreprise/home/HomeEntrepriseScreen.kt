package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.insets.LocalWindowInsets
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.repository.ressources.Ressources
import com.sardes.thegabworkproject.ui.composition.CircularButton
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.components.PostCardComponent
import com.sardes.thegabworkproject.ui.theme.AppBarCollapsedHeight
import com.sardes.thegabworkproject.ui.theme.AppBarExpendedHeight
import com.sardes.thegabworkproject.ui.theme.BlueFlag
import com.sardes.thegabworkproject.ui.theme.GWpalette.Gunmetal
import kiwi.orbit.compose.ui.controls.Scaffold
import kotlin.math.max
import kotlin.math.min

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun HomeEntrepriseScreen(
    homeEntrepriseViewModel: HomeEntrepriseViewModel?,
    onPostClick: (id: String) -> Unit,
) {

    val homeUiState = homeEntrepriseViewModel?.homeEntrepriseUiState ?: HomeEntrepriseUiState()
//    val scrollState = rememberLazyListState()

    LaunchedEffect(Unit) {
        homeEntrepriseViewModel?.loadActivePosts()
    }

/*
    LaunchedEffect(Unit){
        homeEntrepriseViewModel?.loadApplications()
    }
*/

    Scaffold(
        backgroundColor = Gunmetal,
        topBar = { },
    ) { padding ->
        Box(
            modifier = Modifier
                .background(Gunmetal)
                .clip(RoundedCornerShape(24.dp))
                .fillMaxSize()
                .padding(padding)
                .background(Color.White)
        ) {
            LazyColumn(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            Text(text = "HOME")
                            Button(onClick = { /*TODO*/ }) {
                                Text(text = "Rechercher ...")
                            }
                        }
                    }
                }

                item {
                    Text(text = "Posts vacants")
                }

                homeUiState.homePostList.data?.forEach {
                    item {
                        PostCardComponent(it) { onPostClick(it.postId) }
                    }
                }
/*

                item {
                    Text(
                        text = "Ils ont recement postulé",
                        style = GWTypography.h6,
                        color = Gunmetal,
                        modifier = Modifier.padding(start = 30.dp)
                    )
                }

                homeUiState.applicants.data?.forEach {
                    item {
                        SeekerCardComponent(applicant = it)
                    }
                }
*/


            }
        }
    }
}

@Composable
private fun Content(
    homeUiState: HomeEntrepriseUiState,
    scrollState: LazyListState,
    onPostClick: (id: String) -> Unit,
) {
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
//                items(homeUiState.homePostList.data ?: emptyList()) { post ->
//                    PostCardComponent(post) { onPostClick(post.postId) }
//                }

                homeUiState.homePostList.data?.forEach {
                    item {
                        PostCardComponent(it) { onPostClick(it.postId) }
                    }
                }
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
}


@SuppressLint("FrequentlyChangedStateReadInComposition", "MaterialDesignInsteadOrbitDesign")
@Composable
fun ParallaxToolbar(
    scrollState: LazyListState,
    dataImage: String,
    dataName: String
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
                                    Pair(0.25f, Color.Transparent),
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
                        "Bonjour",
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
                    .height(AppBarCollapsedHeight)
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    if (dataName == null) dataName
                    else "",
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
        CircularButton(kiwi.orbit.compose.icons.R.drawable.ic_orbit_menu_hamburger)
        AsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(dataImage)
                .crossfade(true)
                .crossfade(1000)
                .placeholder(R.drawable.ic_person)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(38.dp)
                .clip(shape = CircleShape),
            contentScale = ContentScale.Crop,
        )

    }
}


@Preview
@Composable
fun HomePreview() {
    HomeEntrepriseScreen(null) {}
}
