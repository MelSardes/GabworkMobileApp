package com.sardes.thegabworkproject.ui.screens.signup.seekersignup.components

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
fun StepsBarsSeekerSignUp(stepIndicator: Int) {
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        when (stepIndicator) {
            10 -> {
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
//                STEP ONE
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(5.dp)
                        .height(5.dp)
                        .clip(CircleShape)
                        .background(if (stepIndicator == 1) Color.White else TailwindCSSColor.Green500)
                )

//                STEP TWO
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

//                STEP THREE
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

//                STEP FOUR
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

//                STEP FIVE
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
                                4 -> GWpalette.CoolGrey
                                5 -> Color.White
                                else -> TailwindCSSColor.Green500
                            }
                        )
                )

//                STEP SIX
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
                                4 -> GWpalette.CoolGrey
                                5 -> GWpalette.CoolGrey
                                6 -> Color.White
                                else -> TailwindCSSColor.Green500
                            }
                        )
                )

//                STEP SEVEN
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
                                4 -> GWpalette.CoolGrey
                                5 -> GWpalette.CoolGrey
                                6 -> GWpalette.CoolGrey
                                7 -> Color.White
                                else -> TailwindCSSColor.Green500
                            }
                        )
                )

//                STEP HEIGHT
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
                                4 -> GWpalette.CoolGrey
                                5 -> GWpalette.CoolGrey
                                6 -> GWpalette.CoolGrey
                                7 -> GWpalette.CoolGrey
                                8 -> Color.White
                                else -> TailwindCSSColor.Green500
                            }
                        )
                )
//                STEP NINE
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
                                4 -> GWpalette.CoolGrey
                                5 -> GWpalette.CoolGrey
                                6 -> GWpalette.CoolGrey
                                7 -> GWpalette.CoolGrey
                                8 -> GWpalette.CoolGrey
                                9 -> Color.White
                                else -> TailwindCSSColor.Green500
                            }
                        )
                )
            }
        }
    }
}


@Preview(name = "StepsBarsSeekerSignUp")
@Composable
private fun PreviewStepsBarsSeekerSignUp() {
    StepsBarsSeekerSignUp(8)
}