package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PostAdd
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.repository.Ressources
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.components.PostCardComponent

@SuppressLint("MaterialDesignInsteadOrbitDesign", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PostsEntrepriseScreen(
    postsViewModel: PostsEntrepriseViewModel? = PostsEntrepriseViewModel(),
    onPostClick: (id: String) -> Unit,
    navToPostPage: () -> Unit,
    navToNewPostPage: () -> Unit,
) {
    val applicationUiState = postsViewModel?.postsUiState ?: PostsEntrepriseUiState()

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = Unit){
        postsViewModel?.loadPosts()
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                navigationIcon = {},
                actions = {
                    IconButton(onClick = {navToNewPostPage.invoke()}) {
                        Icon(
                            imageVector = Icons.Rounded.PostAdd,
                            contentDescription = "Add Post",
                        )
                    }
                },
                title = {
                    Text(text = "Posts", textAlign = TextAlign.Center)
                }
            )
        }
    ) {

        when(applicationUiState.postList){
            is Ressources.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }

            is Ressources.Success -> {
                LazyColumn(modifier = Modifier.padding(6.dp)){
                    items(applicationUiState.postList.data ?: emptyList()) {
                        post ->
                        PostCardComponent(post, onClick = { onPostClick.invoke(post.postId) })
                    }
                }
            }
            else -> {
                Text(
                    text = applicationUiState
                        .postList.throwable?.localizedMessage ?: "OOPS!\nUne Erreur s'est produite",
                    color = Color.Red
                )
            }

        }

    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun ApplicationsScreenPreview() {
    PostsEntrepriseScreen(
        null,{},{}, {}
    )
}

