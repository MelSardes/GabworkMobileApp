package com.sardes.thegabworkproject.ui.theme

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = BlueFlag,
    primaryVariant = BlueVariant,
    background = BlueBackground,
    surface = Color.White,
    onPrimary = Color.White,
    onBackground = Color.White,
    onSurface = BlueVariant,
    secondary = Color.White,
    onSecondary = YellowFlag
)
@Composable
fun TheGabworkProjectTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}