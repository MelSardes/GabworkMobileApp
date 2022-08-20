package com.sardes.thegabworkproject.navigation

sealed class Screen(val route: String){
        object Start: Screen(route = "Start_Screen")
        object LoginOrSignUp: Screen(route = "Login_or_signup_screen")
        object SelectSignUpAccount: Screen(route = "select_signup_screen")
        object Login: Screen(route = "login_screen")
        object Verification: Screen(route = "verification_process")
        object StandardSignUp: Screen(route = "Standard_signup_screen")
        object EntrepriseSignUp: Screen(route = "Entreprise_signup_screen")
        object IndependantSignUp: Screen(route = "Independant_signup_screen")
        object Main: Screen(route = "main_screen")
        object MainEntreprise: Screen(route = "main_entreprise_screen")
        object SkillEdit: Screen(route = "skill_edit")
}

enum class NestedRoutes{
        Main,
        Login
}