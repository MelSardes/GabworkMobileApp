package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.applications.PostsEntrepriseScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.home.HomeEntrepriseScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.message.MessagesEntrepriseScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.profile.ProfileEntrepriseScreen
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.search.SearchEntrepriseScreen
import com.sardes.thegabworkproject.ui.theme.TheGabworkProjectTheme
import com.sardes.thegabworkproject.ui.theme.YellowFlag

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Preview(
    device = "spec:parent=pixel_5",
    showBackground = true,
    showSystemUi = true
)
@Composable
fun EntrepriseMainPage(){
    data class TabItem(
        val icon: ImageVector,
        val contentDescription: String
    )

    var tabIndex by remember { mutableStateOf(0) }
    val homeScreens = listOf(
        TabItem(Icons.Rounded.Home, "Home"),
        TabItem(Icons.Rounded.FormatListBulleted, "Applications"),
        TabItem(Icons.Rounded.Search, "Search"),
        TabItem(Icons.Rounded.Message, "Messages"),
        TabItem(Icons.Rounded.Person, "Profile"),
    )

    TheGabworkProjectTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .fillMaxSize()
        ) {

            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(0.9f).align(Alignment.Start)) {
                when (tabIndex) {
                    0 -> HomeEntrepriseScreen()
                    1 -> PostsEntrepriseScreen(
                        onPostClick = {},
                        navToNewPostPage = {},
                        navToPostPage = {}
                    )
                    2 -> SearchEntrepriseScreen()
                    3 -> MessagesEntrepriseScreen()
                    4 -> ProfileEntrepriseScreen()
                }
            }

            Box(modifier = Modifier.fillMaxWidth().align(Alignment.End)) {
                TabRow(
                    selectedTabIndex = tabIndex,
                    backgroundColor = Color(0xFF2A3855),
                    contentColor = YellowFlag,
                    modifier = Modifier
                ) {
                    homeScreens.forEachIndexed { i, item ->
                        Tab(
                            selected = tabIndex == i,
                            onClick = { tabIndex = i },
                            modifier = Modifier.heightIn(48.dp)
                        ) {
                            Icon(item.icon, contentDescription = item.contentDescription,
                                tint = if (i == tabIndex) YellowFlag
                                else Color.White.copy(alpha = 0.5f)
                            )
                        }
                    }
                }
            }

        }
    }
}