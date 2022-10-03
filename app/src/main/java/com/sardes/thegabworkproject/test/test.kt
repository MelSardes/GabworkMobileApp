package com.sardes.thegabworkproject.test

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.data.provider.GabworkDataProvider
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.search.components.EntrepriseSearchBar
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.search.components.GabworkSearchGrid
import com.sardes.thegabworkproject.ui.theme.modifiers.horizontalGradientBackground



@SuppressLint("MaterialDesignInsteadOrbitDesign", "UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun Test(
    onAreaClick: () -> Unit = {}
) {

    val scrollState = rememberScrollState(0)
    val surfaceGradient = GabworkDataProvider.gabworkSurfaceGradient(isSystemInDarkTheme())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .horizontalGradientBackground(surfaceGradient)
    ) {

        Image(
            painter = painterResource(id = R.drawable.search_rafiki_2),
            contentDescription = null,
            modifier = Modifier
//                .padding(top = 80.dp, bottom = 40.dp)
                .fillMaxWidth()
                .alpha(1f - scrollState.value / 200)
        )

        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(200.dp))
            Column(modifier = Modifier.horizontalGradientBackground(surfaceGradient)) {
                EntrepriseSearchBar()
                GabworkSearchGrid{
                    onAreaClick.invoke()
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
    }


}



