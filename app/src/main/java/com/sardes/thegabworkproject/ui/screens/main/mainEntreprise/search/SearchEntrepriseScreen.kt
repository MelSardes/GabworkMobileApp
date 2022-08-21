package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun SearchEntrepriseScreen(
    searchViewModel:SearchEntrepriseViewModel = SearchEntrepriseViewModel()
) {
    Box {
        Text(text = "SearchEntrepriseScreen")
    }
}

@Preview(name = "SearchEntrepriseScreen")
@Composable
private fun PreviewSearchEntrepriseScreen() {
    SearchEntrepriseScreen()
}