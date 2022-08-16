package com.sardes.thegabworkproject.navigation

sealed class Screen(val route: String){
        object Start: Screen(route = "Start_Screen")
        object LoginOrSignUp: Screen(route = "Login_or_register_screen")
        object SelectSignUpAccount: Screen(route = "Login_or_register_screen")
        object Login: Screen(route = "login_screen")
        object StandardSignUp: Screen(route = "Standard_signup_screen")
        object EntrepriseSignUp: Screen(route = "Entreprise_signup_screen")
        object IndependantSignUp: Screen(route = "Independant_signup_screen")
        object Home: Screen(route = "home_screen")
        object SkillEdit: Screen(route = "skill_edit")
}

enum class NestedRoutes{
        Main,
        Login
}