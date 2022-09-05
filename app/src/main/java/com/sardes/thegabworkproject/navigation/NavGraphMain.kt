package com.sardes.thegabworkproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.ui.screens.GetStartedScreen
import com.sardes.thegabworkproject.ui.screens.control.AuthControl
import com.sardes.thegabworkproject.ui.screens.login.LoginScreen
import com.sardes.thegabworkproject.ui.screens.login.LoginViewModel
import com.sardes.thegabworkproject.ui.screens.login_and_signup.SelectSignUpAccount
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.EntrepriseMainPage
import com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.screens.EntrepriseAccountSignUpScreen
import com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.EntrepriseAccountSignUpViewModel
import com.sardes.thegabworkproject.ui.screens.signup.independantsignup.IndependantAccountSignUpSceen
import com.sardes.thegabworkproject.ui.screens.signup.independantsignup.IndependantAccountSignUpViewModel
import com.sardes.thegabworkproject.ui.screens.signup.standardsignup.StandardAccountSignUpViewModel
import com.sardes.thegabworkproject.ui.screens.signup.standardsignup.StandardSignUpScreen
import com.sardes.thegabworkproject.ui.screens.splash.SplashScreen

sealed class Interface(val route: String) {

    object HomeInterface : Interface("HomeInterface")
    object AuthInterface : Interface("AuthInterface")

    object EntrepriseInterface : Interface("EntrepriseInterface")
    object StandardInterface : Interface("StandardInterface")
    object IndependantInterface : Interface("IndependantInterface")
    object StudentInterface : Interface("StudentInterface")
    object SeekerInterface : Interface("SeekerInterface")
}

sealed class AuthInterfaceScreen(val route: String) {
    object LoginScreen : AuthInterfaceScreen("LoginScreen")
    object SignUpSelectScreen : AuthInterfaceScreen("SignUpSelectScreen")
    object StandardSignUpScreen : AuthInterfaceScreen("StandardSignUpScreen")
    object IndependantSignUpScreen : AuthInterfaceScreen("IndependantSignUpScreen")
    object EntrepriseSignUpScreen : AuthInterfaceScreen("EntrepriseSignUpScreen")
    object StudentSignUpScreen : AuthInterfaceScreen("StudentSignUpScreen")
    object SeekerSignUpScreen : AuthInterfaceScreen("SeekerSignUpScreen")
}

sealed class HomeInterfaceScreen(val route: String) {
    object Splash : HomeInterfaceScreen("Splash")
    object Control : HomeInterfaceScreen("Control")
    object GetStarted : HomeInterfaceScreen("GetStarted")
}

sealed class EntrepriseInterfaceScreen(var route: String, var icon: Int, var title: String) {
    object EntrepriseMain : EntrepriseInterfaceScreen("EntrepriseInterface/EntrepriseMain", 0, "")
    object EntrepriseMainNavigation :
        EntrepriseInterfaceScreen("EntrepriseInterface/MainNavigation", 0, "")

    object EntrepriseHome :
        EntrepriseInterfaceScreen("EntrepriseInterface/EntrepriseHome", R.drawable.ic_home, "Home")

    object EntreprisePosts : EntrepriseInterfaceScreen(
        "EntrepriseInterface/EntreprisePosts",
        R.drawable.ic_post,
        "Posts"
    )

    object EntrepriseSearch : EntrepriseInterfaceScreen(
        "EntrepriseInterface/EntrepriseSearch",
        R.drawable.ic_search,
        "Chercher"
    )

    object EntrepriseMessages : EntrepriseInterfaceScreen(
        "EntrepriseInterface/EntrepriseMessages",
        R.drawable.ic_message,
        "Messages"
    )

    object EntrepriseProfile : EntrepriseInterfaceScreen(
        "EntrepriseInterface/EntrepriseProfile",
        R.drawable.ic_person,
        "Profil"
    )
}

sealed class SeekerInterfaceScreen(val route: String) {
    object SeekerMain : SeekerInterfaceScreen("SeekerInterface/Main")

    object SeekerHome : SeekerInterfaceScreen("SeekerInterface/SeekerHome")
    object SeekerBookmarks : SeekerInterfaceScreen("SeekerInterface/SeekerBookmarks")
    object SeekerSearch : SeekerInterfaceScreen("SeekerInterface/SeekerSearch")
    object SeekerMessages : SeekerInterfaceScreen("SeekerInterface/SeekerMessages")
    object SeekerProfile : SeekerInterfaceScreen("SeekerInterface/SeekerProfile")
}

sealed class StandardInterfaceScreen(val route: String) {
    object StandardMain : StandardInterfaceScreen("StandardInterface/Main")

