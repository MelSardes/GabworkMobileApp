package com.sardes.thegabworkproject.ui.composition

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun CircularButton(
    @DrawableRes iconResouce: Int,
    color: Color = Color.Gray,
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    onClick: () -> Unit = {},
) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White, contentColor = color),
        elevation = elevation,
        modifier = Modifier
            .width(38.dp)
            .height(38.dp)
    ) {
        Icon(painterResource(id = iconResouce), null)
    }
}
