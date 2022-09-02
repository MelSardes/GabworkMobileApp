package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.applicants

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sardes.thegabworkproject.data.models.CompteDemandeur
import com.sardes.thegabworkproject.repository.main.entreprise.MainEntrepriseRepository
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.components.PostCardComponent
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.components.SeekerCardComponent

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun ApplicantsScreen(
    applicantsViewModel:ApplicantsViewModel = ApplicantsViewModel(repository = MainEntrepriseRepository()),
    postId: String,
    onNavigate: () -> Unit,
) {


    val applicantsUiState = applicantsViewModel?.applicantsUiState ?: ApplicantsUiState()

    LaunchedEffect(key1 = Unit) {
        applicantsViewModel?.getPost(postId)
    }


    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            PostCardComponent(post = applicantsUiState.selectedPost) {
            }

            Text(text = "Tous les candidats à ce post")

            SeekerCardComponent(
                demandeur = CompteDemandeur(
                    nom = "Sardes",
                    prenom = "Mel",
                    sexe = "homme",
                    telephone = "066157770",
                    email = "melsardes2042@hotmail.com",
                    ville = "Sardesville",
                    nationalite = "Sardesienne",
                    adresse = "42 Avenue des légendes, Sardesville, Etat de Sardes",
                    urlPhotoProfil = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/df/We_Can_Do_It%21_NARA_535413_-_Restoration_2.jpg/220px-We_Can_Do_It%21_NARA_535413_-_Restoration_2.jpg",
                    occupation = "Développeur Kotlin",
                )
            )

        }
    }

}

@Preview(name = "Applicants")
@Composable
private fun PreviewApplicants() {
    ApplicantsScreen(ApplicantsViewModel(), "") {}
}