package com.sardes.thegabworkproject.ui.screens.control

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.sardes.thegabworkproject.ui.screens.login.LoginViewModel

@Composable
fun AuthControl(
    loginViewModel: LoginViewModel? = null,
    onNavToStartPage: () -> Unit,
    onNavToMainPage: () -> Unit
) {

    LaunchedEffect(key1 = loginViewModel?.hasUser){
        if (loginViewModel?.hasUser != true){
            onNavToStartPage()
        }else
            onNavToMainPage()
    }
}

@Composable
fun AccountControl() {
    TODO("CREATE THE CONTROL FOR WHICH ACCOUNT A USER IS CONNECTED TO")
}
