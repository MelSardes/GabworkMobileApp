package com.sardes.thegabworkproject.test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette.CoolGrey
import com.sardes.thegabworkproject.ui.theme.GWpalette.MaximumRed
import com.sardes.thegabworkproject.ui.theme.TailwindCSSColor.Green500
import kiwi.orbit.compose.ui.controls.Card
import kiwi.orbit.compose.ui.controls.Surface
import kiwi.orbit.compose.ui.controls.Text


val surfaceColor = MaximumRed

@Preview(showSystemUi = true)
@Composable
fun Test() {


    var stepIndicator by remember { mutableStateOf(1) }

    val focusRequester = remember { FocusRequester() }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = surfaceColor
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxHeight(0.2f)) {
//            HEADER
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                ) {
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            when (stepIndicator) {
                                2 -> "33%"
                                3 -> "66%"
                                4 -> "100%"
                                else -> ""
                            },
                            color = White,
                            style = GWTypography.h5.copy(fontWeight = FontWeight.Bold)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            when (stepIndicator) {
                                1 -> ""
                                else -> "completé"
                            },
                            color = Color.LightGray,
                            style = GWTypography.body2
                        )
                    }
                }
                Text(
                    text =
                    when (stepIndicator) {
                        1 -> "Details du compte"
                        2 -> "Informations sur l'entreprise 1/2"
                        3 -> "Informations sur l'entreprise 2/2"
                        4 -> "Vérification des informations"
                        else -> ""
                    },
                    color = White,
                    style = GWTypography.h6
                )

//                STEPS
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp)
                            .height(5.dp)
                            .clip(CircleShape)
                            .background(if (stepIndicator == 1) White else Green500)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp)
                            .height(5.dp)
                            .clip(CircleShape)
                            .background(
                                when (stepIndicator) {
                                    1 -> CoolGrey
                                    2 -> White
                                    else -> Green500
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
                                    1 -> CoolGrey
                                    2 -> CoolGrey
                                    3 -> White
                                    else -> Green500
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
                                    1 -> CoolGrey
                                    2 -> CoolGrey
                                    3 -> CoolGrey
                                    4 -> White
                                    else -> Green500
                                }
                            )
                    )
                }
            }


            Column(
                modifier = Modifier
                    .fillMaxHeight(1f)
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(
                        Brush.horizontalGradient(
                            colorStops = arrayOf(
                                Pair(0.3f, surfaceColor),
                                Pair(1f, Green500)
                            )
                        )
                    )
            ) {

                Card(
                    modifier = Modifier
                        .weight(0.92f)
                        .fillMaxWidth(),
                    backgroundColor = White,
                    shape = RoundedCornerShape(24.dp)
                ) {

                    //================================================
                    //      CONTENT
                    //================================================

                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.08f)
                ) {
                    Card(
                        backgroundColor = surfaceColor,
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .fillMaxHeight(),
                        onClick = { stepIndicator -= 1 },
                    ) {
                        Text(
                            text = "Précédent",
                            color = White,
                            style = GWTypography.h6,
                            textAlign = TextAlign.Center
                        )
                    }
                    Card(
                        backgroundColor = Green500,
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .fillMaxHeight(),
                        onClick = {

                            stepIndicator += 1

//                            when(stepIndicator){
//                                1 -> stepIndicator = 2
//                                2 -> stepIndicator = 3
//                                3 -> stepIndicator = 4
//                                4 -> stepIndicator = 5
//                            }
                        },
                    ) {
                        Text(
                            text = "Suivant",
                            color = White,
                            style = GWTypography.h6,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

