package com.sardes.thegabworkproject.ui.screens.main.mainSeeker.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sardes.thegabworkproject.navigation.SeekerInterfaceScreen
import com.sardes.thegabworkproject.ui.screens.main.mainSeeker.home.HomeSeekerScreen
import com.sardes.thegabworkproject.ui.screens.main.mainSeeker.home.HomeSeekerViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainSeeker.message.MessagesSeekerScreen
import com.sardes.thegabworkproject.ui.screens.main.mainSeeker.message.MessagesSeekerViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainSeeker.profile.ProfileSeekerScreen
import com.sardes.thegabworkproject.ui.screens.main.mainSeeker.profile.ProfileSeekerViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainSeeker.saves.SavesSeekerScreen
import com.sardes.thegabworkproject.ui.screens.main.mainSeeker.saves.SavesSeekerViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainSeeker.search.SearchSeekerScreen
import com.sardes.thegabworkproject.ui.screens.main.mainSeeker.search.SearchSeekerViewModel

@Composable
fun SeekerMainNavigation(
    navController           : NavHostController,
    homeSeekerViewModel     : HomeSeekerViewModel?,
    savesSeekerViewModel    : SavesSeekerViewModel?,
    searchSeekerViewModel   : SearchSeekerViewModel?,
    messagesSeekerViewModel : MessagesSeekerViewModel?,
    profileSeekerViewModel  : ProfileSeekerViewModel?
) {

    NavHost(
        navController = navController,
        route = SeekerInterfaceScreen.SeekerMain.route,
        startDestination = SeekerInterfaceScreen.SeekerHome.route
    ){
        composable(SeekerInterfaceScreen.SeekerHome.route){
            HomeSeekerScreen(homeSeekerViewModel)
        }

        composable(SeekerInterfaceScreen.SeekerSaves.route){
            SavesSeekerScreen(savesSeekerViewModel)
        }

        composable(SeekerInterfaceScreen.SeekerSearch.route){
            SearchSeekerScreen(searchSeekerViewModel)
        }

        composable(SeekerInterfaceScreen.SeekerMessages.route){
            MessagesSeekerScreen(messagesSeekerViewModel)
        }

        composable(SeekerInterfaceScreen.SeekerProfile.route){
            ProfileSeekerScreen(profileSeekerViewModel)
        }
    }



}

/*
@Preview(name = "SeekerMainNavigation")
@Composable
private fun PreviewSeekerMainNavigation() {
    SeekerMainNavigation()
}*/
