package com.sardes.thegabworkproject.ui.screens.main.mainSeeker.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeekerMainPage() {
    val navController = rememberNavController()
//    val homeSeekerViewModel = viewModel(modelClass = HomeSeekerViewModel::class.java)
//    val savesSeekerViewModel = viewModel(modelClass = SavesSeekerViewModel::class.java)
//    val searchSeekerViewModel = viewModel(modelClass = SearchSeekerViewModel::class.java)
//    val profileSeekerViewModel = viewModel(modelClass = ProfileSeekerViewModel::class.java)
//    val messagesSeekerViewModel = viewModel(modelClass = MessagesSeekerViewModel::class.java)


    Scaffold(
        bottomBar = { SeekerBottomNavigationBar(navController) },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                SeekerMainNavigation(
                    navController,
//                    homeSeekerViewModel,
//                    savesSeekerViewModel,
//                    searchSeekerViewModel,
//                    messagesSeekerViewModel,
//                    profileSeekerViewModel
                )
            }
        })


}

@Preview(name = "SeekerMainPage")
@Composable
private fun PreviewSeekerMainPage() {
    SeekerMainPage()
}