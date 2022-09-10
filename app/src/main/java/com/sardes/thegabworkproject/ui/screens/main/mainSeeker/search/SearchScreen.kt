package com.sardes.thegabworkproject.ui.screens.main.mainSeeker.search

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kiwi.orbit.compose.ui.controls.Text

@Composable
fun SearchSeekerScreen(searchSeekerViewModel: SearchSeekerViewModel?) {
    Text(text = "SEARCH")
}






@Preview(name = "SavesScreen")
@Composable
private fun PreviewSavesScreen() {
    SearchSeekerScreen(null)
}