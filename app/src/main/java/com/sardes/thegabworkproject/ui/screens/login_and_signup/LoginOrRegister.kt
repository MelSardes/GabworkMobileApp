package com.sardes.thegabworkproject.ui.screens.login_and_signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sardes.thegabworkproject.ui.screens.login.LoginScreen
import com.sardes.thegabworkproject.ui.theme.BlueFlag
import com.sardes.thegabworkproject.ui.theme.GrayPic
import com.sardes.thegabworkproject.ui.theme.YellowFlag

@Composable
fun LoginOrSignUp() {
    var tabIndex by remember { mutableStateOf(0) } // 1.
    val tabTitles = listOf("Connexion", "Inscription")
    Column { // 2.
        TabRow(selectedTabIndex = tabIndex) { // 3.
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = tabIndex == index, // 4.
                    modifier = Modifier.background(GrayPic),
                    onClick = { tabIndex = index },
                    text = { Text(text = title) },
                    selectedContentColor = YellowFlag,
                    unselectedContentColor = BlueFlag
                )
            }
        }
        when (tabIndex) { // 6.
            0 -> LoginScreen(onNavToHomePage = {}, onNavToSelectSignUpPage = {})
            1 -> SelectSignUpAccount({},{}, {})
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Login_Or_Register_Preview(){
    LoginOrSignUp()
}
