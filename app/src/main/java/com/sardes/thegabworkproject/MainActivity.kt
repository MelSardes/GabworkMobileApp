package com.sardes.thegabworkproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sardes.thegabworkproject.navigation.SetupNavGraph
import com.sardes.thegabworkproject.ui.screens.login.LoginViewModel
import com.sardes.thegabworkproject.ui.theme.TheGabworkProjectTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val loginViewModel = viewModel(modelClass = LoginViewModel::class.java)

            TheGabworkProjectTheme{
                Verification(
                    loginViewModel = loginViewModel,
                    onNavToStartPage = {},
                    onNavToMainPage = {}
                )

                SetupNavGraph(loginViewModel = loginViewModel)
            }
        }
    }
}

@Composable
fun Verification(
    loginViewModel: LoginViewModel? = null,
    onNavToStartPage: () -> Unit,
    onNavToMainPage: () -> Unit
){
    LaunchedEffect(key1 = loginViewModel?.hasUser){
        if (loginViewModel?.hasUser != true){
            onNavToStartPage.invoke()
        }else
            onNavToMainPage.invoke()
    }
}
