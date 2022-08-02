package com.sardes.thegabworkproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sardes.thegabworkproject.ui.theme.TheGabworkProjectTheme


class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheGabworkProjectTheme{
                // TODO
            }
        }
    }
}



