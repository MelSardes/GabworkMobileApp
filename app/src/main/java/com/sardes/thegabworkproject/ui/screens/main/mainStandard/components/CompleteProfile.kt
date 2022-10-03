package com.sardes.thegabworkproject.ui.screens.main.mainStandard.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.sardes.thegabworkproject.data.models.CompteStandard
import com.sardes.thegabworkproject.data.models.Skill
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.home.HomeStandardViewModel
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette
import com.sardes.thegabworkproject.ui.theme.GWpalette.Gunmetal
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CompleteProfile(
    viewModel: HomeStandardViewModel?,
    onFinish: () -> Unit
) {
    val uiState = viewModel?.homeStandardUiState

    val tabItems = listOf("Éducaton", "Éxpérience", "Compétences", "Langues", "Emplois", "Vérification")

    val educations = remember { mutableStateListOf<CompteStandard.Education>() }
    val experiences = remember { mutableStateListOf<CompteStandard.Experience>() }
    val selectedLanguages = remember { mutableStateListOf("") }
    val preferences = remember { mutableStateListOf("") }
    val skills = remember { mutableStateListOf<Skill>() }

    val focusRequester = FocusRequester()

    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    val context = LocalContext.current
    
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Gunmetal.copy(alpha = 0.8f), elevation = 10.dp) {
                ScrollableTabRow(
                    selectedTabIndex = pagerState.currentPage,
                    backgroundColor = Gunmetal.copy(alpha = 0.8f),
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Gunmetal.copy(alpha = 0.8f))
                        .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)),
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            Modifier
                                .pagerTabIndicatorOffset(pagerState, tabPositions)
                                .fillMaxSize()
                                .padding(top = 2.dp, bottom = 2.dp)
                                .clip(RoundedCornerShape(24.dp))
                                .zIndex(0.1f),
                            color = Color.White
                        )
                    }
                ) {
                    tabItems.forEachIndexed { index, title ->
                        Tab(
                            text = {
                                Text(
                                    text = title,
                                    style =
                                    if (pagerState.currentPage == index)
                                        GWTypography.subtitle2.copy(color = Gunmetal)
                                    else GWTypography.body1.copy(color = GWpalette.EauBlue),
                                    modifier = Modifier.zIndex(1f),
                                )
                            },
                            selected = pagerState.currentPage == index,
                            modifier = Modifier
                                .background(
                                    color = Color.Transparent,
                                    shape = RoundedCornerShape(24.dp)
                                )
                                .zIndex(1f),
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            }
                        )
                    }
                }

            }
        },
        containerColor = Gunmetal.copy(alpha = 0.8f)

    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(padding)
        ) {

            HorizontalPager(
                count = tabItems.size,
                state = pagerState,
                modifier = Modifier.fillMaxWidth()
            ) { page ->

                Box(modifier = Modifier.fillMaxSize()) {
                    when (page) {
                        0 -> {
                            EducationToComplete(
                                uiState = uiState,
                                viewModel = viewModel,
                                focusRequester = focusRequester,
                                educations = educations
                            )
                        }
                        1 -> {
                            ExperienceToComplete(
                                viewModel = viewModel,
                                experiences = experiences
                            )
                        }
                        2 -> {
                            SkillsToComplete(
                                viewModel = viewModel,
                                focusRequester = focusRequester,
                                skills = skills
                            )
                        }

                        3 -> {
                            LanguagesToComplete(
                                uiState = uiState,
                                viewModel = viewModel,
                                selectedLanguages = selectedLanguages
                            )
                        }
                        4 -> {
                            JobPreferencesToComplete(
                                viewModel = viewModel,
                                focusRequester = focusRequester,
                                preferences = preferences,
                                uiState = uiState
                            )
                        }

                        5 -> InformationsVerification(
                            uiState = uiState,
                            viewModel = viewModel,
                            context = context,
                            onFinish = {onFinish.invoke()}
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Preview(name = "CompleteProfile")
@Composable
private fun PreviewCompleteProfile() {
    CompleteProfile(null){}
}