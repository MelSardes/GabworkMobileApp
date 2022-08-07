package com.sardes.thegabworkproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sardes.thegabworkproject.GetStartedScreen
import com.sardes.thegabworkproject.StandardLogin
import com.sardes.thegabworkproject.view.home.Home
import com.sardes.thegabworkproject.view.login.LoginViewModel
import com.sardes.thegabworkproject.view.login_and_register.login_or_register_account_select_page.LoginOrRegister
import com.sardes.thegabworkproject.view.register.StandardSignUp

@Composable
fun SetupNavGraph(
    navController: NavHostController = rememberNavController(),
    loginViewModel: LoginViewModel
){
    NavHost(
        navController = navController,
        startDestination = Screen.Start.route
    ){

        composable(route = Screen.Start.route){
            GetStartedScreen(navController = navController)
        }

        composable(route = Screen.LoginOrRegister.route){
            LoginOrRegister(navController = navController)
        }

        composable(route = Screen.StandardLogin.route){
            StandardLogin(
                onNavToHomePage = {
                    navController.navigate(Screen.Home.route){
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
                    navController.navigate(Screen.Home.route){
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

        composable(route = Screen.Home.route){
            Home()
        }
    }
}
