package com.sardes.thegabworkproject.ui.screens.main.mainStandard.saves

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun ApplicationsSection(
    modifier: Modifier = Modifier
) {
    Box(modifier.fillMaxSize()) {
        Text(text = "ApplicationsSection")
    }
}

@Preview(name = "ApplicationsSection")
@Composable
private fun PreviewApplicationsSection() {
    ApplicationsSection()
}