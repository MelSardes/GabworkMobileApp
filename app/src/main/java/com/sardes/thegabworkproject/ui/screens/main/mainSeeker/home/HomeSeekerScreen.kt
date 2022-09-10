package com.sardes.thegabworkproject.ui.screens.main.mainSeeker.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kiwi.orbit.compose.ui.controls.Scaffold
import kiwi.orbit.compose.ui.controls.TopAppBar

@Composable
fun HomeSeekerScreen(
    homeSeekerViewModel: HomeSeekerViewModel?,

) {

    Scaffold(

        topBar = { TopAppBar(title = {

        }) },



        content = {

        }
    )





}


@Preview(name = "HomeScreen", showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    HomeSeekerScreen(null)
}