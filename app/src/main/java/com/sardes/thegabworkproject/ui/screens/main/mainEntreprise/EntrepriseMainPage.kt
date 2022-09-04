package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sardes.thegabworkproject.navigation.EntrepriseInterfaceScreen
import com.sardes.thegabworkproject.navigation.EntreprisePostsScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.home.HomeEntrepriseScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.home.HomeEntrepriseViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.message.MessagesEntrepriseScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.PostsEntrepriseScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.PostsEntrepriseViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.applicants.ApplicantsScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.standalonepost.create.NewPostScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.standalonepost.create.NewPostViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.profile.ProfileEntrepriseScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.search.SearchEntrepriseScreen
import com.sardes.thegabworkproject.ui.theme.GWpalette.Gunmetal
import com.sardes.thegabworkproject.ui.theme.GWpalette.ImperialRed


@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun EntrepriseMainPage() {
    val navController = rememberNavController()
    val homeViewModel = viewModel(modelClass = HomeEntrepriseViewModel::class.java)
    val postsViewModel = viewModel(modelClass = PostsEntrepriseViewModel::class.java)
    val newPostViewModel = viewModel(modelClass = NewPostViewModel::class.java)

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                EntrepriseMainNavigation(
                    navController,
                    homeViewModel,
                    postsViewModel,
                    newPostViewModel
                )
            }
        },
        backgroundColor = Color.White// Set background color to avoid the white flashing when you switch between screens
    )
}


@Composable
fun EntrepriseMainNavigation(
    navController: NavHostController,
    homeViewModel: HomeEntrepriseViewModel,
    postsViewModel: PostsEntrepriseViewModel,
    newPostViewModel: NewPostViewModel,
) {
    NavHost(
        navController,
        route = EntrepriseInterfaceScreen.EntrepriseMainNavigation.route,
        startDestination = EntrepriseInterfaceScreen.EntrepriseHome.route
    ) {

//        homeEntrepriseScreen(navController)
        composable(EntrepriseInterfaceScreen.EntrepriseHome.route) {
            HomeEntrepriseScreen(homeViewModel){ postId ->
                navController.navigate(EntreprisePostsScreen.EntreprisePostsApplicants.route + "?id=$postId") {
                    launchSingleTop = true
                }
            }
        }

//        postsEntrepriseScreen(navController)
        composable(EntrepriseInterfaceScreen.EntreprisePosts.route){
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
            NewPostScreen(newPostViewModel) {
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
                postId = entry.arguments?.getString("id") as String
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
            MessagesEntrepriseScreen()
        }

//        profileEntrepriseScreen(navController)
        composable(EntrepriseInterfaceScreen.EntrepriseProfile.route) {
            ProfileEntrepriseScreen()
        }

    }
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

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        EntrepriseInterfaceScreen.EntrepriseHome,
        EntrepriseInterfaceScreen.EntreprisePosts,
        EntrepriseInterfaceScreen.EntrepriseSearch,
        EntrepriseInterfaceScreen.EntrepriseMessages,
        EntrepriseInterfaceScreen.EntrepriseProfile,
    )
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black,
        modifier = Modifier.clip(RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)),
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(
                    painterResource(id = item.icon),
                    contentDescription = item.title
                ) },
                label = { Text(text = item.title) },
                selectedContentColor = ImperialRed,
                unselectedContentColor = Gunmetal,
                alwaysShowLabel = false,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}
