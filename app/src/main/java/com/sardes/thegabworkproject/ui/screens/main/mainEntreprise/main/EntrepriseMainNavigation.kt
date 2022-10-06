package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sardes.thegabworkproject.navigation.EntrepriseInterfaceScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.home.HomeEntrepriseScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.home.HomeEntrepriseViewModel

@Composable
fun EntrepriseMainNavigation(
    navController: NavHostController,
    homeViewModel: HomeEntrepriseViewModel?,
//    postsViewModel: PostsEntrepriseViewModel?,
//    newPostViewModel: NewPostViewModel?,
//    applicantsViewModel: ApplicantsViewModel?,
//    messagesViewModel: MessagesEntrepriseViewModel?,
//    profileViewModel: ProfileEntrepriseViewModel?,
//    searchViewModel: SearchViewModel?
) {
    NavHost(
        navController,
        route = EntrepriseInterfaceScreen.EntrepriseMainNavigation.route,
        startDestination = EntrepriseInterfaceScreen.EntrepriseHome.route
    ) {

//        homeEntrepriseScreen(navController)
        composable(EntrepriseInterfaceScreen.EntrepriseHome.route) {
            HomeEntrepriseScreen(homeViewModel) {
/*
                    postId ->
                navController.navigate(EntreprisePostsScreen.EntreprisePostsApplicants.route + "?id=$postId") {
                    launchSingleTop = true
                }
*/
            }
            }
        }
/*
//        postsEntrepriseScreen(navController)
        composable(EntrepriseInterfaceScreen.EntreprisePosts.route) {
            PostsEntrepriseScreen(
                postsViewModel,
                CreatePost = { navController.navigate(EntreprisePostsScreen.EntrepriseNewPost.route) }
            ) { postId ->
                navController.navigate(EntreprisePostsScreen.EntreprisePostsApplicants.route + "?id=$postId") {
                    launchSingleTop = true
                }
            }
        }

        composable(EntreprisePostsScreen.EntrepriseNewPost.route) {
            NewPostScreen(
                newPostViewModel,
                homeViewModel
            ) {
                navController.navigateUp()
            }
        }

        composable(
            route = EntreprisePostsScreen.EntreprisePostsApplicants.route + "?id={id}",
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
                defaultValue = ""
            })
        ) { entry ->
            ApplicantsScreen(
                postId = entry.arguments?.getString("id") as String,
                applicantsViewModel = applicantsViewModel
            ) {
                navController.navigateUp()
            }
        }

//        searchEntrepriseScreen(navController)
        composable(EntrepriseInterfaceScreen.EntrepriseSearch.route) {
            SearchEntrepriseScreen(searchViewModel)
        }

//        messagesEntrepriseScreen(navController)
        composable(EntrepriseInterfaceScreen.EntrepriseMessages.route) {
            MessagesEntrepriseScreen(
                messagesViewModel,
                homeViewModel,
                onConversationClick = {conversationId ->
                    navController.navigate(EntrepriseMessagesScreen.EntrepriseConversationScreen.route + "?id=$conversationId"){
                        launchSingleTop = true
                    }
                }
            )
        }

//        profileEntrepriseScreen(navController)
        composable(EntrepriseInterfaceScreen.EntrepriseProfile.route) {
            ProfileEntrepriseScreen(profileViewModel = profileViewModel)
        }



        composable(
            route = EntrepriseMessagesScreen.EntrepriseConversationScreen.route + "?id={id}",
            arguments = listOf(navArgument("id"){
                type = NavType.StringType
                defaultValue = ""
            })
        ){entry ->
            EntrepriseConversationScreen(
                viewModel = messagesViewModel,
                conversationId = entry.arguments?.getString("id") as String
            )
        }

    }
*/
    }
