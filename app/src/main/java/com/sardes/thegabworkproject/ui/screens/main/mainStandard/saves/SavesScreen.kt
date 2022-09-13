package com.sardes.thegabworkproject.ui.screens.main.mainStandard.saves

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kiwi.orbit.compose.ui.controls.Scaffold

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun SavesStandardScreen(savesStandardViewModel: SavesStandardViewModel? = null) {
    Scaffold(

        topBar = {  },

        content = {
            kiwi.orbit.compose.ui.controls.Text("SAVES")
        }
    )

}

@Preview(name = "SavesScreen")
@Composable
private fun PreviewSavesScreen() {
    SavesStandardScreen(null)
}