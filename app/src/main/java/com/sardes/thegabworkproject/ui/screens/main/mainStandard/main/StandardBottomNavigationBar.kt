package com.sardes.thegabworkproject.ui.screens.main.mainStandard.main

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sardes.thegabworkproject.navigation.StandardInterfaceScreen
import com.sardes.thegabworkproject.ui.theme.GWpalette
import com.sardes.thegabworkproject.ui.theme.GWpalette.Gunmetal

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun StandardBottomNavigationBar(navController: NavController, bottomBarState: MutableState<Boolean>) {

    val items = listOf(
        StandardInterfaceScreen.StandardHome,
        StandardInterfaceScreen.StandardSaves,
        StandardInterfaceScreen.StandardSearch,
        StandardInterfaceScreen.StandardMessages,
        StandardInterfaceScreen.StandardProfile,
    )


    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            BottomNavigation(
                backgroundColor = Gunmetal,
                contentColor = White,
                elevation = 0.dp
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route



                items.forEach { item ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painterResource(id = item.icon),
                                contentDescription = null,
                            )
                        },
//                label = { Text(text = item.title) },
                        selectedContentColor = GWpalette.ImperialRed,
                        unselectedContentColor = LightGray,
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
    )
}
/*

@Preview(name = "SeekerBottomNavigationBar")
@Composable
private fun PreviewSeekerBottomNavigationBar() {
    StandardBottomNavigationBar(rememberNavController(), true.)
}*/