    object StandardHome : StandardInterfaceScreen("StandardInterface/StandardHome")
    object StandardBookmarks : StandardInterfaceScreen("StandardInterface/StandardBookmarks")
    object StandardSearch : StandardInterfaceScreen("StandardInterface/StandardSearch")
    object StandardMessages : StandardInterfaceScreen("StandardInterface/StandardMessages")
    object StandardProfile : StandardInterfaceScreen("StandardInterface/StandardProfile")
}

sealed class StudentInterfaceScreen(val route: String) {
    object StudentMain : StudentInterfaceScreen("StudentInterface/Main")

    object StudentHome : StudentInterfaceScreen("StudentInterface/StudentHome")
    object StudentBookmarks : StudentInterfaceScreen("StudentInterface/StudentBookmarks")
    object StudentSearch : StudentInterfaceScreen("StudentInterface/StudentSearch")
    object StudentMessages : StudentInterfaceScreen("StudentInterface/StudentMessages")
    object StudentProfile : StudentInterfaceScreen("StudentInterface/StudentProfile")
}

sealed class IndependantInterfaceScreen(val route: String) {
    object IndependantMain : IndependantInterfaceScreen("IndependantInterface/Main")

    object IndependantHome : IndependantInterfaceScreen("IndependantInterface/IndependantHome")
    object IndependantBookmarks :
        IndependantInterfaceScreen("IndependantInterface/IndependantBookmarks")

    object IndependantSearch : IndependantInterfaceScreen("IndependantInterface/Independantearch")
    object IndependantMessages :
        IndependantInterfaceScreen("IndependantInterface/IndependantMessages")

    object IndependantProfile :
        IndependantInterfaceScreen("IndependantInterface/IndependantProfile")
}


sealed class EntrepriseHomeScreen(val route: String) {
    object EntrepriseHomeMain : EntrepriseHomeScreen("EntrepriseInterface/EntrepriseHome/Main")

    object EntrepriseHomePost : EntrepriseHomeScreen("EntrepriseInterface/EntrepriseHome/Post")
    object EntrepriseHomeApplicant :
        EntrepriseHomeScreen("EntrepriseInterface/EntrepriseHome/Applicant")

    object EntrepriseHomeStudent :
        EntrepriseHomeScreen("EntrepriseInterface/EntrepriseHome/Student")

    object EntrepriseHomeIndependant :
        EntrepriseHomeScreen("EntrepriseInterface/EntrepriseHome/Independant")
}

sealed class EntrepriseSearchScreen(val route: String) {
    object EntrepriseSearchMain :
        EntrepriseSearchScreen("EntrepriseInterface/EntrepriseSearch/Main")
}

sealed class EntrepriseMessagesScreen(val route: String) {
    object EntrepriseMessagesMain :
        EntrepriseMessagesScreen("EntrepriseInterface/EntrepriseMessages/Main")
}

sealed class EntreprisePostsScreen(val route: String) {
    object EntreprisePostsMain : EntreprisePostsScreen("EntrepriseInterface/EntreprisePosts/Main")

    object EntreprisePostsApplicants :
        EntreprisePostsScreen("EntrepriseInterface/EntreprisePosts/Post")

    object EntrepriseNewPost : EntreprisePostsScreen("EntrepriseInterface/EntreprisePosts/NewPost")
    object EntrepriseSeeCV : EntreprisePostsScreen("EntrepriseInterface/EntreprisePosts/SeeCV")
    object EntrepriseSeeProfile :
        EntreprisePostsScreen("EntrepriseInterface/EntreprisePosts/SeeProfile")
}

sealed class EntrepriseProfileScreen(val route: String) {
    object EntrepriseProfileMain :
        EntrepriseProfileScreen("EntrepriseInterface/EntrepriseProfile/Main")
}


@Composable
fun NavGraphMain(
    loginViewModel: LoginViewModel? = null,
    standardAccountSignUpViewModel: StandardAccountSignUpViewModel,
    independantAccountSignUpViewModel: IndependantAccountSignUpViewModel,
    entrepriseAccountSignUpViewModel: EntrepriseAccountSignUpViewModel,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Interface.HomeInterface.route
    ) {

        homeInterfaceGraph(navController, loginViewModel)

        authInterfaceGraph(
            navController,
            loginViewModel,
            standardAccountSignUpViewModel,
            independantAccountSignUpViewModel,
            entrepriseAccountSignUpViewModel
        )

        entrepriseInterfaceGraph()
    }
}


