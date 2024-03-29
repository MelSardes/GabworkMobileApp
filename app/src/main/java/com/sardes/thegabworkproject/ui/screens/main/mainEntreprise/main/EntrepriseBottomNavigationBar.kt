package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.main

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sardes.thegabworkproject.navigation.EntrepriseInterfaceScreen
import com.sardes.thegabworkproject.ui.theme.GWpalette
import com.sardes.thegabworkproject.ui.theme.GWpalette.EauBlue
import com.sardes.thegabworkproject.ui.theme.GWpalette.Gunmetal

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun EntrepriseBottomNavigationBar(navController: NavController, bottomBarState: MutableState<Boolean>) {
    val items = listOf(
        EntrepriseInterfaceScreen.EntrepriseHome,
        EntrepriseInterfaceScreen.EntreprisePosts,
        EntrepriseInterfaceScreen.EntrepriseSearch,
        EntrepriseInterfaceScreen.EntrepriseMessages,
        EntrepriseInterfaceScreen.EntrepriseProfile,
    )

    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            BottomNavigation(
                backgroundColor = Gunmetal,
                contentColor = EauBlue,
                elevation = 0.dp
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { item ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painterResource(id = item.icon),
                                contentDescription = item.title
                            )
                        },
//                label = { Text(text = item.title) },
                        selectedContentColor = GWpalette.ImperialRed,
                        unselectedContentColor = EauBlue,
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
