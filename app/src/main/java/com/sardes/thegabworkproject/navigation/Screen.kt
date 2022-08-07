package com.sardes.thegabworkproject.navigation

sealed class Screen(val route: String){
        object Start: Screen(route = "Start_Screen")
        object LoginOrRegister: Screen(route = "Login_or_register_screen")
        object StandardLogin: Screen(route = "Standard_login_screen")
        object StandardSignUp: Screen(route = "Standard_signup_screen")
        object Home: Screen(route = "home_screen")
}
