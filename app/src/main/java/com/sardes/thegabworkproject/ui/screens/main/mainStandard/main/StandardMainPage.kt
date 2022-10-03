package com.sardes.thegabworkproject.ui.screens.main.mainStandard.main


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sardes.thegabworkproject.navigation.StandardInterfaceScreen
import com.sardes.thegabworkproject.ui.screens.main.SearchViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.home.HomeStandardViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.message.MessagesStandardViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.profile.ProfileStandardViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.saves.SavesStandardViewModel
import com.sardes.thegabworkproject.ui.theme.GWpalette.Gunmetal

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun StandardMainPage() {
    val navController = rememberNavController()
    val searchViewModel = viewModel(modelClass = SearchViewModel::class.java)
    val homeStandardViewModel = viewModel(modelClass = HomeStandardViewModel::class.java)
    val savesStandardViewModel = viewModel(modelClass = SavesStandardViewModel::class.java)
    val profileStandardViewModel = viewModel(modelClass = ProfileStandardViewModel::class.java)
    val messagesStandardViewModel = viewModel(modelClass = MessagesStandardViewModel::class.java)

    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    // Subscribe to navBackStackEntry, required to get current route
    val navBackStackEntry by navController.currentBackStackEntryAsState()


    when (navBackStackEntry?.destination?.route) {
        StandardInterfaceScreen.StandardHome.route -> bottomBarState.value = true
        StandardInterfaceScreen.StandardSaves.route -> bottomBarState.value = true
        StandardInterfaceScreen.StandardSearch.route -> bottomBarState.value = true
        StandardInterfaceScreen.StandardMessages.route -> bottomBarState.value = true
        StandardInterfaceScreen.StandardProfile.route -> bottomBarState.value = true
        else -> bottomBarState.value = false
    }


    Scaffold(
        bottomBar = { StandardBottomNavigationBar(navController, bottomBarState) },
        content = { padding ->
            Box(
                modifier = Modifier
                    .background(Gunmetal)
                    .fillMaxSize()
                    .padding(padding)
            ) {
                StandardMainNavigation(
                    navController,
                    homeStandardViewModel,
                    savesStandardViewModel,
                    searchViewModel,
                    messagesStandardViewModel,
                    profileStandardViewModel
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