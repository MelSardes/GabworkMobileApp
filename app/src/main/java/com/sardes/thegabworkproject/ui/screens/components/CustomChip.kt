package com.sardes.thegabworkproject.ui.screens.components

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.sardes.thegabworkproject.ui.theme.GWpalette.CoolGrey

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun CustomChip(
    modifier: Modifier = Modifier,

) {
    var state by remember { mutableStateOf(false)}

    Chip(
        onClick = { state = !state },
        border = BorderStroke(
            ChipDefaults.OutlinedBorderSize,
            CoolGrey
        ),
        colors = ChipDefaults.chipColors(
            backgroundColor = when(state){
                false -> Color.White
                true -> Color.Red
            } ,
            contentColor = Color.Red
        ),
    ) {
        Text("Mel SARDES")
    }
}

@Preview(name = "CustomChip", showBackground = true)
@Composable
private fun PreviewCustomChip() {
    CustomChip()
}