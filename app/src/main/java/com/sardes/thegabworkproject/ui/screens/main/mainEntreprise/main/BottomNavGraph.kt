/*
package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sardes.thegabworkproject.navigation.BottomBarScreen
import com.sardes.thegabworkproject.navigation.Screen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.home.HomeEntrepriseScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.message.MessagesEntrepriseScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.PostsEntrepriseScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.profile.ProfileEntrepriseScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.search.SearchEntrepriseScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
    ){

        composable(route = BottomBarScreen.Home.route){
            HomeEntrepriseScreen{}
        }
        composable(route = BottomBarScreen.Posts.route){
            PostsEntrepriseScreen(
                onPostClick = {postId ->
                    navController.navigate(Screen.PostApplicants.route + "?id=$postId"){
                        launchSingleTop = true
                    }
                },
                navToNewPost = {
                    navController.navigate(Screen.NewPost.route){
                        launchSingleTop = true
                    }
                },
                newNav = {
                    navController.navigate(Screen.NewPost.route)
                }
            )

        }

        composable(route = BottomBarScreen.Search.route){
            SearchEntrepriseScreen()
        }
        composable(route = BottomBarScreen.Message.route){
            MessagesEntrepriseScreen()
        }
        composable(route = BottomBarScreen.Profile.route){
            ProfileEntrepriseScreen()
        }
    }

}
*/
