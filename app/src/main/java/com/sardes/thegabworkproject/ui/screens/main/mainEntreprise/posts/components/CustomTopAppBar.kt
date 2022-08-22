package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun CustomTopAppBar(title: String) {
    TopAppBar(
        title = {Text(text = title, fontWeight = FontWeight.SemiBold)},
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    Icons.Filled.ArrowBackIos,
                    "backIcon",
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFEAF1FF))
                        .padding(start = 5.dp),
                    tint = Color(0xFF6B9EFF)
                )
            }
        },
        backgroundColor = Color.White,
        contentColor = Color.Black,
        elevation = 10.dp
    )

}

@Preview
@Composable
fun PreviewCustomAppBar() {
    CustomTopAppBar(title = "Top Bar")
}
