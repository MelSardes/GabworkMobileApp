package com.sardes.thegabworkproject.ui.screens.main.mainSeeker.saves

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun SavesSeekerScreen(savesSeekerViewModel: SavesSeekerViewModel?) {
    Box() {
        Text(text = "SavesScreen")
    }
}

@Preview(name = "SavesScreen")
@Composable
private fun PreviewSavesScreen() {
    SavesSeekerScreen(null)
}