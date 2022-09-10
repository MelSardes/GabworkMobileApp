package com.sardes.thegabworkproject.ui.screens.main.mainSeeker.message

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun MessagesSeekerScreen(homeSeekerViewModel: MessagesSeekerViewModel? = null) {
    Box() {
        Text(text = "MessagesScreen")
    }
}

@Preview(name = "MessagesScreen")
@Composable
private fun PreviewMessagesScreen() {
    MessagesSeekerScreen(null)
}