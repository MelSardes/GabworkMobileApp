package com.sardes.thegabworkproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sardes.thegabworkproject.navigation.NavGraphMain
import com.sardes.thegabworkproject.ui.screens.login.LoginViewModel
import com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.EntrepriseAccountSignUpViewModel
import com.sardes.thegabworkproject.ui.screens.signup.standardsignup.StandardSignUpViewModel
import com.sardes.thegabworkproject.ui.theme.TheGabworkProjectTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val loginViewModel = viewModel(modelClass = LoginViewModel::class.java)

            val entrepriseAccountSignUpViewModel =
                viewModel(modelClass = EntrepriseAccountSignUpViewModel::class.java)

            val standardSignUpViewModel =
                viewModel(modelClass = StandardSignUpViewModel::class.java)



            TheGabworkProjectTheme {
                NavGraphMain(
                    loginViewModel,
                    entrepriseAccountSignUpViewModel,
                    standardSignUpViewModel,
                )

            }
        }
    }
}
