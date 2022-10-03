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
import com.sardes.thegabworkproject.ui.screens.login.LoginScreen
import com.sardes.thegabworkproject.ui.screens.login.LoginViewModel
import com.sardes.thegabworkproject.ui.screens.login_and_signup.SelectSignUpAccount
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.main.EntrepriseMainPage
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.main.StandardMainPage
import com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.EntrepriseAccountSignUpViewModel
import com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.screens.EntrepriseAccountSignUpScreen
import com.sardes.thegabworkproject.ui.screens.signup.standardsignup.StandardSignUpViewModel
import com.sardes.thegabworkproject.ui.screens.signup.standardsignup.screens.AfterSignUpMessage
import com.sardes.thegabworkproject.ui.screens.signup.standardsignup.screens.StandardSignUpScreen
import com.sardes.thegabworkproject.ui.screens.signup.standardsignup.screens.StandardSignUpStart
import com.sardes.thegabworkproject.ui.screens.splash.SplashScreen

sealed class Interface(val route: String) {

    object HomeInterface : Interface("HomeInterface")
    object AuthInterface : Interface("AuthInterface")

    object EntrepriseInterface : Interface("EntrepriseInterface")
    object StandardInterface : Interface("standardInterface")
}

sealed class AuthInterfaceScreen(val route: String) {
    object LoginScreen : AuthInterfaceScreen("LoginScreen")
    object SignUpSelectScreen : AuthInterfaceScreen("SignUpSelectScreen")

    object EntrepriseStartSignUpScreen : AuthInterfaceScreen("EntrepriseStartSignUpScreen")
    object EntrepriseSignUpScreen : AuthInterfaceScreen("EntrepriseSignUpScreen")

    object StandardStartSignUpScreen : AuthInterfaceScreen("standardStartSignUpScreen")
    object StandardSignUpScreen : AuthInterfaceScreen("standardSignUpScreen")
    object StandardScreenAfterSignUp : AuthInterfaceScreen("standardScreenAfterSignUp")
}

sealed class HomeInterfaceScreen(val route: String) {
    object Splash : HomeInterfaceScreen("Splash")
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

sealed class StandardInterfaceScreen(val route: String, val icon: Int) {
    object StandardMain : StandardInterfaceScreen("standardInterface/Main", 0)

    object StandardHome :
        StandardInterfaceScreen("standardInterface/standardHome", R.drawable.ic_home)

    object StandardSaves : StandardInterfaceScreen(
        "standardInterface/standardBookmarks",
        kiwi.orbit.compose.ui.R.drawable.ic_orbit_bookmark
    )

    object StandardSearch :
        StandardInterfaceScreen("standardInterface/standardSearch", R.drawable.ic_search)

    object StandardMessages :
        StandardInterfaceScreen("standardInterface/standardMessages", R.drawable.ic_message)

    object StandardProfile :
        StandardInterfaceScreen("standardInterface/standardProfile", R.drawable.ic_person)
}


sealed class StandardPostScreen(val route: String) {
    object DetailsPostScreen : StandardPostScreen("StandardApplicationPost")
}

sealed class StandardMessagesScreen(val route: String) {
    object StandardConversationScreen :
        StandardMessagesScreen("standardInterface/standardMessages/Conversation")
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
    object EntrepriseConversationScreen :
        EntrepriseMessagesScreen("EntrepriseInterface/EntrepriseMessages/Conversation")
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
    entrepriseAccountSignUpViewModel: EntrepriseAccountSignUpViewModel,
    standardSignUpViewModel: StandardSignUpViewModel?
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
            entrepriseAccountSignUpViewModel,
            standardSignUpViewModel
        )

        entrepriseInterfaceGraph()

        standardInterfaceGraph()
    }
}


