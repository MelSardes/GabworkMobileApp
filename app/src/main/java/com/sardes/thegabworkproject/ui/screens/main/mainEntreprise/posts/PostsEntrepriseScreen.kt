package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PostAdd
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sardes.thegabworkproject.navigation.NavigationItem
import com.sardes.thegabworkproject.repository.ressources.PostsRessources
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.components.PostCardComponent
import com.sardes.thegabworkproject.ui.theme.BlueFlag
import com.sardes.thegabworkproject.ui.theme.YellowFlag

@SuppressLint("MaterialDesignInsteadOrbitDesign", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PostsEntrepriseScreen(
    navController: NavController,
    postsViewModel: PostsEntrepriseViewModel? = PostsEntrepriseViewModel(),
    onPostClick: (id: String) -> Unit,
    navToNewPost: () -> Unit,
    newNav: () -> Unit
) {

    val postsUiState = postsViewModel?.postsUiState ?: PostsEntrepriseUiState()

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = Unit){
        postsViewModel?.loadPosts()
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                backgroundColor = BlueFlag,
                modifier = Modifier.clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)),
                navigationIcon = {},
                actions = {
                    IconButton(onClick = {navController.navigate(NavigationItem.NewPost.route)}) {
                        Icon(
                            imageVector = Icons.Rounded.PostAdd,
                            contentDescription = "Add Post",
                            tint = YellowFlag
                        )
                    }
                },
                title = {
                    Text(text = "Posts", textAlign = TextAlign.Center, color = Color.White)
                }
            )
        }
    ) {padding ->

        when(postsUiState.postList){
            is PostsRessources.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }

            is PostsRessources.Success -> {
                LazyColumn(modifier = Modifier.padding(6.dp)){
                    items(postsUiState.postList.data ?: emptyList()) {
                        post ->
                        PostCardComponent(post, onCardClick = { onPostClick.invoke(post.postId) })
                    }
                }
            }
            else -> {
                Text(
                    text = postsUiState
                        .postList.throwable?.localizedMessage ?: "OOPS!\nUne Erreur s'est produite",
                    color = Color.Red
                )
            }

        }

    }
}
/*

@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun ApplicationsScreenPreview() {
    PostsEntrepriseScreen(
    )
}
*/

