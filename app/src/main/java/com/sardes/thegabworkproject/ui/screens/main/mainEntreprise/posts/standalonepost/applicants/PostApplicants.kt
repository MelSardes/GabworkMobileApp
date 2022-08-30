package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.standalonepost.applicants

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.data.models.CompteDemandeur
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.components.CustomTopAppBar
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.components.SeekerCardComponent
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.components.SoloPostCardComponent
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.standalonepost.StandalonePostViewModel
import kiwi.orbit.compose.ui.controls.Scaffold

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun PostApplicants(
    standalonePostViewModel: StandalonePostViewModel?,
    postId: String,
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold (
        topBar = {CustomTopAppBar(title = stringResource(id = R.string.posts)) }
    ){ padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {

            SoloPostCardComponent(
                postId = postId,
                standalonePostViewModel = standalonePostViewModel){}

            Spacer(Modifier.height(20.dp))

            Text(
                text = stringResource(id = R.string.demandeurs),
                modifier = Modifier.padding(10.dp),
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
                    )
                    )
                }
                item {
                    SeekerCardComponent(
                        demandeur = CompteDemandeur(
                            nom = "MAKOSSO",
                            prenom = "Loïck",
                            occupation = "Developpeur android",
                            urlPhotoProfil = "${R.drawable.business_3d}"
                    )
                    )
                }
            }
        }
    }

}

@Preview(name = "PostDetails")
@Composable
private fun PreviewPostDetails() {
    PostApplicants(null, "")
}
