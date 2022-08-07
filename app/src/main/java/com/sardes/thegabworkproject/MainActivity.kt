package com.sardes.thegabworkproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sardes.thegabworkproject.navigation.SetupNavGraph
import com.sardes.thegabworkproject.ui.theme.TheGabworkProjectTheme
import com.sardes.thegabworkproject.view.login.LoginViewModel


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val loginViewModel = viewModel(modelClass = LoginViewModel::class.java)

            TheGabworkProjectTheme{

                SetupNavGraph(loginViewModel = loginViewModel)

            }
        }
    }
}



