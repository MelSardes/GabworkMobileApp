package com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.EntrepriseAccountSignUpViewModel
import com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.screens.components.*
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.TailwindCSSColor.Green500
import com.sardes.thegabworkproject.ui.theme.TailwindCSSColor.Red500
import kiwi.orbit.compose.ui.controls.Card
import kiwi.orbit.compose.ui.controls.Surface
import kiwi.orbit.compose.ui.controls.Text

@SuppressLint("MaterialDesignInsteadOrbitDesign")
//@Preview
@Composable
fun EntrepriseAccountSignUpScreen(
    viewModel: EntrepriseAccountSignUpViewModel?,
    navToEntrepriseInterface: () -> Unit = {},
) {


    val entrepriseUiState = viewModel?.signUpUiStateEntreprise
    val isError = entrepriseUiState?.signUpError != null


    var stepIndicator by remember { mutableStateOf(1) }

    val focusRequester = remember { FocusRequester() }

    if (entrepriseUiState?.isLoading == true) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            color = Red500
        )
    }else {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = Red500
        ) {
            Column(modifier = Modifier.fillMaxSize()) {

                //===============================================
                //                # HEADER
                //===============================================
                HeaderEntrepriseSignUp(stepIndicator)


                //===============================================
                //                # BODY
                //===============================================
                Column(
                    modifier = Modifier
                        .fillMaxHeight(1f)
                        .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                        .background(
                            when (stepIndicator) {
                                5 -> {
                                    Brush.horizontalGradient(
                                        colorStops = arrayOf(
                                            Pair(0.3f, Red500),
                                            Pair(1f, LightGray)
                                        )
                                    )
                                }
                                else -> {
                                    Brush.horizontalGradient(
                                        colorStops = arrayOf(
                                            Pair(0.3f, Red500),
                                            Pair(1f, Green500)
                                        )
                                    )
                                }
                            }
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
                        //     # CONTENT
                        //================================================

                        if (stepIndicator == 1) AccountDetailsEntrepriseSignUp(
                            entrepriseUiState,
                            viewModel,
                            focusRequester,
                        )
                        if (stepIndicator == 2) EntrepriseInformations1(
                            entrepriseUiState,
                            viewModel,
                            focusRequester,
                        )
                        if (stepIndicator == 3) EntrepriseInformations2(
                            entrepriseUiState,
                            viewModel,
                            focusRequester,
                        )
                        if (stepIndicator == 4) LogoSelectionStep(
                            viewModel
                        )
                        if (stepIndicator == 5) {
                            InformationsEntrepriseStep(isError, entrepriseUiState, viewModel)

                        }
                    }


                    //================================================
                    //     # BOTTOM BUTTONS
                    //================================================
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.08f)
                    ) {
                        when (stepIndicator) {
                            1 -> {
                                Card(
                                    backgroundColor = Red500,
                                    modifier = Modifier
                                        .fillMaxWidth(0.5f)
                                        .fillMaxHeight(),
                                ) {
                                    Text(
                                        text = "Précédent",
                                        color = LightGray,
                                        style = GWTypography.h6,
                                        textAlign = TextAlign.Center
                                    )
                                }

                            }
                            else -> {
                                Card(
                                    backgroundColor = Red500,
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

                            }
                        }

                        when (stepIndicator) {
                            5 -> {
                                Card(
                                    backgroundColor = LightGray,
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .fillMaxHeight(),
                                ) {
                                    Text(
                                        text = "Suivant",
                                        color = White,
                                        style = GWTypography.h6,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                            else -> {
                                Card(
                                    backgroundColor = Green500,
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .fillMaxHeight(),
                                    onClick = { stepIndicator += 1 }
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
        }

        LaunchedEffect(key1 = viewModel?.hasUser) {
            if (viewModel?.hasUser == true) {
                navToEntrepriseInterface.invoke()
            }
        }
    }
}