fun NavGraphBuilder.authInterfaceGraph(
    navController: NavHostController,
    loginViewModel: LoginViewModel?,
    entrepriseAccountSignUpViewModel: EntrepriseAccountSignUpViewModel?,
    standardSignUpViewModel: StandardSignUpViewModel?
) {
    navigation(
        route = Interface.AuthInterface.route,
        startDestination = AuthInterfaceScreen.LoginScreen.route
    ) {
        composable(route = AuthInterfaceScreen.LoginScreen.route) {
            LoginScreen(
                loginViewModel = loginViewModel,

                navToEntrepriseInterface = {
                    navController.navigate(Interface.EntrepriseInterface.route) {
                        launchSingleTop = true
                        popUpTo(route = AuthInterfaceScreen.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                },

                onNavToSelectSignUpPage = {
                    navController.navigate(AuthInterfaceScreen.SignUpSelectScreen.route) {
                        launchSingleTop = true
                        popUpTo(AuthInterfaceScreen.LoginScreen.route) {
                            inclusive = true
                        }
                    }

                },

                navToStandardInterface = {
                    navController.navigate(Interface.StandardInterface.route) {
                        launchSingleTop = true
                        popUpTo(route = AuthInterfaceScreen.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }

    composable(route = AuthInterfaceScreen.SignUpSelectScreen.route) {
        SelectSignUpAccount(
            onNavToEntrepriseSignUpAccount = {
                navController.navigate(AuthInterfaceScreen.EntrepriseStartSignUpScreen.route) {
                    launchSingleTop = true
                }
            },

            onNavToStandardSignUpAccount = {
                navController.navigate(AuthInterfaceScreen.StandardStartSignUpScreen.route) {
                    launchSingleTop = true
                }
            }

        )
    }



    composable(route = AuthInterfaceScreen.StandardStartSignUpScreen.route) {
        StandardSignUpStart(
            onNavToLoginPage = {
                navController.navigate(AuthInterfaceScreen.LoginScreen.route) {
                    launchSingleTop = true
                }
            },

            startSigningUp = {
                navController.navigate(AuthInterfaceScreen.StandardSignUpScreen.route) {
                    launchSingleTop = true
                }
            }
        )
    }

    composable(route = AuthInterfaceScreen.EntrepriseStartSignUpScreen.route) {
        StandardSignUpStart(
            onNavToLoginPage = {
                navController.navigate(AuthInterfaceScreen.LoginScreen.route) {
                    launchSingleTop = true
                }
            },

            startSigningUp = {
                navController.navigate(AuthInterfaceScreen.EntrepriseSignUpScreen.route) {
                    launchSingleTop = true
                }
            }
        )
    }






    composable(route = AuthInterfaceScreen.StandardSignUpScreen.route) {
        StandardSignUpScreen(
            viewModel = standardSignUpViewModel,

/*
            navToStandardInterface = {
                navController.navigate(Interface.StandardInterface.route){
                    popUpTo(AuthInterfaceScreen.StandardSignUpScreen.route){
                        inclusive = true
                    }
                }
            }
*/
            afterSignUp = {
                navController.navigate(AuthInterfaceScreen.StandardScreenAfterSignUp.route) {
                    popUpTo(AuthInterfaceScreen.StandardSignUpScreen.route)
                }
            }
        )
    }



    composable(route = AuthInterfaceScreen.StandardScreenAfterSignUp.route) {
        AfterSignUpMessage(
            navToHome = {
                navController.navigate(Interface.StandardInterface.route) {
                    popUpTo(AuthInterfaceScreen.StandardSignUpScreen.route) {
                        inclusive = true
                    }
                }
            },
            goCompleteProfile = {navController.navigate("CompleteProfile")}
        )
    }

    composable(route = AuthInterfaceScreen.EntrepriseSignUpScreen.route) {
        EntrepriseAccountSignUpScreen(
            viewModel = entrepriseAccountSignUpViewModel,

            navToEntrepriseInterface = {
                navController.navigate(Interface.EntrepriseInterface.route) {
                    popUpTo(AuthInterfaceScreen.EntrepriseSignUpScreen.route) {
                        inclusive = true
                    }
                }
            }
        )
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

fun NavGraphBuilder.standardInterfaceGraph() {
    navigation(
        route = Interface.StandardInterface.route,
        startDestination = StandardInterfaceScreen.StandardMain.route
    ) {
        composable(StandardInterfaceScreen.StandardMain.route) {
            StandardMainPage()
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
                navController.navigate(AuthInterfaceScreen.LoginScreen.route) {
                    popUpTo(HomeInterfaceScreen.Splash.route) { inclusive = true }
                }
            },
            loginViewModel = loginViewModel,
            navToStandardInterface = {
                navController.navigate(AuthInterfaceScreen.StandardSignUpScreen.route) {
                    popUpTo(HomeInterfaceScreen.Splash.route) { inclusive = true }
                }
            }
        )
    }
}



