package com.sardes.thegabworkproject.ui.screens.main.mainSeeker.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kiwi.orbit.compose.ui.controls.Text

@Composable
fun HomeSeekerScreen(homeSeekerViewModel: HomeSeekerViewModel?) {
    Text(text = "HOME")
    
}




@Preview(name = "HomeScreen", showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    HomeSeekerScreen(null)
}