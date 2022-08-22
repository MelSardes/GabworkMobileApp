package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.applications.post

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.models.CompteEntreprise
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.applications.components.CustomTopAppBar
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.applications.components.PostCardComponent
import kiwi.orbit.compose.ui.controls.Scaffold

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun PostDetails(
    postViewModel: PostViewModel?,
    postId: String,
    onNavToUpdate: () -> Unit,
    onNavToBack: () -> Unit
) {
    val postUiState = postViewModel?.postUiState ?: PostUiState()

    LaunchedEffect(key1 = Unit){
        postViewModel?.getPost(postId)
    }

    val scaffoldState = rememberScaffoldState()

    Scaffold (
        topBar = {CustomTopAppBar(title = stringResource(id = R.string.posts)) }
    ){
        Column(Modifier.padding(20.dp)) {
            PostCardComponent(Post = CompteEntreprise.PostVacant())

            Spacer(Modifier.height(20.dp))

            Text(text = stringResource(id = R.string.demandeurs))

            Spacer(Modifier.height(10.dp))

            LazyColumn{
                TODO("ADD SEEKER CARDS LIST")
            }
        }
    }

}

@Preview(name = "PostDetails")
@Composable
private fun PreviewPostDetails() {
    PostDetails(null, "", {}, {})
}