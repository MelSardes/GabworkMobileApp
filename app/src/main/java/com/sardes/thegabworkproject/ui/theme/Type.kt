package com.sardes.thegabworkproject.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sardes.thegabworkproject.R

val fonts = FontFamily(
    Font(R.font.yanone_kaffeesatz_regular, weight = FontWeight.Normal),
    Font(R.font.yanone_kaffeesatz_bold, weight = FontWeight.Bold),
    Font(R.font.yanone_kaffeesatz_semi_bold, weight = FontWeight.SemiBold),
    Font(R.font.yanone_kaffeesatz_medium, weight = FontWeight.Medium),
    Font(R.font.yanone_kaffeesatz_light, weight = FontWeight.Light),
    Font(R.font.yanone_kaffeesatz_extra_light, weight = FontWeight.ExtraLight)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Light,
        fontSize = 24.sp
    ),

    body2 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp
    ),


    button = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp
    ),

    h1 = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.SemiBold,
            fontSize = 50.sp
    )

    /*
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )*/
)