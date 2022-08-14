package com.sardes.thegabworkproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sardes.thegabworkproject.GetStartedScreen
import com.sardes.thegabworkproject.StandardLogin
import com.sardes.thegabworkproject.repository.StorageRepository
import com.sardes.thegabworkproject.view.login.LoginViewModel
import com.sardes.thegabworkproject.view.login_and_register.login_or_register_account_select_page.LoginOrRegister
import com.sardes.thegabworkproject.view.register.StandardSignUp
import com.sardes.thegabworkproject.view.skill.HomeSkill
import com.sardes.thegabworkproject.view.skill.HomeSkillViewModel
import com.sardes.thegabworkproject.view.skill.SkillScreen
import com.sardes.thegabworkproject.view.skill.SkillViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController = rememberNavController(),
    loginViewModel: LoginViewModel,
    skillViewModel: SkillViewModel,
    homeSkillViewModel: HomeSkillViewModel
){
    NavHost(
        navController = navController,
        startDestination = Screen.Start.route
    ){

        authGraph(navController, loginViewModel)

        homeGraph(
            navController = navController,
            SkillViewModel(repository = StorageRepository()),
            HomeSkillViewModel(repository = StorageRepository())
        )

        composable(route = Screen.Start.route){
            GetStartedScreen(
                onNavToHomePage = {
                    navController.navigate(Screen.Home.route) {
                        launchSingleTop = true
                        popUpTo(route = Screen.StandardLogin.route) {
                            inclusive = true
                        }
                    }
                },
                loginViewModel = loginViewModel
            ){
                navController.navigate(Screen.LoginOrRegister.route){
                    launchSingleTop = true
                    popUpTo(Screen.Start.route){
                        inclusive = true
                    }
                }
            }
        }

        composable(route = Screen.LoginOrRegister.route){
            LoginOrRegister(navController = navController)
        }

/*        composable(route = Screen.Home.route){
                Home()
        }*/
    }
}


fun NavGraphBuilder.authGraph(
    navController: NavHostController,
    loginViewModel: LoginViewModel
){
    navigation(
        startDestination = Screen.StandardLogin.route,
        route = NestedRoutes.Login.name
    ){

        composable(route = Screen.StandardLogin.route){
            StandardLogin(
                onNavToHomePage = {
                    navController.navigate(NestedRoutes.Main.name){
                        launchSingleTop = true
                        popUpTo(route = Screen.StandardLogin.route){
                            inclusive = true
                        }
                    }
                },
                loginViewModel = loginViewModel
            ) {
                navController.navigate(Screen.StandardSignUp.route){
                    launchSingleTop = true
                    popUpTo(Screen.StandardLogin.route){
                        inclusive = true
                    }
                }
            }
        }

        composable(route = Screen.StandardSignUp.route){
            StandardSignUp(
                onNavToHomePage = {
                    navController.navigate(NestedRoutes.Main.name){
                        popUpTo(Screen.StandardLogin.route){
                            inclusive = true
                        }
                    }
                },
                loginViewModel = loginViewModel
            ){
                navController.navigate(Screen.StandardLogin.route)
            }
        }

    }
}


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
                skillViewModel = SkillViewModel(repository = StorageRepository()),
                skillId = entry.arguments?.getString("id") as String
            ) {
                navController.navigateUp()
            }

        }
    }

}