fun NavGraphBuilder.authInterfaceGraph(
    navController: NavHostController,
    loginViewModel: LoginViewModel?,
    standardAccountSignUpViewModel: StandardAccountSignUpViewModel?,
    independantAccountSignUpViewModel: IndependantAccountSignUpViewModel?,
    entrepriseAccountSignUpViewModel: EntrepriseAccountSignUpViewModel?,
) {
    navigation(
        route = Interface.AuthInterface.route,
        startDestination = AuthInterfaceScreen.LoginScreen.route
    ) {
        composable(route = AuthInterfaceScreen.LoginScreen.route) {
            LoginScreen(
                navToEntrepriseInterface = {
                    navController.navigate(Interface.EntrepriseInterface.route) {
                        launchSingleTop = true
                        popUpTo(route = AuthInterfaceScreen.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                },
                loginViewModel = loginViewModel
            ) {
                navController.navigate(AuthInterfaceScreen.SignUpSelectScreen.route) {
                    launchSingleTop = true
                    popUpTo(AuthInterfaceScreen.LoginScreen.route) {
                        inclusive = true
                    }
                }
            }
        }
    }

    composable(route = AuthInterfaceScreen.SignUpSelectScreen.route){
        SelectSignUpAccount(
            onNavToEntrepriseSignUpAccount = {
                navController.navigate(AuthInterfaceScreen.EntrepriseSignUpScreen.route) {
                    launchSingleTop = true
                    popUpTo(route = AuthInterfaceScreen.SignUpSelectScreen.route) {
                        inclusive = true
                    }
                }
            }
        )
    }


    composable(route = AuthInterfaceScreen.StandardSignUpScreen.route) {
        StandardSignUpScreen(
            navToStandardInterface = {
                navController.navigate(Interface.StandardInterface.route) {
                    launchSingleTop = true
                    popUpTo(AuthInterfaceScreen.StandardSignUpScreen.route) {
                        inclusive = true
                    }
                }
            },
            viewModel = standardAccountSignUpViewModel
        ) {
            navController.navigate(AuthInterfaceScreen.LoginScreen.route)
        }
    }


    composable(route = AuthInterfaceScreen.IndependantSignUpScreen.route) {
        IndependantAccountSignUpSceen(
            navToIndependantInterface = {
                navController.navigate(Interface.IndependantInterface.route) {
                    launchSingleTop = true
                    popUpTo(AuthInterfaceScreen.IndependantSignUpScreen.route) {
                        inclusive = true
                    }
                }
            },
            viewModel = independantAccountSignUpViewModel
        ) {
            navController.navigate(AuthInterfaceScreen.LoginScreen.route)
        }
    }


    composable(route = AuthInterfaceScreen.EntrepriseSignUpScreen.route) {
        EntrepriseAccountSignUpScreen(
            navToEntrepriseInterface = {
                navController.navigate(Interface.EntrepriseInterface.route) {
                    popUpTo(AuthInterfaceScreen.EntrepriseSignUpScreen.route) {
                        inclusive = true
                    }
                }
            },
            viewModel = entrepriseAccountSignUpViewModel
        ) {
            navController.navigate(AuthInterfaceScreen.LoginScreen.route)
        }
    }


}

fun NavGraphBuilder.entrepriseInterfaceGraph() {
    navigation(
        route = Interface.EntrepriseInterface.route,
        startDestination = EntrepriseInterfaceScreen.EntrepriseMain.route
    ) {
        composable(EntrepriseInterfaceScreen.EntrepriseMain.route) {
            EntrepriseMainPage()
        }

    }
}

private fun NavGraphBuilder.homeInterfaceGraph(
    navController: NavHostController,
    loginViewModel: LoginViewModel?,
) {
    navigation(
        route = Interface.HomeInterface.route,
        startDestination = HomeInterfaceScreen.Splash.route
    ) {
        addSplashScreen(navController = navController, loginViewModel)

        composable(HomeInterfaceScreen.Control.route) {
            AuthControl(
                onNavToStartPage = {
                    navController.navigate(HomeInterfaceScreen.GetStarted.route) {
                        launchSingleTop = true
                    }
                },
                loginViewModel = LoginViewModel(),
                onNavToMainPage = {
                    navController.navigate(Screen.EntrepriseMain.route) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(HomeInterfaceScreen.GetStarted.route) {
            GetStartedScreen(onNavToMainPage = { /*TODO*/ }) {

            }
        }
    }
}

private fun NavGraphBuilder.addSplashScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel?,
) {
    composable(HomeInterfaceScreen.Splash.route) {
        SplashScreen(
            navToEntrepriseInterface = {
                navController.navigate(AuthInterfaceScreen.EntrepriseSignUpScreen.route) {
                    popUpTo(HomeInterfaceScreen.Splash.route) { inclusive = true }
                }
            },
            navToLogin = {
                navController.navigate(AuthInterfaceScreen.LoginScreen.route){
                    popUpTo(HomeInterfaceScreen.Splash.route) { inclusive = true }
                }
            },
            loginViewModel = loginViewModel
        )
    }
}



