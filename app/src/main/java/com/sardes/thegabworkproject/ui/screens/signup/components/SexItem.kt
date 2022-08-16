package com.sardes.thegabworkproject.ui.screens.signup.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.ui.theme.BlueFlag

@Composable
fun SexItem(
    sexItem: String,
    onClick:()->Unit
){
    Surface(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                onClick.invoke()
            },
        shape = RoundedCornerShape(30.dp),
        border = BorderStroke(1.dp, BlueFlag)
    )
    {
        Text(text = sexItem, modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
    }
}
