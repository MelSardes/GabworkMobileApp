package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.applications.post

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun PostDetails(
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        Text(text = "PostDetails")
    }
}

@Preview(name = "PostDetails")
@Composable
private fun PreviewPostDetails() {
    PostDetails()
}