package com.sardes.thegabworkproject.ui.screens.signup.seekersignup.screens

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
import com.sardes.thegabworkproject.data.models.Education
import com.sardes.thegabworkproject.data.models.Experience
import com.sardes.thegabworkproject.data.models.Skill
import com.sardes.thegabworkproject.ui.screens.signup.seekersignup.SeekerSignUpViewModel
import com.sardes.thegabworkproject.ui.screens.signup.seekersignup.components.*
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette.Gunmetal
import com.sardes.thegabworkproject.ui.theme.TailwindCSSColor.Green500
import com.sardes.thegabworkproject.ui.theme.TailwindCSSColor.Red500
import kiwi.orbit.compose.ui.controls.Card
import kiwi.orbit.compose.ui.controls.Surface
import kiwi.orbit.compose.ui.controls.Text
import java.util.*

@Composable
fun SeekerSignUpScreen(
    viewModel: SeekerSignUpViewModel?,
    navToSeekerInterface: () -> Unit = {},
) {

    val seekerUiState = viewModel?.signUpUiStateSeeker
    val isError = seekerUiState?.signUpError != null

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


    if (seekerUiState?.isLoading == true) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            color = Green500
        )
    } else {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = Red500
        ) {
            Column(modifier = Modifier.fillMaxSize()) {

                //===============================================
                //                # HEADER
                //===============================================
                HeaderSeekerSignUp(step)


                //===============================================
                //                # BODY
                //===============================================
                Column(
                    modifier = Modifier
                        .fillMaxHeight(1f)
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
                                10 -> {
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
                        backgroundColor = Color.White,
                        shape = RoundedCornerShape(24.dp)
                    ) {

                        //================================================
                        //     # CONTENT
                        //================================================


                        when (step) {
                            1 -> SeekerAccountDetails(
                                focusRequester = focusRequester,
                                uiState = seekerUiState,
                                viewModel = viewModel
                            )

                            2 -> SeekerPersonalInformations(
                                uiState = seekerUiState,
                                viewModel = viewModel,
                                focusRequester = focusRequester,
                                date,
                                datePickerDialog,
                            )

                            3 -> OtherDetailsSeekerSignUp(
                                uiState = seekerUiState,
                                viewModel = viewModel,
                                focusRequester = focusRequester
                            )

                            4 -> SelectPhotoSeekerSignUp(viewModel)


                            5 -> LanguagesSeekerSignUp(seekerUiState, viewModel, selectedLanguages)

                            6 -> EducationSeekerSignUp(
                                uiStateSeeker = seekerUiState,
                                viewModel = viewModel,
                                focusRequester = focusRequester,
                                educations
                            )

                            7 -> ExperienceSeekerSignUp(viewModel, experiences)

                            8 -> SkillsSeekerSignUp(
                                uiStateSeeker = seekerUiState,
                                viewModel = viewModel,
                                focusRequester = focusRequester,
                                skills
                            )

                            9 -> JobPreferences(
                                viewModel = viewModel,
                                focusRequester = focusRequester,
                                preferences = preferences
                            )

                            10 -> SeekerVerifyInformationsStep(isError, seekerUiState, viewModel, context)
                        }


                    }


                    //================================================
                    //     # BOTTOM BUTTONS
                    //================================================
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.06f)
                    ) {
                        when (step) {
                            1 -> {
                                Card(
                                    backgroundColor = LightGray,
                                    modifier = Modifier
                                        .fillMaxWidth(0.5f)
                                        .fillMaxHeight(),
                                ) {
                                    Text(
                                        text = "Précédent",
                                        color = Gunmetal,
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
                                    onClick = { step -= 1 },
                                ) {
                                    Text(
                                        text = "Précédent",
                                        color = Color.White,
                                        style = GWTypography.h6,
                                        textAlign = TextAlign.Center
                                    )
                                }

                            }
                        }

                        when (step) {
                            10 -> {
                                Card(
                                    backgroundColor = LightGray,
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .fillMaxHeight(),
                                ) {
                                    Text(
                                        text = "Suivant",
                                        color = Color.White,
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
                                    onClick = { step += 1 }
                                ) {
                                    Text(
                                        text = "Suivant",
                                        color = Color.White,
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
                navToSeekerInterface.invoke()
            }
        }

    }
}

@Preview(name = "SeekerSignUpScreen", showBackground = true, showSystemUi = true)
@Composable
private fun PreviewSeekerSignUpScreen() {
    SeekerSignUpScreen(null)
}