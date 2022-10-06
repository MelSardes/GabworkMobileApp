package com.sardes.thegabworkproject.ui.screens.main.mainStandard.profile

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kiwi.orbit.compose.ui.controls.Scaffold

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun ProfileStandardScreen(profileStandardViewModel: ProfileStandardViewModel?) {
    Scaffold(

        topBar = { },

        content = {
            kiwi.orbit.compose.ui.controls.Text("PROFILE")
        }
    )

}

@Preview(name = "ProfileScreen")
@Composable
private fun PreviewProfileScreen() {
    ProfileStandardScreen(null)
}