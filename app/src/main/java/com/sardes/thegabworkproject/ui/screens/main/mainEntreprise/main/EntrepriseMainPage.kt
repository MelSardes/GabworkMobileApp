package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.main

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.home.HomeEntrepriseViewModel
import com.sardes.thegabworkproject.ui.theme.GWpalette.Gunmetal


@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun EntrepriseMainPage() {
    val navController = rememberNavController()
    val homeViewModel = viewModel(modelClass = HomeEntrepriseViewModel::class.java)
//    val postsViewModel = viewModel(modelClass = PostsEntrepriseViewModel::class.java)
//    val newPostViewModel = viewModel(modelClass = NewPostViewModel::class.java)
//    val messagesViewModel = viewModel(modelClass = MessagesEntrepriseViewModel::class.java)
//    val applicantsViewModel = viewModel(modelClass = ApplicantsViewModel::class.java)
//    val profileViewModel = viewModel(modelClass = ProfileEntrepriseViewModel::class.java)
//    val searchViewModel = viewModel(modelClass = SearchViewModel::class.java)

/*
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    // Subscribe to navBackStackEntry, required to get current route
    val navBackStackEntry by navController.currentBackStackEntryAsState()


    when (navBackStackEntry?.destination?.route) {
        EntrepriseInterfaceScreen.EntrepriseHome.route -> bottomBarState.value = true
        EntrepriseInterfaceScreen.EntreprisePosts.route -> bottomBarState.value = true
        EntrepriseInterfaceScreen.EntrepriseSearch.route -> bottomBarState.value = true
        EntrepriseInterfaceScreen.EntrepriseMessages.route -> bottomBarState.value = true
        EntrepriseInterfaceScreen.EntrepriseProfile.route -> bottomBarState.value = true
        else -> bottomBarState.value = false
    }
*/


    Scaffold(
//        bottomBar = { EntrepriseBottomNavigationBar(navController, bottomBarState) },
        content = { padding ->
            Box(modifier = Modifier
                .background(Gunmetal)
                .fillMaxSize()
                .padding(padding)
//                .clip(RoundedCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp))
            ) {
                EntrepriseMainNavigation(
                    navController,
                    homeViewModel,
//                    postsViewModel,
//                    newPostViewModel,
//                    applicantsViewModel,
//                    messagesViewModel,
//                    profileViewModel,
//                    searchViewModel
                )
            }
        },
    )
}



@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = "Gabwork", style = MaterialTheme.typography.h5) },
        backgroundColor = Color.White,
        contentColor = Color.White,
        modifier = Modifier.clip(RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp))
    )
}

