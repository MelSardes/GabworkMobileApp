package com.sardes.thegabworkproject.ui.screens.common

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CommonSignUpScreen(
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        Text(text = "CommonSignUpScreen")
    }
}

@Preview(name = "CommonSignUpScreen")
@Composable
private fun PreviewCommonSignUpScreen() {
    CommonSignUpScreen()
}