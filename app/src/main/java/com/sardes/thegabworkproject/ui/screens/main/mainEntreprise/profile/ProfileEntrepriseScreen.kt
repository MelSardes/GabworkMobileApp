package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.profile

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.insets.LocalWindowInsets
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.ui.composition.CircularButton
import com.sardes.thegabworkproject.ui.theme.AppBarCollapsedHeight
import com.sardes.thegabworkproject.ui.theme.AppBarExpendedHeight
import com.sardes.thegabworkproject.ui.theme.BlueFlag
import kotlin.math.max
import kotlin.math.min

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun ProfileEntrepriseScreen(
    profileViewModel: ProfileEntrepriseViewModel?,
) {
    val scrollState = rememberLazyListState()

    val profileUiState = profileViewModel?.informationsUiState ?: InformationsUiState()

    LaunchedEffect(Unit){
        profileViewModel?.loadInformations()
    }

    Box {
        Content(scrollState, profileUiState)
        ParallaxToolbar(scrollState, profileUiState)
    }
}


@SuppressLint("FrequentlyChangedStateReadInComposition", "MaterialDesignInsteadOrbitDesign")
@Composable
fun ParallaxToolbar(
    scrollState: LazyListState,
    profileUiState: InformationsUiState,
) {
    val imageHeight = AppBarExpendedHeight - AppBarCollapsedHeight

    val maxOffset =
        with(LocalDensity.current) { imageHeight.roundToPx() } - LocalWindowInsets.current.systemBars.layoutInsets.top

    val offset = min(scrollState.firstVisibleItemScrollOffset, maxOffset)

    val offsetProgress = max(0f, offset * 3f - 2f * maxOffset) / maxOffset

    TopAppBar(
        contentPadding = PaddingValues(),
        backgroundColor = White,
        modifier = Modifier
            .height(AppBarExpendedHeight)
            .offset { IntOffset(x = 0, y = -offset) },
        elevation = if (offset == maxOffset) 4.dp else 0.dp
    ) {
        Column {
            Box(
                Modifier
                    .height(imageHeight)
                    .graphicsLayer {
                        alpha = 1f - offsetProgress
                    }
            ) {

                if(profileUiState.currentUserEntreprise?.urlLogo != null) {
                    AsyncImage(
                        model = ImageRequest
                            .Builder(LocalContext.current)
                            .data("${profileUiState.currentUserEntreprise.urlLogo}")
                            .crossfade(true)
                            .crossfade(1000)
                            .placeholder(R.drawable.ic_image)
                            .error(R.drawable.ic_broken_image)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                else {
                    Image(
                        painter = painterResource(id = R.drawable.perroquet),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colorStops = arrayOf(
                                    Pair(0.5f, Transparent),
                                    Pair(1f, White)
                                )
                            )
                        )
                )

                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        "${profileUiState.currentUserEntreprise?.activite}",
                        fontWeight = FontWeight.Medium,
                        color = BlueFlag,
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(LightGray)
                            .padding(vertical = 6.dp, horizontal = 16.dp)
                    )
                }
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .height(AppBarCollapsedHeight),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "${profileUiState.currentUserEntreprise?.nom}",
                    style = typography.h5.copy(fontWeight = FontWeight.Bold),
                    color = BlueFlag,
                    modifier = Modifier
                        .padding(horizontal = (16 + 28 * offsetProgress).dp)
                        .scale(1f - 0.25f * offsetProgress)
                )
            }
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(AppBarCollapsedHeight)
            .padding(horizontal = 16.dp)
    ) {
        CircularButton(R.drawable.ic_arrow_back)
        CircularButton(R.drawable.ic_more_vert)
    }
}


@Composable
fun Content(
    scrollState: LazyListState,
    profileUiState: InformationsUiState,
) {
    LazyColumn(
        contentPadding = PaddingValues(top = AppBarExpendedHeight),
        state = scrollState
    ) {
        item {
            Description(profileUiState)
            BasicInfo(profileUiState)
            Address(profileUiState)
        }
    }
}

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun Address(profileUiState: InformationsUiState) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Adresse de bureau",
            fontWeight = FontWeight.Bold,
            color = Gray,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
        )
        Text(
            text = "${profileUiState.currentUserEntreprise?.adresse}",
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
    }
}

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun Description(profileUiState: InformationsUiState) {
    Text(
        text = "${profileUiState.currentUserEntreprise?.description}",
        style = typography.body1,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
    )
}

@Composable
fun BasicInfo(profileUiState: InformationsUiState) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            InfoColumn(R.drawable.ic_post, "27", "posts")
            InfoColumn(R.drawable.ic_people, "30", "recrutements")
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)

        ) {
            InfoColumn(R.drawable.ic_student, "8", "étudiants")
            InfoColumn(R.drawable.ic_employees, "Entre 1 et 50", "employés")
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            InfoColumn(
                R.drawable.ic_web,
                "Visitez",
                "${profileUiState.currentUserEntreprise?.siteWeb}"
            )
            InfoColumn(
                R.drawable.ic_industry,
                "Oeuvre dans le/la/les",
                "${profileUiState.currentUserEntreprise?.activite}"
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            InfoColumn(
                R.drawable.ic_employees,
                "Existe depuis",
                "2042"
            )
            InfoColumn(
                kiwi.orbit.compose.ui.R.drawable.ic_orbit_city,
                "Rendez vous à",
                "",
                /*"${profileUiState.currentUserEntreprise?.ville}"*/
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            InfoColumn(
                R.drawable.ic_email,
                "Contactez",
                "${profileUiState.currentUserEntreprise?.email}"
            )
            InfoColumn(
                kiwi.orbit.compose.ui.R.drawable.ic_orbit_phone,
                "Appelez le",
                "${profileUiState.currentUserEntreprise?.telephone}"
            )
        }
    }
}

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun InfoColumn(@DrawableRes iconResouce: Int, text1: String, text2: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(id = iconResouce),
            contentDescription = null,
            tint = BlueFlag,
            modifier = Modifier.height(24.dp)
        )
        Text(text = text1, fontWeight = FontWeight.Bold, color = Gray)
        Text(text = text2, fontWeight = FontWeight.SemiBold)
    }
}

//@Preview(showSystemUi = true, device = "id:TECNO POP3")
@Preview(showSystemUi = true, device = "spec:width=1280dp,height=800dp,dpi=480")
@Preview(showSystemUi = true, device = "id:pixel")
@Preview(showSystemUi = true, device = "id:pixel_3")
@Composable
fun ScreenConcept() {
    ProfileEntrepriseScreen(null)
}