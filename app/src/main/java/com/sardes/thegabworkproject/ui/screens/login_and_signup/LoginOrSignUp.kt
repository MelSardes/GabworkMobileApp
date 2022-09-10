package com.sardes.thegabworkproject.ui.screens.login_and_signup

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoginOrSignUp() {
/*    var tabIndex by remember { mutableStateOf(0) } // 1.
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
            0 -> LoginScreen(navToEntrepriseInterface = {}, onNavToSelectSignUpPage = {}, loginViewModel = )
            1 -> SelectSignUpAccount({},{}, {})
        }
    }*/
}

@Preview(showSystemUi = true)
@Composable
fun Login_Or_Register_Preview(){
    LoginOrSignUp()
}
