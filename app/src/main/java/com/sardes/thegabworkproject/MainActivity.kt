package com.sardes.thegabworkproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sardes.thegabworkproject.navigation.SetupNavGraph
import com.sardes.thegabworkproject.ui.theme.TheGabworkProjectTheme
import com.sardes.thegabworkproject.view.login.LoginViewModel
import com.sardes.thegabworkproject.view.skill.HomeSkillViewModel
import com.sardes.thegabworkproject.view.skill.SkillViewModel


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



