package com.sardes.thegabworkproject.ui.screens.main.mainStandard.search

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.sardes.thegabworkproject.ui.screens.main.SearchViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.components.cards.ApplicablePostCard
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.home.HomeStandardViewModel
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette.CoolGrey
import com.sardes.thegabworkproject.ui.theme.GWpalette.DarkLiver
import com.sardes.thegabworkproject.ui.theme.GWpalette.Gunmetal
import com.sardes.thegabworkproject.ui.theme.GWpalette.LightRedStatus
import com.sardes.thegabworkproject.ui.theme.GWpalette.RedStatus
import kiwi.orbit.compose.ui.R
import kiwi.orbit.compose.ui.controls.Scaffold
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun SearchStandardScreen(
    viewModel: SearchViewModel?,
    homeViewModel: HomeStandardViewModel?,
    navToDiscover: () -> Unit
) {
    var hideKeyboard by remember { mutableStateOf(false) }

    val tabItems = listOf("Les plus récents", "UI/UX Design", "Architecture", "Sécrétariat", "Son")

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    val uiState = viewModel?.searchUiState ?: SearchViewModel.SearchUiState()

    val scope = rememberCoroutineScope()
    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    LaunchedEffect(Unit) {
        viewModel?.getAllPost()
    }


    LaunchedEffect(Unit) {
        viewModel?.loadFiveLatestPosts()
    }


    ModalBottomSheetLayout(

        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(White)
            ) {
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    hideKeyboard = hideKeyboard,
                    onFocusClear = { hideKeyboard = false },
                    viewModel = viewModel,
                    uiState = uiState
                ) {
                    scope.launch {
                        modalBottomSheetState.animateTo(ModalBottomSheetValue.HalfExpanded)
                    }
                }

                OutlinedButton(
                    onClick = { navToDiscover.invoke() },
                    shape = CircleShape,
                    contentPadding = PaddingValues(horizontal = 10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(LightRedStatus),
                    modifier = Modifier.align(Alignment.End).padding(end = 10.dp)
                ) {
                    Text(
                        "Découvrir",
                        style = GWTypography.subtitle1,
                        color = RedStatus,
                    )
                }

                when (uiState.searchState) {
                    false -> {
                        ScrollableTabRow(
                            selectedTabIndex = pagerState.currentPage,
                            backgroundColor = Transparent,
                            modifier = Modifier
                                .padding(vertical = 15.dp)
                                .background(Transparent),
                            indicator = { tabPositions ->
                                TabRowDefaults.Indicator(
                                    Modifier
                                        .pagerTabIndicatorOffset(pagerState, tabPositions)
                                        .fillMaxSize()
                                        .padding(7.dp)
                                        .clip(RoundedCornerShape(24.dp))
                                        .zIndex(0.1f),
                                    color = Gunmetal
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
                                                GWTypography.subtitle2.copy(color = Color.White)
                                            else GWTypography.body1.copy(color = DarkLiver),
                                            modifier = Modifier.zIndex(1f),
                                        )
                                    },
                                    selected = pagerState.currentPage == index,
                                    modifier = Modifier
                                        .background(
                                            color = Transparent,
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

                        HorizontalPager(
                            count = tabItems.size,
                            state = pagerState,
                            modifier = Modifier.fillMaxSize()
                        ) { page ->
                            for (i in tabItems) {
                                if (page == tabItems.indexOf(tabItems[page])) {
                                    SearchSomeItems(
                                        viewModel = viewModel,
                                        uiState = uiState,
                                        homeStandardViewModel = homeViewModel,
                                        query = tabItems[page]
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(50.dp))
                        }
                    }


                    true -> {
                        QueriedItems(
                            uiState = uiState,
                            homeStandardViewModel = homeViewModel,
                            query = uiState.query
                        )
                    }
                }
            }
        },

        sheetContent = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Mel SARDES")
            }
        }

    )
}


@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hideKeyboard: Boolean = false,
    viewModel: SearchViewModel?,
    uiState: SearchViewModel.SearchUiState,
    onFocusClear: () -> Unit = {},
    displaySheet: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val interactionSource = remember { MutableInteractionSource() }
    val interactionSourceState = interactionSource.collectIsFocusedAsState()
    val scope = rememberCoroutineScope()
    val ime = LocalWindowInsets.current.ime

    // Bring the composable into view (visible to user).
    LaunchedEffect(ime.isVisible, interactionSourceState.value) {
        if (ime.isVisible && interactionSourceState.value) {
            scope.launch {
                delay(300)
                bringIntoViewRequester.bringIntoView()
            }
        }
    }

