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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sardes.thegabworkproject.navigation.NavigationItem
import com.sardes.thegabworkproject.ui.screens.GetStartedScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.home.HomeEntrepriseScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.message.MessagesEntrepriseScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.PostsEntrepriseScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.standalonepost.create.NewPostScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.profile.ProfileEntrepriseScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.search.SearchEntrepriseScreen
import com.sardes.thegabworkproject.ui.theme.BlueFlag
import com.sardes.thegabworkproject.ui.theme.BlueVariant
import com.sardes.thegabworkproject.ui.theme.YellowFlag

/*
@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun EntrepriseMainPage0(
    viewModel: HomeEntrepriseViewModel = HomeEntrepriseViewModel()
){
    data class TabItem(
        val icon: ImageVector,
        val contentDescription: String
    )

    var tabIndex by remember { mutableStateOf(0) }
    val homeScreens = listOf(
        TabItem(Icons.Rounded.Home, "Home"),
        TabItem(Icons.Rounded.FormatListBulleted, "Applications"),
        TabItem(Icons.Rounded.Search, "Search"),
        TabItem(Icons.Rounded.Message, "Messages"),
        TabItem(Icons.Rounded.Person, "Profile"),
    )

    LaunchedEffect(key1 = Unit){
        viewModel.getEntrepriseInformations()
    }

    TheGabworkProjectTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .fillMaxSize()
        ) {

            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(0.9f)
                .align(Alignment.Start)) {
                when (tabIndex) {
                    0 -> HomeEntrepriseScreen{}
                    1 -> PostsEntrepriseScreen(
                        onPostClick = {},
                        postsViewModel = PostsEntrepriseViewModel(),
                        newNav = {},
                        navToNewPost = {},
                        navController = navController
                    )
                    2 -> SearchEntrepriseScreen()
                    3 -> MessagesEntrepriseScreen()
                    4 -> ProfileEntrepriseScreen()
                }
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.End)) {
                TabRow(
                    selectedTabIndex = tabIndex,
                    backgroundColor = Color(0xFF2A3855),
                    contentColor = YellowFlag,
                    modifier = Modifier
                ) {
                    homeScreens.forEachIndexed { i, item ->
                        Tab(
                            selected = tabIndex == i,
                            onClick = { tabIndex = i },
                            modifier = Modifier.heightIn(48.dp)
                        ) {
                            Icon(item.icon, contentDescription = item.contentDescription,
                                tint = if (i == tabIndex) YellowFlag
                                else Color.White.copy(alpha = 0.5f)
                            )
                        }
                    }
                }
            }

        }
    }
}
*/


@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun EntrepriseMainPage() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(navController = navController)
            }
        },
        backgroundColor = Color.White// Set background color to avoid the white flashing when you switch between screens
    )
}


@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeEntrepriseScreen{}
        }
        composable(NavigationItem.Posts.route) {
            PostsEntrepriseScreen(
                onPostClick = {},
                navToNewPost = { /*TODO*/ },
                navController = navController
            ) {

            }
        }
        composable(NavigationItem.Search.route) {
            SearchEntrepriseScreen()
        }
        composable(NavigationItem.Messages.route) {
            MessagesEntrepriseScreen()
        }
        composable(NavigationItem.Profile.route) {
            ProfileEntrepriseScreen()
        }
        composable(NavigationItem.Start.route) {
            GetStartedScreen(null,{}){}
        }
        composable(NavigationItem.NewPost.route) {
            NewPostScreen()
        }
    }
}


@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = "Gabwork", style = MaterialTheme.typography.h5) },
        backgroundColor = BlueFlag,
        contentColor = Color.White,
        modifier = Modifier.clip(RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp))
    )
}

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Posts,
        NavigationItem.Search,
        NavigationItem.Messages,
        NavigationItem.Profile
    )
    BottomNavigation(
        backgroundColor = BlueVariant,
        contentColor = Color.White,
        modifier = Modifier.clip(RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp))
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = YellowFlag,
                unselectedContentColor = Color.White.copy(0.4f),
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
