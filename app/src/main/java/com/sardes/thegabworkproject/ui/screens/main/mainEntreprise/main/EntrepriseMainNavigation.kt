package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sardes.thegabworkproject.navigation.EntrepriseInterfaceScreen
import com.sardes.thegabworkproject.navigation.EntreprisePostsScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.home.HomeEntrepriseScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.home.HomeEntrepriseViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.message.MessagesEntrepriseScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.message.MessagesEntrepriseViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.PostsEntrepriseScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.PostsEntrepriseViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.applicants.ApplicantsScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.applicants.ApplicantsViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.standalonepost.create.NewPostScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.standalonepost.create.NewPostViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.profile.ProfileEntrepriseScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.search.SearchEntrepriseScreen

@Composable
fun EntrepriseMainNavigation(
    navController: NavHostController,
    homeViewModel: HomeEntrepriseViewModel?,
    postsViewModel: PostsEntrepriseViewModel?,
    newPostViewModel: NewPostViewModel?,
    applicantsViewModel: ApplicantsViewModel?,
    messagesViewModel: MessagesEntrepriseViewModel
) {
    NavHost(
        navController,
        route = EntrepriseInterfaceScreen.EntrepriseMainNavigation.route,
        startDestination = EntrepriseInterfaceScreen.EntrepriseHome.route
    ) {

//        homeEntrepriseScreen(navController)
        composable(EntrepriseInterfaceScreen.EntrepriseHome.route) {
            HomeEntrepriseScreen(homeViewModel) { postId ->
                navController.navigate(EntreprisePostsScreen.EntreprisePostsApplicants.route + "?id=$postId") {
                    launchSingleTop = true
                }
            }
        }

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
            SearchEntrepriseScreen()
        }

//        messagesEntrepriseScreen(navController)
        composable(EntrepriseInterfaceScreen.EntrepriseMessages.route) {
            MessagesEntrepriseScreen(messagesViewModel)
        }

//        profileEntrepriseScreen(navController)
        composable(EntrepriseInterfaceScreen.EntrepriseProfile.route) {
            ProfileEntrepriseScreen()
        }

    }
}
