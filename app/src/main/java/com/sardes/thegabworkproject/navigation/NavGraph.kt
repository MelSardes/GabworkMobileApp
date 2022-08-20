package com.sardes.thegabworkproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.sardes.thegabworkproject.ui.screens.GetStartedScreen
import com.sardes.thegabworkproject.Verification
import com.sardes.thegabworkproject.ui.screens.Main
import com.sardes.thegabworkproject.ui.screens.login.LoginScreen
import com.sardes.thegabworkproject.ui.screens.login.LoginViewModel
import com.sardes.thegabworkproject.ui.screens.login_and_signup.LoginOrSignUp
import com.sardes.thegabworkproject.ui.screens.login_and_signup.SelectSignUpAccount
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.home.HomeEntrepriseScreen
import com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.EntrepriseAccountSignUpScreen
import com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.EntrepriseAccountSignUpViewModel
import com.sardes.thegabworkproject.ui.screens.signup.independantsignup.IndependantAccountSignUpSceen
import com.sardes.thegabworkproject.ui.screens.signup.independantsignup.IndependantAccountSignUpViewModel
import com.sardes.thegabworkproject.ui.screens.signup.standardsignup.StandardAccountSignUpViewModel
import com.sardes.thegabworkproject.ui.screens.signup.standardsignup.StandardSignUpScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController = rememberNavController(),
    loginViewModel: LoginViewModel,
){
    NavHost(
        navController = navController,
        startDestination = Screen.Verification.route
    ){

/*
        homeGraph(
            navController = navController,
        )
*/
        composable(Screen.Main.route){
            Main()
        }

        composable(Screen.Verification.route){
            Verification(
                onNavToStartPage = {
                    navController.navigate(Screen.Start.route){
                        launchSingleTop = true
                    }
                },
                onNavToMainPage = {
                    navController.navigate(Screen.Main.route){
                        launchSingleTop = true
                    }
                }
            )
        }

        authGraph(navController, loginViewModel)

        composable(route = Screen.Start.route){
            GetStartedScreen(
                onNavToMainPage = {
                    navController.navigate(Screen.Main.route) {
                        launchSingleTop = true
                        popUpTo(route = Screen.Start.route) {
                            inclusive = true
                        }
                    }
                },
                loginViewModel = loginViewModel
            ){
                navController.navigate(Screen.Login.route){
                    launchSingleTop = true
                    popUpTo(Screen.Start.route){
                        inclusive = true
                    }
                }
            }
        }

        composable(Screen.LoginOrSignUp.route){
            LoginOrSignUp()
        }

        composable(route = Screen.SelectSignUpAccount.route){
            SelectSignUpAccount(
                onNavToEntrepriseSignUpAccount = {
                    navController.navigate(Screen.EntrepriseSignUp.route)
                },
                onNavToIndependantSignUpAccount = {
                    navController.navigate(Screen.IndependantSignUp.route)
                },
                onNavToStandardSignUpAccount = {
                    navController.navigate(Screen.StandardSignUp.route)
                }
            )
        }
    }
}


fun NavGraphBuilder.authGraph(
    navController: NavHostController,
    loginViewModel: LoginViewModel
){
    navigation(
        startDestination = Screen.Login.route,
        route = NestedRoutes.Login.name
    ){

        composable(Screen.MainEntreprise.route){
            HomeEntrepriseScreen()
        }

        composable(route = Screen.Login.route){
            LoginScreen(
                onNavToMainPage = {
                    navController.navigate(Screen.MainEntreprise.route){
                        launchSingleTop = true
                        popUpTo(route = Screen.Login.route){
                            inclusive = true
                        }
                    }
                },
                loginViewModel = loginViewModel
            ) {
                navController.navigate(Screen.SelectSignUpAccount.route){
                    launchSingleTop = true
                    popUpTo(Screen.Login.route){
                        inclusive = true
                    }
                }
            }
        }

        composable(route = Screen.StandardSignUp.route){
            StandardSignUpScreen(
                onNavToMainPage = {
                    navController.navigate(Screen.Main.route){
                        launchSingleTop = true
                        popUpTo(Screen.StandardSignUp.route){
                            inclusive = true
                        }
                    }
                },
                viewModel = StandardAccountSignUpViewModel()
            ){
                navController.navigate(Screen.Login.route)
            }
        }

        composable(route = Screen.IndependantSignUp.route){
            IndependantAccountSignUpSceen(
                onNavToMainPage = {
                    navController.navigate(Screen.Main.route){
                        launchSingleTop = true
                        popUpTo(Screen.IndependantSignUp.route){
                            inclusive = true
                        }
                    }
                },
                viewModel = IndependantAccountSignUpViewModel()
            ){
                navController.navigate(Screen.Login.route)
            }
        }

        composable(route = Screen.EntrepriseSignUp.route){
            EntrepriseAccountSignUpScreen(
                onNavToMainPage = {
                    navController.navigate(Screen.Main.route){
                        popUpTo(Screen.Login.route){
                            inclusive = true
                        }
                    }
                },
                viewModel = EntrepriseAccountSignUpViewModel()
            ){
                navController.navigate(Screen.Login.route)
            }
        }

    }
}


/*fun NavGraphBuilder.homeGraph(
    navController: NavHostController,
){
    navigation(
        startDestination = Screen.Home.route,
        route = Screen.Home.route
    ){
        composable(Screen.Home.route){
            Home(
                onNavToLoginPage = {
                    navController.navigate(Screen.Login.route){
                        popUpTo(Screen.Home.route){inclusive = false}
                    }
                }
            )
        }


    }

}*/

/*
fun NavGraphBuilder.homeGraph(
    navController: NavHostController,
    skillViewModel: SkillViewModel,
    homeSkillViewModel: HomeSkillViewModel
){
    navigation(
        startDestination = Screen.Home.route,
        route = NestedRoutes.Main.name
    ){
        composable(Screen.Home.route){
            HomeSkill(homeSkillViewModel = homeSkillViewModel,
                onSkillClick = { skillId ->
                    navController.navigate(Screen.SkillEdit.route + "?id=$skillId") {
                        launchSingleTop = true
                    }
                },
                navToSkillPage = {
                    navController.navigate(
                        Screen.SkillEdit.route
                    )
                }
            ) {
                navController.navigate(NestedRoutes.Login.name){
                    launchSingleTop = true
                    popUpTo(0){
                        inclusive = true
                    }
                }
            }
        }

        composable(
            route  = Screen.SkillEdit.route + "?id={id}",
            arguments = listOf(navArgument("id"){
                type = NavType.StringType
                defaultValue = ""
            })
        ){entry ->
            SkillScreen(
                skillViewModel = SkillViewModel(repository = SkillsStorageRepository()),
                skillId = entry.arguments?.getString("id") as String
            ) {
                navController.navigateUp()
            }

        }
    }

}*/
