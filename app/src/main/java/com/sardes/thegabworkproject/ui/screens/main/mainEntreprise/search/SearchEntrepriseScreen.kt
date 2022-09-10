package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.search

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.data.provider.GabworkDataProvider
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.search.components.EntrepriseSearchBar
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.search.components.GabworkSearchGrid
import com.sardes.thegabworkproject.ui.theme.modifiers.horizontalGradientBackground

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun SearchEntrepriseScreen(
    searchViewModel:SearchEntrepriseViewModel = SearchEntrepriseViewModel()
) {
    val scrollState = rememberScrollState(0)
    val surfaceGradient = GabworkDataProvider.gabworkSurfaceGradient(isSystemInDarkTheme())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .horizontalGradientBackground(surfaceGradient)
    ) {
        Text(
            text = "Rechercher",
            style = typography.h2.copy(fontWeight = FontWeight.ExtraBold),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 80.dp, bottom = 40.dp)
                .fillMaxSize()
                .alpha(1f - scrollState.value / 200)
            // Just reducing the opacity by small fraction when scroll happens
        )
        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(180.dp))
            Column(modifier = Modifier.horizontalGradientBackground(surfaceGradient)) {
                EntrepriseSearchBar()
                GabworkSearchGrid()
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
    }

}

@Preview(name = "Phone", device = "spec:width=411dp,height=891dp", showSystemUi = true, showBackground = true)
@Preview(name = "TECNO POP3",device = "id:TECNO POP3", showSystemUi = true, showBackground = true)
@Preview(name = "PIXEL",device = "id:pixel", showBackground = true, showSystemUi = true)
@Composable
private fun PreviewSearchEntrepriseScreen() {
    SearchEntrepriseScreen()
}