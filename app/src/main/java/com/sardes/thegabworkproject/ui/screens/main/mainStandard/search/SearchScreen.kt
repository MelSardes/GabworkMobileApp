package com.sardes.thegabworkproject.ui.screens.main.mainStandard.search

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kiwi.orbit.compose.ui.controls.Scaffold
import kiwi.orbit.compose.ui.controls.Text

@Composable
fun SearchStandardScreen(searchStandardViewModel: SearchStandardViewModel? = null) {
    Scaffold(

        topBar = {  },

        content = {
            Text("SEARCH")
        }
    )
}






@Preview(name = "SavesScreen")
@Composable
private fun PreviewSavesScreen() {
    SearchStandardScreen(null)
}