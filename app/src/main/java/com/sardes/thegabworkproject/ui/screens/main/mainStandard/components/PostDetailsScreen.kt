package com.sardes.thegabworkproject.ui.screens.main.mainStandard.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.home.HomeStandardViewModel
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette
import com.sardes.thegabworkproject.ui.theme.GWpalette.EauBlue
import com.sardes.thegabworkproject.ui.theme.GWpalette.Gunmetal
import com.sardes.thegabworkproject.ui.theme.TailwindCSSColor
import kiwi.orbit.compose.icons.Icons
import kiwi.orbit.compose.ui.controls.IconButton
import kotlinx.coroutines.launch
import kiwi.orbit.compose.ui.R as OrbitR


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostDetailsScreen(
    postId: String,
    viewModel: HomeStandardViewModel?,
    onMessageClick: (entrepriseId: String) -> Unit,
    onApply: () -> Unit = {},
) {
    val context = LocalContext.current

    val uiState = viewModel?.homeStandardUiState

    LaunchedEffect(Unit) {
        viewModel?.getPost(postId)
    }

    Scaffold(
        modifier = Modifier
            .background(Gunmetal)
            .clip(RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp)),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = uiState?.selectedPost?.postName ?: "",
                        color = EauBlue,
                        style = GWTypography.h6
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Gunmetal),
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = OrbitR.drawable.ic_appbar_arrow_left),
                            contentDescription = null,
                            tint = EauBlue
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            if (uiState?.selectedPost?.savers?.contains(viewModel.userId) == true) {
                                viewModel.removeFromBookmarks(uiState.selectedPost.postId)
                                viewModel.getPost(postId)

                            } else {
                                viewModel?.addToBookmarks(
                                    postId = uiState?.selectedPost?.postId!!,
                                    entrepriseId = uiState.selectedPost.entrepriseId,
                                    postName = uiState.selectedPost.postName,
                                    entrepriseName = uiState.selectedPost.entrepriseName,
                                    urlLogo = uiState.selectedPost.urlLogo,
                                    salary = uiState.selectedPost.salaire,
                                    city = uiState.selectedPost.ville,
                                    province = uiState.selectedPost.province,
                                    jobType = uiState.selectedPost.typeDEmploi,
                                    context
                                )
                                viewModel?.getPost(postId)
                            }
                        },
                        rippleRadius = 16.dp,
                    ) {
                        Icon(
                            painter = Icons.Bookmark,
                            contentDescription = null,
                            tint =
                            if (uiState?.selectedPost?.savers?.contains(viewModel.userId) == true) TailwindCSSColor.Red500
                            else GWpalette.CoolGrey
                        )
                    }
                    IconButton(
                        onClick = { /*TODO*/ },
                        rippleRadius = 16.dp,
                    ) {
                        Icon(
                            painter = Icons.ShareAndroid,
                            contentDescription = null,
                            tint = EauBlue
                        )
                    }
                }
            )
        },

        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(Gunmetal),
            ) {
                Content(
                    padding = it,
                    post = uiState?.selectedPost,
                    modifier = Modifier.fillMaxSize()
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .align(Alignment.BottomCenter),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = {
                        onMessageClick.invoke(uiState?.selectedPost?.entrepriseId.toString())
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_message),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .clip(RoundedCornerShape(topEnd = 24.dp))
                                .background(Gunmetal)
                                .padding(10.dp)
                        )
                    }

                    Text(
                        "POSTULER MAINTENANT",
                        style = GWTypography.h6,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        modifier = Modifier
                            .clickable { onApply.invoke() }
                            .height(IntrinsicSize.Min)
                            .clip(RoundedCornerShape(topStart = 40.dp))
                            .background(Gunmetal)
                            .padding(horizontal = 30.dp, vertical = 10.dp)
                    )
                }
            }
        }
    )
}


