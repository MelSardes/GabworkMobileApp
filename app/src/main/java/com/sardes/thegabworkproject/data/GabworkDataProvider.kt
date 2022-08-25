package com.sardes.thegabworkproject.data

import androidx.compose.ui.graphics.Color
import com.sardes.thegabworkproject.ui.theme.graySurface

object GabworkDataProvider {
    fun gabworkSurfaceGradient(isDark: Boolean) =
        if (isDark) listOf(graySurface, Color.Black) else listOf(Color.White, Color.LightGray)
}