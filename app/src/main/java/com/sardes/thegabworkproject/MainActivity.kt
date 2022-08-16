package com.sardes.thegabworkproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sardes.thegabworkproject.navigation.SetupNavGraph
import com.sardes.thegabworkproject.ui.screens.login.LoginViewModel
import com.sardes.thegabworkproject.ui.screens.skill.HomeSkillViewModel
import com.sardes.thegabworkproject.ui.screens.skill.SkillViewModel
import com.sardes.thegabworkproject.ui.theme.TheGabworkProjectTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val loginViewModel = viewModel(modelClass = LoginViewModel::class.java)
            val homeSkillViewModel = viewModel(modelClass = HomeSkillViewModel::class.java)
            val skillViewModel = viewModel(modelClass = SkillViewModel::class.java)

            TheGabworkProjectTheme{

                SetupNavGraph(
                    loginViewModel = loginViewModel,
                    skillViewModel = skillViewModel,
                    homeSkillViewModel = homeSkillViewModel
                )

            }
        }
    }
}



