package com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.ui.theme.GWpalette
import com.sardes.thegabworkproject.ui.theme.TailwindCSSColor
import kiwi.orbit.compose.ui.OrbitTheme

@Composable
fun StepsBarsSignUpEntreprise(stepIndicator: Int) {
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        when (stepIndicator) {
            5 -> {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(5.dp)
                        .height(5.dp)
                        .clip(CircleShape)
                        .background(OrbitTheme.colors.primary.normal)
                )
            }
            else -> {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(5.dp)
                        .height(5.dp)
                        .clip(CircleShape)
                        .background(if (stepIndicator == 1) Color.White else TailwindCSSColor.Green500)
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(5.dp)
                        .height(5.dp)
                        .clip(CircleShape)
                        .background(
                            when (stepIndicator) {
                                1 -> GWpalette.CoolGrey
                                2 -> Color.White
                                else -> TailwindCSSColor.Green500
                            }
                        )
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(5.dp)
                        .height(5.dp)
                        .clip(CircleShape)
                        .background(
                            when (stepIndicator) {
                                1 -> GWpalette.CoolGrey
                                2 -> GWpalette.CoolGrey
                                3 -> Color.White
                                else -> TailwindCSSColor.Green500
                            }
                        )
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(5.dp)
                        .height(5.dp)
                        .clip(CircleShape)
                        .background(
                            when (stepIndicator) {
                                1 -> GWpalette.CoolGrey
                                2 -> GWpalette.CoolGrey
                                3 -> GWpalette.CoolGrey
                                4 -> Color.White
                                else -> TailwindCSSColor.Green500
                            }
                        )
                )
            }


        }
    }
}

@Preview(name = "StepsBarsSignUpEntreprise")
@Composable
private fun PreviewStepsBarsSignUpEntreprise() {
    StepsBarsSignUpEntreprise(3)
}