@Composable
fun CardReview(review: CompteEntreprise.Post.Review) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
        ) {

            Card(
                modifier = Modifier.size(60.dp),
                colors = CardDefaults.cardColors(Color.White),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 8.dp
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.painted_paul),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(4.dp))
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = review.reviewerName ?: "",
                    style = GWTypography.subtitle1,
                    color = Gunmetal,
                    textAlign = TextAlign.End
                )
                Text(
                    text = review.date ?: "",
                    style = GWTypography.body2,
                    color = EauBlue,
                    textAlign = TextAlign.End
                )
            }
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
                .background(EauBlue)
                .height(1.dp)
        )

        Text(
            text = review.reviewCotent ?: "",
            style = GWTypography.body1.copy(fontWeight = FontWeight.Medium),
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
        )
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun Content(
    padding: PaddingValues,
    post: CompteEntreprise.Post?,
    modifier: Modifier = Modifier
) {

    val tabItems = listOf("A propos", "Commentaires")

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    var selectedTab by remember { mutableStateOf(1) }

    LazyColumn(
        modifier
            .padding(padding)
            .background(Color.White)
    ) {
        item {
            Surface(
                //        color = Gunmetal,
                modifier = Modifier
                    .clip(RoundedCornerShape(bottomEnd = 80.dp))
                    .background(Gunmetal.copy(alpha = 0.9f))
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .padding(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Gunmetal.copy(alpha = 0.9f)),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(id = R.drawable.painted_paul),
                            contentDescription = null,
                            alignment = Alignment.Center,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(24.dp))
                        )
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = post?.postName.toString(),
                            style = GWTypography.h4,
                            color = Color.White
                        )
                        Text(
                            text = post?.entrepriseName.toString(),
                            style = GWTypography.subtitle1,
                            color = EauBlue
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                        Text(
                            text = post?.domaine.toString(),
                            style = GWTypography.body1.copy(color = Color.White),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.1f))
                                .padding(vertical = 8.dp)
                                .weight(1f)
                        )
                        Spacer(modifier = Modifier.weight(0.1f))
                        Text(
                            text = post?.typeDEmploi.toString(),
                            style = GWTypography.body1.copy(color = Color.White),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.1f))
                                .padding(vertical = 8.dp)
                                .weight(1f)
                        )
                        Spacer(modifier = Modifier.weight(0.1f))
                        Text(
                            text = post?.experience.toString(),
                            style = GWTypography.body1.copy(color = Color.White),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.1f))
                                .padding(vertical = 8.dp)
                                .weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(end = 20.dp)
                    ) {
                        Text(
                            text = post?.salaire + "F/m",
                            style = GWTypography.subtitle2,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "${post?.ville}, ${post?.province}",
                            style = GWTypography.subtitle2,
                            color = Color.White
                        )
                    }
                }
            }
        }

        item {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                backgroundColor = Gunmetal.copy(alpha = 0.8f),
                modifier = Modifier
                    .padding(all = 15.dp)
                    .background(Color.Transparent)
                    .clip(RoundedCornerShape(24.dp)),
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier
                            .pagerTabIndicatorOffset(pagerState, tabPositions)
                            .fillMaxSize()
                            .padding(5.dp)
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
                                else GWTypography.body1.copy(color = EauBlue),
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

                when (page) {
                    0 -> About(post)
                    1 -> Comments(post)
                }
            }
        }
    }
}

@Composable
private fun Comments(post: CompteEntreprise.Post?) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            post?.comments?.forEach {
                CardReview(review = it)
            }
        }
    }
}

@Composable
private fun About(post: CompteEntreprise.Post?) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(24.dp)) {

            Text(
                "Description",
                style = GWTypography.subtitle2.copy(color = GWpalette.LackCoral),
                textDecoration = TextDecoration.Underline
            )
            Text(
                post?.descriptionEmploi.toString(),
                style = GWTypography.body1.copy(color = GWpalette.CoolGrey),
                modifier = Modifier.padding(start = 20.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                "Responsabilités :",
                style = GWTypography.subtitle2.copy(color = GWpalette.LackCoral),
                textDecoration = TextDecoration.Underline
            )
            post?.responsabilites?.forEach {
                Text(
                    "• $it",
                    style = GWTypography.body1.copy(color = GWpalette.CoolGrey),
                    modifier = Modifier.padding(start = 20.dp)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                "Compétences :",
                style = GWTypography.subtitle2.copy(color = GWpalette.LackCoral),
                textDecoration = TextDecoration.Underline
            )
            post?.competences?.forEach {
                Text(
                    "• $it",
                    style = GWTypography.body1.copy(color = GWpalette.CoolGrey),
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun PostDetailsScreenPreview() {
    PostDetailsScreen("", null, {""}) {}
}