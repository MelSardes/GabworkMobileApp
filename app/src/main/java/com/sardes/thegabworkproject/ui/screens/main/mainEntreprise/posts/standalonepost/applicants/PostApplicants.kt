package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.standalonepost.applicants

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.models.CompteDemandeur
import com.sardes.thegabworkproject.models.CompteEntreprise
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.components.CustomTopAppBar
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.components.PostCardComponent
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.components.SeekerCardComponent
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.standalonepost.PostUiState
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.standalonepost.StandalonePostViewModel
import kiwi.orbit.compose.ui.controls.Scaffold

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun PostApplicants(
    standalonePostViewModel: StandalonePostViewModel? = StandalonePostViewModel(),
    post: CompteEntreprise.PostVacant,
) {
    val postUiState = standalonePostViewModel?.postUiState ?: PostUiState()

    LaunchedEffect(key1 = Unit){
        standalonePostViewModel?.getPost(post.postId)
    }

    val scaffoldState = rememberScaffoldState()

    Scaffold (
        topBar = {CustomTopAppBar(title = stringResource(id = R.string.posts)) }
    ){ padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {

            PostCardComponent(Post = CompteEntreprise.PostVacant()) {}

            Spacer(Modifier.height(20.dp))

            Text(
                text = stringResource(id = R.string.demandeurs),
                style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(Modifier.height(10.dp))

            LazyColumn{
                item {
                    SeekerCardComponent(
                        demandeur = CompteDemandeur(
                            nom = "SARDES",
                            prenom = "Mel",
                            occupation = "Developpeur blockchain",
                            urlPhotoProfil = "${R.drawable.black_businessman_in_blue_suit_waving_hello}"
                    ))
                }
            }
        }
    }

}
/*

@Preview(name = "PostDetails")
@Composable
private fun PreviewPostDetails() {
    PostApplicants(null, null)
}*/
