package com.sardes.thegabworkproject.ui.screens.main.mainStandard.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sardes.thegabworkproject.navigation.StandardInterfaceScreen
import com.sardes.thegabworkproject.navigation.StandardMessagesScreen
import com.sardes.thegabworkproject.navigation.StandardPostScreen
import com.sardes.thegabworkproject.ui.screens.components.ActivityAreaDetailScreen
import com.sardes.thegabworkproject.ui.screens.main.SearchViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.search.components.GabworkSearchGrid
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.components.ApplicationRequiredInformationsMessage
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.components.CompleteProfile
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.components.PostDetailsScreen
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.home.HomeStandardScreen
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.home.HomeStandardViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.message.MessagesStandardScreen
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.message.MessagesStandardViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.message.conversation.StandardConversationScreen
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.profile.ProfileStandardScreen
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.profile.ProfileStandardViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.saves.SavesStandardScreen
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.saves.SavesStandardViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.search.SearchStandardScreen

@Composable
fun StandardMainNavigation(
    navController: NavHostController,
    homeStandardViewModel: HomeStandardViewModel?,
    savesStandardViewModel    : SavesStandardViewModel?,
    searchViewModel: SearchViewModel,
    messagesStandardViewModel: MessagesStandardViewModel?,
    profileStandardViewModel  : ProfileStandardViewModel?
) {

    NavHost(
        navController = navController,
        route = StandardInterfaceScreen.StandardMain.route,
        startDestination = StandardInterfaceScreen.StandardHome.route
    ) {
        composable(StandardInterfaceScreen.StandardHome.route) {
            HomeStandardScreen(
                homeStandardViewModel,
                onPostClick = { postId ->
                    navController.navigate(StandardPostScreen.DetailsPostScreen.route + "?id=$postId") {
                        launchSingleTop = true
                    }
                },
                onLogoClick = { entrepriseId ->
                    navController.navigate(StandardMessagesScreen.StandardConversationScreen.route + "?id=$entrepriseId") {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(StandardInterfaceScreen.StandardSaves.route) {
            SavesStandardScreen(savesStandardViewModel, homeStandardViewModel)
        }

        composable(StandardInterfaceScreen.StandardSearch.route) {
            SearchStandardScreen(
                searchViewModel,
                homeStandardViewModel,
                navToDiscover = {
                    navController.navigate("Discover")
                }
            )
        }
        
        composable("Discover"){
            GabworkSearchGrid {index ->
                navController.navigate("AreaDetails?index=$index")
            }
        }

        composable(
            "AreaDetails?index={index}",
            arguments = listOf(navArgument("index") {
                type = NavType.IntType
                defaultValue = 0
            })
        ) { entry ->
            ActivityAreaDetailScreen(
                index = entry.arguments?.getInt("id") as Int,
                viewModel = SearchViewModel()
            ) { postId ->
                navController.navigate(StandardPostScreen.DetailsPostScreen.route + "?id=$postId")
            }
        }

        composable(StandardInterfaceScreen.StandardMessages.route) {
            MessagesStandardScreen(
                messagesStandardViewModel,
                homeStandardViewModel,
                onCardClick = { conversationId ->
                    navController.navigate(StandardMessagesScreen.StandardConversationScreen.route + "?id=$conversationId") {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(StandardInterfaceScreen.StandardProfile.route) {
            ProfileStandardScreen()
        }


        composable(route = "CompleteProfile"){
            CompleteProfile(
                viewModel = homeStandardViewModel,
                onFinish = {
                    navController.navigate(StandardInterfaceScreen.StandardHome.route){popUpTo("CompleteProfile")}
                }
            )
        }

        composable(
            route = StandardMessagesScreen.StandardConversationScreen.route + "?id={id}",
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
                defaultValue = ""
            })
        ) { entry ->
            StandardConversationScreen(
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
        ) { entry ->
            PostDetailsScreen(
                viewModel = homeStandardViewModel,
                postId = entry.arguments?.getString("id") as String,
                onMessageClick = {entrepriseId ->
                    navController.navigate(StandardMessagesScreen.StandardConversationScreen.route + "?id=$entrepriseId") {
                        launchSingleTop = true
                    }
                },
                completeAccount = {
                    navController.navigate("completeAccountMessage"){launchSingleTop = true}
                }
            )
        }

        composable(route = "completeAccountMessage"){
            ApplicationRequiredInformationsMessage(
                navToComplete = {navController.navigate("CompleteProfile")}
            )
        }

        composable("CompleteProfile"){
            CompleteProfile(viewModel = homeStandardViewModel) {
                navController.navigate(StandardInterfaceScreen.StandardHome.route)
            }
        }
    }
}
