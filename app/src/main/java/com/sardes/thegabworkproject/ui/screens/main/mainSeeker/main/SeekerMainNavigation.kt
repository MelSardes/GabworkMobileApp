package com.sardes.thegabworkproject.ui.screens.main.mainSeeker.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sardes.thegabworkproject.navigation.SeekerInterfaceScreen
import com.sardes.thegabworkproject.ui.screens.main.mainSeeker.home.HomeSeekerScreen
import com.sardes.thegabworkproject.ui.screens.main.mainSeeker.message.MessagesSeekerScreen
import com.sardes.thegabworkproject.ui.screens.main.mainSeeker.profile.ProfileSeekerScreen
import com.sardes.thegabworkproject.ui.screens.main.mainSeeker.saves.SavesSeekerScreen
import com.sardes.thegabworkproject.ui.screens.main.mainSeeker.search.SearchSeekerScreen

@Composable
fun SeekerMainNavigation(
    navController           : NavHostController,
//    homeSeekerViewModel     : HomeSeekerViewModel?,
//    savesSeekerViewModel    : SavesSeekerViewModel?,
//    searchSeekerViewModel   : SearchSeekerViewModel?,
//    messagesSeekerViewModel : MessagesSeekerViewModel?,
//    profileSeekerViewModel  : ProfileSeekerViewModel?
) {

    NavHost(
        navController = navController,
        route = SeekerInterfaceScreen.SeekerMain.route,
        startDestination = SeekerInterfaceScreen.SeekerHome.route
    ){
        composable(SeekerInterfaceScreen.SeekerHome.route){
            HomeSeekerScreen()
        }

        composable(SeekerInterfaceScreen.SeekerSaves.route){
            SavesSeekerScreen()
        }

        composable(SeekerInterfaceScreen.SeekerSearch.route){
            SearchSeekerScreen()
        }

        composable(SeekerInterfaceScreen.SeekerMessages.route){
            MessagesSeekerScreen()
        }

        composable(SeekerInterfaceScreen.SeekerProfile.route){
            ProfileSeekerScreen()
        }
    }



}

/*
@Preview(name = "SeekerMainNavigation")
@Composable
private fun PreviewSeekerMainNavigation() {
    SeekerMainNavigation()
}*/
