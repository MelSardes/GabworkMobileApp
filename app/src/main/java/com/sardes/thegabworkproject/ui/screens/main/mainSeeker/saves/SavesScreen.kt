package com.sardes.thegabworkproject.ui.screens.main.mainSeeker.saves

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun SavesScreen(
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        Text(text = "SavesScreen")
    }
}

@Preview(name = "SavesScreen")
@Composable
private fun PreviewSavesScreen() {
    SavesScreen()
}