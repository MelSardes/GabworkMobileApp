package com.sardes.thegabworkproject.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route:String,
    val title: String,
    val icon: ImageVector
){
    object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Rounded.Home
    )
    object Posts: BottomBarScreen(
        route = "post",
        title = "Post",
        icon = Icons.Rounded.FormatListBulleted
    )
    object Search: BottomBarScreen(
        route = "search",
        title = "Recherche",
        icon = Icons.Rounded.Search
    )
    object Message: BottomBarScreen(
        route = "message",
        title = "Messages",
        icon = Icons.Rounded.Message
    )
    object Profile: BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Rounded.Person
    )
}
