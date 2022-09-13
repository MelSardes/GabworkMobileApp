package com.sardes.thegabworkproject.ui.screens.main.mainStandard.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sardes.thegabworkproject.navigation.StandardInterfaceScreen
import com.sardes.thegabworkproject.navigation.StandardMessageScreen
import com.sardes.thegabworkproject.navigation.StandardPostScreen
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.components.PostDetailsScreen
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.home.HomeStandardScreen
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.home.HomeStandardViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.message.ConversationScreen
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.message.MessagesStandardScreen
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.message.MessagesStandardViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.profile.ProfileStandardScreen
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.saves.SavesStandardScreen
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.search.SearchStandardScreen

@Composable
fun StandardMainNavigation(
    navController           : NavHostController,
    homeStandardViewModel     : HomeStandardViewModel?,
//    savesStandardViewModel    : SavesStandardViewModel?,
//    searchStandardViewModel   : SearchStandardViewModel?,
    messagesStandardViewModel : MessagesStandardViewModel?,
//    profileStandardViewModel  : ProfileStandardViewModel?
) {

    NavHost(
        navController = navController,
        route = StandardInterfaceScreen.StandardMain.route,
        startDestination = StandardInterfaceScreen.StandardHome.route
    ){
        composable(StandardInterfaceScreen.StandardHome.route){
            HomeStandardScreen(
                homeStandardViewModel,
                onPostClick = { postId ->
                    navController.navigate(StandardPostScreen.DetailsPostScreen.route + "?id=$postId") {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(StandardInterfaceScreen.StandardSaves.route){
            SavesStandardScreen()
        }

        composable(StandardInterfaceScreen.StandardSearch.route){
            SearchStandardScreen()
        }

        composable(StandardInterfaceScreen.StandardMessages.route){
            MessagesStandardScreen(
                messagesStandardViewModel,
                homeStandardViewModel,
                onCardClick = {conversationId ->
                    navController.navigate(StandardMessageScreen.StandardConversationScreen.route + "?id=$conversationId"){
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(StandardInterfaceScreen.StandardProfile.route){
            ProfileStandardScreen()
        }


        composable(
            route = StandardMessageScreen.StandardConversationScreen.route + "?id={id}",
            arguments = listOf(navArgument("id"){
                type = NavType.StringType
                defaultValue = ""
            })
        ){entry ->
            ConversationScreen(
                messagesStandardViewModel,
                conversationId = entry.arguments?.getString("id") as String,
            )
        }


        composable(
            route = StandardPostScreen.DetailsPostScreen.route + "?id={id}",
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
                defaultValue = ""
            })
        ){entry ->
            PostDetailsScreen(
                homeStandardViewModel = homeStandardViewModel,
                postId = entry.arguments?.getString("id")as String,
            )
        }
    }
}
