package com.sardes.thegabworkproject.test

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.ui.theme.GWTypography


@Preview(showBackground = true)
@Composable
fun Test() {

    Box(
        modifier = Modifier
            .width(350.dp)
            .height(150.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.cag_ink_drawing),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier.fillMaxSize().background(Color.White.copy(alpha = 0.9f)),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Mel SARDES", style = GWTypography.h1)
            Text("Mel SARDES", style = GWTypography.h3)
            Text("Mel SARDES", style = GWTypography.h6)
            Text("Mel SARDES", style = GWTypography.subtitle2)
        }

    }
}



