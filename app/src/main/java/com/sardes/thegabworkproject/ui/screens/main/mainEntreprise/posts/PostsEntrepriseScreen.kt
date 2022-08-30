package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.repository.ressources.Ressources
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.components.PostCardComponent
import kiwi.orbit.compose.ui.controls.Scaffold
import kiwi.orbit.compose.ui.controls.TopAppBar

@Composable
fun PostsEntrepriseScreen(
    postsEntrepriseViewModel: PostsEntrepriseViewModel? = PostsEntrepriseViewModel()
) {

    val postsUiState = postsEntrepriseViewModel?.postsEntrepriseUiState ?: PostsEntrepriseUiState()

    LaunchedEffect(key1 = Unit){
        postsEntrepriseViewModel?.loadActivePosts()
    }


    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)),
                elevation = 16.dp,
                navigationIcon = {},
                title = {
                    Text(
                        text = "Posts",
                        textAlign = TextAlign.Center,
                    )
                }
            )
        }
    ) { padding ->

        when (postsUiState.postList){
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
                            Text(text = "Posts Actifs", textAlign = TextAlign.Start)
                            Spacer(Modifier.width(10.dp))
                        }

                        LazyRow {
                            items(postsUiState.postList.data ?: emptyList()) {
                                    post ->
                                PostCardComponent(post, onCardClick = { })
                            }
                        }
                    }
                }
            }

            else -> {
                Text(
                    text = postsUiState.postList.throwable?.localizedMessage ?: "OOPS!\nUne erreur s'est produite",
                    color = Color.Red
                )
            }



        }


    }

}

@Preview(name = "PostsEntrepriseScreen")
@Composable
private fun PreviewPostsEntrepriseScreen() {
    PostsEntrepriseScreen()
}