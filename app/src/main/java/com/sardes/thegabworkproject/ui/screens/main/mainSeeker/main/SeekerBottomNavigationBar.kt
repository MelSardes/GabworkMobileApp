package com.sardes.thegabworkproject.ui.screens.main.mainSeeker.main

import android.annotation.SuppressLint
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sardes.thegabworkproject.navigation.SeekerInterfaceScreen
import com.sardes.thegabworkproject.ui.theme.GWpalette
import com.sardes.thegabworkproject.ui.theme.GWpalette.Gunmetal

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun SeekerBottomNavigationBar(navController: NavController) {

    val items = listOf(
        SeekerInterfaceScreen.SeekerHome,
        SeekerInterfaceScreen.SeekerSaves,
        SeekerInterfaceScreen.SeekerSearch,
        SeekerInterfaceScreen.SeekerMessages,
        SeekerInterfaceScreen.SeekerProfile,
    )



    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Gunmetal,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(
                    painterResource(id = item.icon),
                    contentDescription = null
                ) },
//                label = { Text(text = item.title) },
                selectedContentColor = GWpalette.ImperialRed,
                unselectedContentColor = Gunmetal,
                alwaysShowLabel = false,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }

    }






}

@Preview(name = "SeekerBottomNavigationBar")
@Composable
private fun PreviewSeekerBottomNavigationBar() {
    SeekerBottomNavigationBar(rememberNavController())
}