package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.applicants

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.repository.ressources.Ressources
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.components.PostCardComponent
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.components.SeekerCardComponent

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun ApplicantsScreen(
    applicantsViewModel: ApplicantsViewModel?,
    postId: String,
    onNavigate: () -> Unit,
) {


    val applicantsUiState = applicantsViewModel?.applicantsUiState ?: ApplicantsUiState()

    LaunchedEffect(applicantsViewModel?.getPost(postId)) {
        applicantsViewModel?.getPost(postId)
        applicantsViewModel?.getPostApplicants(postId)
    }


    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            PostCardComponent(post = applicantsUiState.selectedPost)

            Text(text = "Tous les candidats à ce post")

            when (applicantsUiState.applicantList) {
                is Ressources.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )
                }

                is Ressources.Success -> {
                    LazyColumn(contentPadding = PaddingValues(20.dp)) {
                        if (applicantsUiState.applicantList.data?.isEmpty() == false) {
                            items(applicantsUiState.applicantList.data) { candidat ->
                                SeekerCardComponent(candidat)
                            }
                        }else{
                            item {
                                Text(text = "Aucun candidat pour l'instant", textAlign = TextAlign.Center)
                            }
                        }
                    }
                }
                else -> {
                    Text(
                        text = applicantsUiState.applicantList.throwable?.localizedMessage
                            ?: "OOPS!\nUne erreur s'est produite",
                        color = Color.Red
                    )

                }
            }
        }
    }

}

@Preview(name = "Applicants")
@Composable
private fun PreviewApplicants() {
    ApplicantsScreen(ApplicantsViewModel(), "") {}
}