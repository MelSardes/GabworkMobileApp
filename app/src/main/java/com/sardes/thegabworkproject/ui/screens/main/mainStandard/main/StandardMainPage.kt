package com.sardes.thegabworkproject.ui.screens.main.mainStandard.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.home.HomeStandardViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.message.MessagesStandardViewModel
import com.sardes.thegabworkproject.ui.theme.GWpalette.Gunmetal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardMainPage() {
    val navController = rememberNavController()
    val homeStandardViewModel = viewModel(modelClass = HomeStandardViewModel::class.java)
//    val savesStandardViewModel = viewModel(modelClass = SavesStandardViewModel::class.java)
//    val searchStandardViewModel = viewModel(modelClass = SearchStandardViewModel::class.java)
//    val profileStandardViewModel = viewModel(modelClass = ProfileStandardViewModel::class.java)
    val messagesStandardViewModel = viewModel(modelClass = MessagesStandardViewModel::class.java)


    Scaffold(

        bottomBar = { StandardBottomNavigationBar(navController) },
        content = { padding ->
            Box(
                modifier = Modifier
                    .background(Gunmetal)
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color.White)
            ) {
                StandardMainNavigation(
                    navController,
                    homeStandardViewModel,
//                    savesStandardViewModel,
//                    searchStandardViewModel,
                    messagesStandardViewModel,
//                    profileStandardViewModel
                )
            }
        }
    )
}


@Preview(name = "StandardMainPage", showSystemUi = true)
@Composable
private fun PreviewStandardMainPage() {
    StandardMainPage()
}