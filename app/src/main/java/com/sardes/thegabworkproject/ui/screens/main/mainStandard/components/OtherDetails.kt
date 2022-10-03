package com.sardes.thegabworkproject.ui.screens.main.mainStandard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.ui.composition.TextFieldAuthItem
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.home.HomeStandardUiState
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.home.HomeStandardViewModel

@Composable
fun OtherDetails(
    uiState: HomeStandardUiState?,
    viewModel: HomeStandardViewModel?
) {
    LazyColumn(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(30.dp)
    ){

//        # HQH
        item {
            TextFieldAuthItem(
                label = "Diplome le plus élevé *",
                info = "Diplôme le plus élevé obtenu lors de votre parcours scolaire",
                value = uiState?.HQH ?: "",
                valueChange = { viewModel?.onHQHChange(it) }
            )
            Spacer(modifier = Modifier.height(20.dp))
        }

//        # POSITION
        item {
            TextFieldAuthItem(
                label = "Métier *",
                info = "Le metier parfait pour vous",
                value = uiState?.preferredJob ?: "",
                valueChange = { viewModel?.onPreferredJobChange(it) }
            )
        }
    }
}

@Preview(name = "OtherDetails")
@Composable
private fun PreviewOtherDetails() {
    OtherDetails(null, null)
}