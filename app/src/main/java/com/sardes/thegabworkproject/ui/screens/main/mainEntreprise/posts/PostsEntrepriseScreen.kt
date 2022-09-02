package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
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
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.repository.ressources.Ressources
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.components.PostCardComponent
import com.sardes.thegabworkproject.ui.theme.AppBarCollapsedHeight
import com.sardes.thegabworkproject.ui.theme.AppBarExpendedHeight
import com.sardes.thegabworkproject.ui.theme.TailwindCSSColor.Pink900
import kotlin.math.max
import kotlin.math.min

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun PostsEntrepriseScreen(
//    navController: NavController,
    postsEntrepriseViewModel: PostsEntrepriseViewModel?,
    CreatePost: () -> Unit,
    onPostClick: (id: String) -> Unit,
) {


    val postsUiState = postsEntrepriseViewModel?.postsEntrepriseUiState ?: PostsEntrepriseUiState()
    val scrollState = rememberLazyListState()

    LaunchedEffect(key1 = Unit) {
        postsEntrepriseViewModel?.loadAllPosts()
    }


    Box(modifier = Modifier.background(Color.White)) {
        when (postsUiState.postList) {
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
                    items(postsUiState.postList.data ?: emptyList()) { post ->
                        PostCardComponent(post) { onPostClick(post.postId) }
                    }
                }
            }

            else -> {
                Text(
                    text = postsUiState.postList.throwable?.localizedMessage
                        ?: "OOPS!\nUne erreur s'est produite",
                    color = Color.Red
                )
            }
        }

        ParallaxToolbar(scrollState){CreatePost()}
    }
}


@SuppressLint("FrequentlyChangedStateReadInComposition", "MaterialDesignInsteadOrbitDesign")
@Composable
fun ParallaxToolbar(
    scrollState: LazyListState,
    CreatePost: () -> Unit
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
                        "Créer un post",
                        fontWeight = FontWeight.Medium,
                        color = Color.LightGray,
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(Pink900)
                            .padding(vertical = 6.dp, horizontal = 16.dp)
                            .clickable { CreatePost.invoke() }
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
                    "POSTS",
                    style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
                    color = Pink900,
                    modifier = Modifier
                        .padding(horizontal = (16 + 28 * offsetProgress).dp)
                        .scale(1f - 0.25f * offsetProgress)
                )
            }
        }
    }

/*
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
        CircularButton(R.drawable.ic_plus)
    }
*/
}
/*


@Preview(name = "PostsEntrepriseScreen")
@Composable
private fun PreviewPostsEntrepriseScreen() {
    PostsEntrepriseScreen()
}
*/
