package com.sardes.thegabworkproject.ui.screens.main.mainSeeker.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        Text(text = "ProfileScreen")
    }
}

@Preview(name = "ProfileScreen")
@Composable
private fun PreviewProfileScreen() {
    ProfileScreen()
}