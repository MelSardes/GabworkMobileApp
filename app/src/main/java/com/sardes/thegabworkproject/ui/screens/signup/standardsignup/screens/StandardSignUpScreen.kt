package com.sardes.thegabworkproject.ui.screens.signup.standardsignup.screens

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.ui.screens.signup.standardsignup.StandardSignUpViewModel
import com.sardes.thegabworkproject.ui.screens.signup.standardsignup.components.*
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette.Gunmetal
import com.sardes.thegabworkproject.ui.theme.TailwindCSSColor.Green500
import com.sardes.thegabworkproject.ui.theme.TailwindCSSColor.Red500
import kiwi.orbit.compose.ui.controls.Card
import kiwi.orbit.compose.ui.controls.Surface
import kiwi.orbit.compose.ui.controls.Text
import java.util.*

@Composable
fun StandardSignUpScreen(
    viewModel: StandardSignUpViewModel?,
    afterSignUp: (userId: String) -> Unit
//    navToStandardInterface: () -> Unit = {},
) {

    val standardUiState = viewModel?.signUpUiStateStandard
    val isError = standardUiState?.signUpError != null

    var step by remember { mutableStateOf(1) }

    val focusRequester = remember { FocusRequester() }

    val context = LocalContext.current

    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val date = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = "$dayOfMonth/${month + 1}/$year"
        }, year, month, day
    )


/*
    val selectedLanguages = remember {
        mutableStateListOf(
            "Français"
        )
    }

    val educations = remember {
        mutableStateListOf<Education>()
    }

    var experiences = remember{
        mutableStateListOf<Experience>()
    }

    var skills = remember {
        mutableStateListOf<Skill>()
    }

    var preferences = remember {
        mutableStateListOf<String>()
    }
*/


    if (standardUiState?.isLoading == true) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .padding(200.dp),
            color = Green500
        )
    } else {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = Red500
        ) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {

                //===============================================
                //                # HEADER
                //===============================================
                HeaderStandardSignUp(step)


                //===============================================
                //                # BODY
                //===============================================
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                        .background(
                            when (step) {
                                1 -> {
                                    Brush.horizontalGradient(
                                        colorStops = arrayOf(
                                            Pair(0.3f, LightGray),
                                            Pair(1f, Green500)
                                        )
                                    )
                                }
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
                        ),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        backgroundColor = Color.White,
                        shape = RoundedCornerShape(24.dp)
                    ) {

                        //================================================
                        //     # CONTENT
                        //================================================


                        when (step) {
                            1 -> StandardAccountDetails(
                                focusRequester = focusRequester,
                                uiState = standardUiState,
                                viewModel = viewModel
                            )

                            2 -> StandardPersonalInformations(
                                uiState = standardUiState,
                                viewModel = viewModel,
                                focusRequester = focusRequester,
                                date,
                                datePickerDialog,
                            )

                            3 -> OtherDetailsStandardSignUp(
                                uiState = standardUiState,
                                viewModel = viewModel,
                                focusRequester = focusRequester
                            )

                            4 -> SelectPhotoStandardSignUp(viewModel)


/*
                            5 -> LanguagesStandardSignUp(standardUiState, viewModel, selectedLanguages)

                            6 -> EducationStandardSignUp(
                                uiStateStandard = standardUiState,
                                viewModel = viewModel,
                                focusRequester = focusRequester,
                                educations
                            )

                            7 -> ExperienceStandardSignUp(viewModel, experiences)

                            8 -> SkillsStandardSignUp(
                                uiStateStandard = standardUiState,
                                viewModel = viewModel,
                                focusRequester = focusRequester,
                                skills
                            )

                            9 -> JobPreferences(
                                viewModel = viewModel,
                                focusRequester = focusRequester,
                                preferences = preferences
                            )
*/

                            5 -> StandardVerifyInformationsStep(isError, standardUiState, viewModel, context)
                        }


                    }


                    //================================================
                    //     # BOTTOM BUTTONS
                    //================================================
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        when (step) {
                            1 -> {
                                Card(
                                    backgroundColor = LightGray,
                                    modifier = Modifier
                                        .fillMaxWidth(0.5f)
                                ) {
                                    Text(
                                        text = "Précédent",
                                        color = Gunmetal,
                                        style = GWTypography.h6,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(vertical = 5.dp)
                                    )
                                }

                            }
                            else -> {
                                Card(
                                    backgroundColor = Red500,
                                    modifier = Modifier
                                        .fillMaxWidth(0.5f),
                                    onClick = { step -= 1 },
                                ) {
                                    Text(
                                        text = "Précédent",
                                        color = Color.White,
                                        style = GWTypography.h6,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(vertical = 5.dp)
                                    )
                                }

                            }
                        }

                        when (step) {
                            5 -> {
                                Card(
                                    backgroundColor = LightGray,
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                ) {
                                    Text(
                                        text = "Suivant",
                                        color = Color.White,
                                        style = GWTypography.h6,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(vertical = 5.dp)
                                    )
                                }
                            }
                            else -> {
                                Card(
                                    backgroundColor = Green500,
                                    modifier = Modifier
                                        .fillMaxWidth(1f),
                                    onClick = { step += 1 }
                                ) {
                                    Text(
                                        text = "Suivant",
                                        color = Color.White,
                                        style = GWTypography.h6,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(vertical = 5.dp)
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
                afterSignUp.invoke(viewModel.userId)
            }
        }
    }
}

@Preview(name = "StandardSignUpScreen", showBackground = true, showSystemUi = true)
@Composable
private fun PreviewStandardSignUpScreen() {
    StandardSignUpScreen(null){}
}