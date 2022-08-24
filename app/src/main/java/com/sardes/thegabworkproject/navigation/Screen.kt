package com.sardes.thegabworkproject.navigation

import com.sardes.thegabworkproject.R

sealed class Screen(val route: String){
        object Start: Screen(route = "Start_Screen")
        object LoginOrSignUp: Screen(route = "Login_or_signup_screen")
        object SelectSignUpAccount: Screen(route = "select_signup_screen")
        object Login: Screen(route = "login_screen")
        object Verification: Screen(route = "verification_process")
        object StandardSignUp: Screen(route = "Standard_signup_screen")
        object EntrepriseSignUp: Screen(route = "Entreprise_signup_screen")
        object IndependantSignUp: Screen(route = "Independant_signup_screen")
        object EntrepriseMain: Screen(route = "main_screen")
        object MainEntreprise: Screen(route = "main_entreprise_screen")
        object SkillEdit: Screen(route = "skill_edit")
        object PostsEntrepriseScreen: Screen(route = "PostsEntrepriseScreen")
        object PostApplicants: Screen(route = "PostsApplicants")
}

enum class NestedRoutes{
        Main,
        Login
}

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
        object Home : NavigationItem("home", R.drawable.ic_home, "Home")
        object Posts : NavigationItem("posts", R.drawable.ic_post, "Posts")
        object Search : NavigationItem("search", R.drawable.ic_search, "Chercher")
        object Messages : NavigationItem("messagess", R.drawable.ic_message, "Messages")
        object Profile : NavigationItem("profile", R.drawable.ic_person, "Profil")
        object Start : NavigationItem("start", R.drawable.ic_start, "start")
        object NewPost : NavigationItem("newPost", R.drawable.ic_start, "newPost")
}