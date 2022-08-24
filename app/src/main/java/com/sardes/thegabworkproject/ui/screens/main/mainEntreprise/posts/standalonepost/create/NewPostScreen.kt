package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.standalonepost.create

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun NewPostScreen(
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        Text(text = "NewPostScreen")
    }
}

@Preview(name = "NewPostScreen")
@Composable
private fun PreviewNewPostScreen() {
    NewPostScreen()
}