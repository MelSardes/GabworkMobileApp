package com.sardes.thegabworkproject.ui.screens.main.mainStandard.saves

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.home.HomeStandardViewModel
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette
import com.sardes.thegabworkproject.ui.theme.GWpalette.DarkLiver
import com.sardes.thegabworkproject.ui.theme.GWpalette.EauBlue
import com.sardes.thegabworkproject.ui.theme.GWpalette.Gunmetal
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun SavesStandardScreen(
    savesStandardViewModel: SavesStandardViewModel?,
    homeViewModel: HomeStandardViewModel?,
) {

    val uiState = homeViewModel?.homeStandardUiState

    val tabItems = listOf("Sauvegardes", "Propositions", "Posts")

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()


//    SavesSection(viewModel = homeViewModel, uiState = uiState)

    Scaffold (
        backgroundColor = Gunmetal,
        modifier = Modifier.clip(RoundedCornerShape(24.dp)),

        topBar = { SavesTopBar(homeStandardUiState = uiState)}
    ){
        Column(
            Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(it)
        ) {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                backgroundColor = EauBlue,
                modifier = Modifier
                    .padding(all = 10.dp)
                    .background(Color.Transparent)
                    .clip(RoundedCornerShape(24.dp)),
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier
                            .pagerTabIndicatorOffset(pagerState, tabPositions)
                            .fillMaxSize()
                            .clip(RoundedCornerShape(24.dp))
                            .zIndex(0.1f),
                        color = Gunmetal
                    )
                }
            ) {
                tabItems.forEachIndexed { index, title ->
//                    val color = remember { Animatable(Color.White) }

//                    LaunchedEffect(pagerState.currentPage == index) {
//                        color.animateTo(if (pagerState.currentPage == index) Gunmetal else EauBlue)
//                    }

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

            HorizontalPager(
                count = tabItems.size,
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->

                Box(modifier = Modifier.fillMaxSize()){
                    when(page){
                        0 -> SavesSection(viewModel = homeViewModel, uiState = uiState)
                        1 -> ProposalsSection()
                        2 -> ApplicationsSection()
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Applications() {

    var stateAll by remember { mutableStateOf(false) }
    var statePending by remember { mutableStateOf(false) }
    var stateRejected by remember { mutableStateOf(false) }
    var stateAccepted by remember { mutableStateOf(false) }
    var stateScheduled by remember { mutableStateOf(false) }



    Row{
        Chip(
            onClick = { stateAll = !stateAll },
            border = BorderStroke(
                ChipDefaults.OutlinedBorderSize,
                GWpalette.CoolGrey
            ),
            colors = ChipDefaults.chipColors(
                backgroundColor = when(stateAll){
                    false -> Color.White
                    true -> Gunmetal
                } ,
                contentColor = Color.Red
            ),
        ) {
            Text("Tout")
        }

        Chip(
            onClick = { stateAccepted = !stateAccepted },
            border = BorderStroke(
                ChipDefaults.OutlinedBorderSize,
                GWpalette.CoolGrey
            ),
            colors = ChipDefaults.chipColors(
                backgroundColor = when(stateAccepted){
                    false -> Color.White
                    true -> Gunmetal
                } ,
                contentColor = Color.Red
            ),
        ) {
            Text("Acceptés")
        }

        Chip(
            onClick = { statePending = !statePending },
            border = BorderStroke(
                ChipDefaults.OutlinedBorderSize,
                GWpalette.CoolGrey
            ),
            colors = ChipDefaults.chipColors(
                backgroundColor = when(statePending){
                    false -> Color.White
                    true -> Gunmetal
                } ,
                contentColor = Color.Red
            ),
        ) {
            Text("En attente")
        }

        Chip(
            onClick = { stateScheduled = !stateScheduled },
            border = BorderStroke(
                ChipDefaults.OutlinedBorderSize,
                GWpalette.CoolGrey
            ),
            colors = ChipDefaults.chipColors(
                backgroundColor = when(stateScheduled){
                    false -> Color.White
                    true -> Gunmetal
                } ,
                contentColor = Color.Red
            ),
        ) {
            Text("Interviews programmés")
        }

        Chip(
            onClick = { stateRejected = !stateRejected },
            border = BorderStroke(
                ChipDefaults.OutlinedBorderSize,
                GWpalette.CoolGrey
            ),
            colors = ChipDefaults.chipColors(
                backgroundColor = when(stateScheduled){
                    false -> Color.White
                    true -> Gunmetal
                } ,
                contentColor = Color.Red
            ),
        ) {
            Text("Rejetés")
        }

    }


}



@Preview(name = "SavesScreen")
@Composable
private fun PreviewSavesScreen() {
//    SavesStandardScreen(null, null)
//    Applications()
}


enum class Car(val value: String){
    AUDI("Audi"),
    VW("VW"),
    BMW("BWM"),
}

fun getAllCars(): List<Car>{
    return listOf(Car.AUDI, Car.VW, Car.BMW)
}

fun getCar(value: String): Car? {
    val map = Car.values().associateBy(Car::value)
    return map[value]
}


@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Preview(showBackground = true)
@Composable
fun Chip(
    name: String = "Chip",
//    isSelected: Boolean = false,
    onSelectionChanged: (String) -> Unit = {},
) {

    val isSelected by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.padding(4.dp),
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        color = if (isSelected) Color.LightGray else MaterialTheme.colors.primary
    ) {
        Row(modifier = Modifier
            .toggleable(
                value = isSelected,
                onValueChange = {
                    onSelectionChanged(name)
                }
            )
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.body2,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ChipGroup(
    cars: List<Car> = getAllCars(),
    selectedCar: Car? = null,
    onSelectedChanged: (String) -> Unit = {},
) {
    Column(modifier = Modifier.padding(8.dp)) {
        LazyRow {
            items(cars) {
                Chip(
                    name = it.value,
//                    isSelected = selectedCar == it,
                    onSelectionChanged = {
                        onSelectedChanged(it)
                    },
                )
            }
        }
    }
}


