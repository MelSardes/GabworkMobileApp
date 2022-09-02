/*

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sardes.thegabworkproject.navigation.NavigationItem
import com.sardes.thegabworkproject.repository.main.entreprise.MainEntrepriseRepository
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.home.HomeEntrepriseScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.message.MessagesEntrepriseScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.PostsEntrepriseScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.applicants.ApplicantsScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.applicants.ApplicantsViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.standalonepost.create.NewPostScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.standalonepost.create.NewPostViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.profile.ProfileEntrepriseScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.search.SearchEntrepriseScreen
import com.sardes.thegabworkproject.ui.theme.graySurface

enum class TestNavType {
    HOME, POSTS, SEARCH, MESSAGES, PROFILE
}

@Preview
@Composable
fun Test() {
    val navController = rememberNavController()
    TestScreen(navController = navController)
}

@Composable
fun TestScreen(navController: NavHostController) {

    TestContent(navController = navController)

    NavHost(
        navController = navController,
        startDestination = "HOME"
    ){
        composable("NEW_POST"){
            NewPostScreen(postViewModel = NewPostViewModel()) {
                navController.navigateUp()
            }
        }

        composable("HOME") {
            HomeEntrepriseScreen(
                newPost = {
                    navController.navigate(NavigationItem.NewPost.route)
                },
                onPostClick = {}
            )
        }

        composable("POSTS") {
            PostsEntrepriseScreen(
                CreatePost = { navController.navigate(NavigationItem.NewPost.route) }
            ) { postId ->
                navController.navigate(NavigationItem.Applicants.route + "?id=$postId") {
                    launchSingleTop = true
                }
            }
        }
        composable("SEARCH") {
            SearchEntrepriseScreen()
        }
        composable("MESSAGES") {
            MessagesEntrepriseScreen()
        }
        composable("PROFILE") {
            ProfileEntrepriseScreen()
        }

        composable(
            route =   "APPLICANTS?id={id}",
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
                defaultValue = ""
            })
        ) { entry ->
            ApplicantsScreen(
                applicantsViewModel = ApplicantsViewModel(repository = MainEntrepriseRepository()),
                postId = entry.arguments?.getString("id") as String
            ) {
                navController.navigateUp()
            }
        }
    }
}

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun TestContent(navController: NavHostController) {
    val testNavItemState = rememberSaveable { mutableStateOf(TestNavType.HOME) }
    Scaffold(
        bottomBar = { TestBottomNavigation(testNavItemState) },
        content = { paddingValues ->
            TestBodyContent(
                testNavItemState.value,
                modifier = Modifier.padding(paddingValues),
                navController = navController
            )
        }
    )
}

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun TestBottomNavigation(testNavItemState: MutableState<TestNavType>) {
    val bottomNavBackground =
        if (isSystemInDarkTheme()) graySurface else MaterialTheme.colors.background
    BottomNavigation(backgroundColor = bottomNavBackground) {
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Outlined.Home, contentDescription = null) },
            selected = testNavItemState.value == TestNavType.HOME,
            onClick = { testNavItemState.value = TestNavType.HOME },
            label = { Text(text = "HOME") },
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Outlined.Search, contentDescription = null) },
            selected = testNavItemState.value == TestNavType.POSTS,
            onClick = { testNavItemState.value = TestNavType.POSTS },
            label = { Text(text = "POSTS") }
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Outlined.Search, contentDescription = null) },
            selected = testNavItemState.value == TestNavType.SEARCH,
            onClick = { testNavItemState.value = TestNavType.SEARCH },
            label = { Text(text = "SEARCH") }
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Outlined.LibraryMusic, contentDescription = null) },
            selected = testNavItemState.value == TestNavType.MESSAGES,
            onClick = { testNavItemState.value = TestNavType.MESSAGES },
            label = { Text(text = "MESSAGES") }
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Outlined.LibraryMusic, contentDescription = null) },
            selected = testNavItemState.value == TestNavType.PROFILE,
            onClick = { testNavItemState.value = TestNavType.PROFILE },
            label = { Text(text = "PROFILE") }
        )
    }
}


@Composable
fun TestBodyContent(
    spotifyNavType: TestNavType,
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    Crossfade(
        targetState = spotifyNavType,
        modifier = modifier,
    ) { navType ->
        when (navType) {
            TestNavType.HOME -> HomeEntrepriseScreen(onPostClick = { */
/*TODO*//*
 }) {}
            TestNavType.POSTS -> PostsEntrepriseScreen(
                CreatePost = { navController.navigate(NavigationItem.NewPost.route) }
            ) { postId ->
                navController.navigate(NavigationItem.Applicants.route + "?id=$postId") {
                    launchSingleTop = true
                }
            }
            TestNavType.SEARCH -> SearchEntrepriseScreen()
            TestNavType.MESSAGES -> MessagesEntrepriseScreen()
            TestNavType.PROFILE -> ProfileEntrepriseScreen()
        }
    }
}

*/
