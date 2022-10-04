package com.sardes.thegabworkproject.ui.screens.main.mainStandard.saves

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.repository.ressources.Ressources
import com.sardes.thegabworkproject.ui.composition.AnimatedShimmer
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.components.cards.ApplicationCard
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.home.HomeStandardUiState
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.home.HomeStandardViewModel
import com.sardes.thegabworkproject.ui.theme.GWTypography

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun ApplicationsSection(
    viewModel: HomeStandardViewModel?,
    uiState: HomeStandardUiState?
) {
    LaunchedEffect(Unit) {
        viewModel?.loadAllApplications()
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        when (uiState?.applications) {
            is Ressources.Loading ->
                LazyColumn(modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 10.dp)
                ) {
                    items(count = 4) {
                        Spacer(modifier = Modifier.height(20.dp))
                        AnimatedShimmer()
                    }
                }
            is Ressources.Success -> LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                item {
                    Text(
                        text =
                        if(uiState.applications.data.isNullOrEmpty())
                            "Vous avez déjà postulé à "+uiState.applications.data?.size +" offres d'emploi"
                        else "C'est encore vide içi",
                        style = GWTypography.h4
                    )
                }

                uiState.applications.data?.forEach {
                    item {
                        Spacer(modifier = Modifier.height(20.dp))
                        ApplicationCard(application = it) {
                        }
                    }
                }
            }
            else -> {
                Text(text = "Erreur")
            }
        }
    }


}

@Preview(name = "ApplicationsSection")
@Composable
private fun PreviewApplicationsSection() {
    ApplicationsSection(null, null)
}