//    Box(modifier = modifier.background(color = Transparent)) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.height(IntrinsicSize.Min)
    ) {

        TextField(
            value = uiState.query,
            onValueChange = {
                viewModel?.onQueryChange(it)
            },
            placeholder = { Text(text = "Emploi, domaine, service, ...", color = CoolGrey) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                focusManager.clearFocus()
                viewModel?.onQueryChange(uiState.query)
                viewModel?.onSearchStateChange(true)
            }),
            interactionSource = interactionSource,
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Gunmetal),
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 5.dp)
                .height(IntrinsicSize.Min)
                .bringIntoViewRequester(bringIntoViewRequester)
                .weight(1f)
//                .shadow(1.dp, CircleShape)
//                .background(LightGray.copy(alpha = 0.3f))
//                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {

                },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            shape = CircleShape,

            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = LightGray,
                focusedIndicatorColor = Transparent,
                unfocusedIndicatorColor = Transparent,
                textColor = DarkLiver,
            )
        )

//    }

        if (hideKeyboard) {
            focusManager.clearFocus()
            // Call onFocusClear to reset hideKeyboard state to false
            onFocusClear()
        }

        IconButton(onClick = { displaySheet.invoke() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_orbit_filters),
                contentDescription = null,
                tint = White,
                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxHeight()
                    .clip(CircleShape)
                    .background(LightGray)
                    .padding(10.dp)
            )

        }
    }
}

@Preview(name = "SavesScreen", showSystemUi = true)
@Composable
private fun PreviewSavesScreen() {
    SearchStandardScreen(null, null) {}
}


@Composable
fun SearchSomeItems(
    viewModel: SearchViewModel?,
    uiState: SearchViewModel.SearchUiState,
    homeStandardViewModel: HomeStandardViewModel?,
    query: String
) {

    val queryList = uiState.fiveLatestPostsList.data?.filter {
        it.postName.contains(query)
    } ?: emptyList()

    LazyColumn(Modifier.fillMaxSize()) {

        if (queryList.isEmpty()) {
            item { Text(text = "Nous n'avons rien pour \"$query\"") }
        } else {
            item { Text(text = "Des posts pour vous") }
            queryList.forEach {
                item {
                    ApplicablePostCard(post = it, viewModel = homeStandardViewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchResults(
    uiState: SearchViewModel.SearchUiState?,
    homeViewModel: HomeStandardViewModel?,
    query: String = "son"
) {

    Scaffold(
        content = {
            QueriedItems(
                uiState = uiState,
                homeStandardViewModel = homeViewModel,
                query = query
            )
        }
    )

}

@Composable
fun QueriedItems(
    uiState: SearchViewModel.SearchUiState?,
    homeStandardViewModel: HomeStandardViewModel?,
    query: String
) {

    val queryList = uiState?.allPosts?.data?.filter { post ->
        post.postName
            .lowercase()
            .replace('à', 'a')
            .replace('â', 'a')
            .replace('è', 'e')
            .replace('é', 'e')
            .replace('ê', 'e')
            .replace('ï', 'i')
            .replace('î', 'i')
            .replace('ö', 'o')
            .replace('ô', 'o')
            .replace('û', 'u')
            .contains(
                query
                    .lowercase()
                    .replace('à', 'a')
                    .replace('â', 'a')
                    .replace('è', 'e')
                    .replace('é', 'e')
                    .replace('ê', 'e')
                    .replace('ï', 'i')
                    .replace('î', 'i')
                    .replace('ö', 'o')
                    .replace('ô', 'o')
                    .replace('û', 'u')
            )
    } ?: emptyList()

    LazyColumn(Modifier.fillMaxWidth()) {
        item { Text(text = "Résultats pour $query") }

        if (queryList.isEmpty()) {
            item { Text(text = "Nous n'avons rien pour \"$query\"") }
        } else {
            queryList.forEach {
                item {
                    ApplicablePostCard(post = it, viewModel = homeStandardViewModel)
                }
            }
        }
    }
}