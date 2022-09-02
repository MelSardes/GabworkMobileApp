package com.sardes.thegabworkproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.sardes.thegabworkproject.navigation.NavGraphMain
import com.sardes.thegabworkproject.ui.screens.login.LoginViewModel
import com.sardes.thegabworkproject.ui.theme.TheGabworkProjectTheme


class MainActivity : ComponentActivity() {


//    private val loginViewModel by viewModel<LoginViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val loginViewModel = viewModel(modelClass = LoginViewModel::class.java)
            val navController = rememberNavController()
            TheGabworkProjectTheme{
/*                Verification(
                    loginViewModel = loginViewModel,
                    onNavToStartPage = {},
                    onNavToMainPage = {}
                )*/

//                EntrepriseMainPage(navController = navController)
                NavGraphMain(loginViewModel)
/*
                SetupNavGraph(
                    navController = navController,
                    loginViewModel = loginViewModel,
                )
*/
            }
        }
    }
}

/*
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
*/
