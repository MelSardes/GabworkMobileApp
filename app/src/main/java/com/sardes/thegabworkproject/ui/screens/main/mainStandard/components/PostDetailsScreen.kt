package com.sardes.thegabworkproject.ui.screens.main.mainStandard.components

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.components.cards.CardReview
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.home.HomeStandardUiState
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.home.HomeStandardViewModel
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette
import com.sardes.thegabworkproject.ui.theme.GWpalette.DarkLiver
import com.sardes.thegabworkproject.ui.theme.GWpalette.EauBlue
import com.sardes.thegabworkproject.ui.theme.GWpalette.Gunmetal
import com.sardes.thegabworkproject.ui.theme.GWpalette.ImperialRed
import com.sardes.thegabworkproject.ui.theme.GWpalette.LackCoral
import com.sardes.thegabworkproject.ui.theme.GWpalette.LightRedStatus
import com.sardes.thegabworkproject.ui.theme.TailwindCSSColor
import kiwi.orbit.compose.icons.Icons
import kiwi.orbit.compose.ui.controls.ButtonBundleTop
import kiwi.orbit.compose.ui.controls.IconButton
import kiwi.orbit.compose.ui.controls.TextField
import kotlinx.coroutines.launch
import kiwi.orbit.compose.ui.R as OrbitR


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PostDetailsScreen(
    postId: String,
    viewModel: HomeStandardViewModel?,
    onMessageClick: (entrepriseId: String) -> Unit,
//    onApply: () -> Unit = {},
    completeAccount: () -> Unit = {}
) {


    val context = LocalContext.current

    val uiState = viewModel?.homeStandardUiState

    val focusRequester = FocusRequester()

    LaunchedEffect(Unit) {
        viewModel?.getUserInformations()
    }

    LaunchedEffect(Unit) {
        viewModel?.getPost(postId)
    }

    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()


//    val status: Boolean? = uiState?.userInformations?.isComplete

    ModalBottomSheetLayout(
//        backgroundColor = Gunmetal,
        sheetShape = RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp),
        sheetState = modalBottomSheetState,
        modifier = Modifier.background(Gunmetal),
//        topBar = { TopBar(uiState, viewModel, uiState?.selectedPost, postId, context) },

        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Gunmetal),
            ) {
                Content(
//                    padding = it,
                    post = uiState?.selectedPost,
                    modifier = Modifier.fillMaxSize(),
                    viewModel = viewModel,
                    uiState = uiState,
                    focusRequester
                )

                Row(
                    modifier = Modifier
                        .height(IntrinsicSize.Min)
                        .width(IntrinsicSize.Max)
                        .align(Alignment.BottomEnd)
                        .clip(RoundedCornerShape(topStart = 24.dp))
                        .background(LightRedStatus.copy(alpha = 0.9f)),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = {
                        onMessageClick.invoke(uiState?.selectedPost?.entrepriseId.toString())
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_message),
                            contentDescription = null,
                            tint = ImperialRed,
                            modifier = Modifier
                                .clip(RoundedCornerShape(topStart = 24.dp))
                                .padding(vertical = 10.dp, horizontal = 15.dp)
                        )
                    }

                    Text(
                        "POSTULER MAINTENANT ${uiState?.userInformations?.isComplete}",
                        style = GWTypography.h6,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(topStart = 24.dp))
                            .height(IntrinsicSize.Min)
                            .background(ImperialRed)
                            .clickable {
                                //                                completeAccount.invoke()

//                                if (uiState?.userInformations?.isComplete == 1) {
//                                    completeAccount.invoke()
//                                } else {
//                                onApply.invoke()
//                                }

                                scope.launch {
                                    modalBottomSheetState.animateTo(ModalBottomSheetValue.HalfExpanded)
                                }

                            }
                            .padding(horizontal = 30.dp, vertical = 8.dp)
                    )
                }
            }
        },

        sheetContent = {
            ExpandedSheet(post = uiState?.selectedPost, viewModel = viewModel, context = context, uiState = uiState) {
                scope.launch {
                    modalBottomSheetState.animateTo(ModalBottomSheetValue.Hidden)
                }
            }
        },

    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopBar(
    uiState: HomeStandardUiState?,
    viewModel: HomeStandardViewModel?,
    selectedPost: CompteEntreprise.Post?,
    postId: String,
    context: Context
) {
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
                    if (uiState?.selectedPost?.savers?.contains(viewModel?.userId) == true) {
                        viewModel?.removeFromBookmarks(selectedPost?.postId, context)
                        viewModel?.getPost(postId)

                    } else {
                        viewModel?.addToBookmarks(
                            postId = uiState?.selectedPost?.postId!!,
                            entrepriseId = selectedPost?.entrepriseId,
                            postName = selectedPost?.postName,
                            entrepriseName = selectedPost?.entrepriseName,
                            urlLogo = selectedPost?.urlLogo,
                            salary = selectedPost?.salary,
                            city = selectedPost?.city,
                            province = selectedPost?.province,
                            jobType = selectedPost?.jobType,
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
                    if (uiState?.selectedPost?.savers?.contains(viewModel?.userId) == true) TailwindCSSColor.Red500
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
}


@SuppressLint("MaterialDesignInsteadOrbitDesign")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun Content(
//    padding: PaddingValues,
    post: CompteEntreprise.Post?,
    modifier: Modifier = Modifier,
    viewModel: HomeStandardViewModel?,
    uiState: HomeStandardUiState?,
    focusRequester: FocusRequester
) {

    val tabItems = listOf("A propos", "Commentaires")

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()


    LazyColumn(
        modifier
//            .padding(padding)
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
                            text = post?.postName ?: "Erreur!!!",
                            style = GWTypography.h4,
                            color = Color.White
                        )
                        Text(
                            text = post?.entrepriseName ?: "Erreur!!!",
                            style = GWTypography.subtitle1,
                            color = EauBlue
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                        Text(
                            text = post?.domain.toString(),
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
                            text = post?.jobType.toString(),
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

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(end = 20.dp)
                    ) {
                        Text(
                            text = post?.salary + "F/m",
                            style = GWTypography.subtitle2,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "${post?.city}, ${post?.province}",
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
                    1 -> Comments(post, viewModel, uiState, focusRequester)
                }
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}

@Composable
private fun Comments(
    post: CompteEntreprise.Post?,
    viewModel: HomeStandardViewModel?,
    uiState: HomeStandardUiState?,
    focusRequester: FocusRequester
) {
    Box(modifier = Modifier.fillMaxSize()) {

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .align(Alignment.BottomCenter)
                .padding(bottom = 5.dp),
            value = uiState?.comment ?: "",
            singleLine = false,
            maxLines = 10,
            onValueChange = { viewModel?.onCommentChange(it) },
            trailingIcon = {
                IconButton(onClick = {
                    if (!uiState?.comment.isNullOrBlank()) {
                        viewModel?.addComment(
                            post = post,
                            reviewerId = viewModel.userId,
                            reviewerName = uiState?.userInformations?.name + " " + uiState?.userInformations?.forename,
                            reviewerContent = uiState?.comment!!,
                            urlPhoto = uiState.userInformations?.urlPhoto!!
                        )
                    }
                }) {
                    Icon(
                        painter = painterResource(id = kiwi.orbit.compose.ui.R.drawable.ic_orbit_send),
                        contentDescription = null,
                        tint = GWpalette.LackCoral
                    )
                }
            },
        )


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
        Column(
            modifier = Modifier
                .padding(24.dp)
                .padding(bottom = 50.dp)
        ) {

            Text(
                "Description",
                style = GWTypography.subtitle2.copy(color = GWpalette.LackCoral),
                textDecoration = TextDecoration.Underline
            )
            Text(
                post?.description.toString(),
                style = GWTypography.body1.copy(color = GWpalette.CoolGrey),
                modifier = Modifier.padding(start = 20.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                "Responsabilités :",
                style = GWTypography.subtitle2.copy(color = GWpalette.LackCoral),
                textDecoration = TextDecoration.Underline
            )
            post?.responsibilities?.forEach {
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
            post?.skills?.forEach {
                Text(
                    "• $it",
                    style = GWTypography.body1.copy(color = GWpalette.CoolGrey),
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
        }
    }
}


@Preview(name = "Post Details Preview")
@Composable
fun PostDetailsScreenPreview() {
    PostDetailsScreen("", null, { "" }) {}
}

@Preview
@Composable
fun JobDetailsCard(post: CompteEntreprise.Post? = null) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .width(327.dp)
            .height(114.dp),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.painted_paul),
                contentDescription = null,
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Spacer(modifier = Modifier.width(15.dp))

            Column(
                verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = post?.postName.toString(),
                        style = GWTypography.subtitle1
                    )
                    Text(
                        text = post?.salary + " F/m",
                        style = GWTypography.subtitle2
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = post?.entrepriseName.toString(),
                        style = GWTypography.body1
                    )
                    Text(
                        text = post?.city.toString() +" " + post?.jobType,
                        style = GWTypography.body1.copy(color = GWpalette.EauBlue)
                    )
                }
            }
        }
    }
}

@Composable
fun CoverLetter(viewModel: HomeStandardViewModel?, uiState: HomeStandardUiState?) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.Start) {
            Text(
                "Lettre de motivation",
                style = GWTypography.h6.copy(color = DarkLiver),
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = uiState?.coverLetter!!,
                onValueChange = { viewModel?.onCoverLetterChange(it) },
                Modifier
//                    .height(100.dp)
                    .fillMaxWidth()
                    .shadow(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(8.dp),
                        ambientColor = LackCoral
                    )
                    .background(Color.White),
                placeholder = { Text("Écrire une lettre de motivation") },
                maxLines = 100,
                singleLine = false,
            )
        }
    }
}


@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
private fun ApplicationHeader(onCollapse: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { onCollapse() },
            Modifier.padding(4.dp)
        ) {
            Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.KeyboardArrowDown,
                contentDescription = "Collapse Application icon"
            )
        }
        Text(
            "Postuler".uppercase(),
            style = GWTypography.subtitle1
        )
    }
}

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun ExpandedSheet(
    post: CompteEntreprise.Post?,
    viewModel: HomeStandardViewModel?,
    uiState: HomeStandardUiState?,
    context: Context,
    onCollapse: () -> Unit,
) {

    Surface(color = EauBlue) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            ApplicationHeader(onCollapse = onCollapse)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 64.dp)
            ) {

                JobDetailsCard(post)

                CoverLetter(viewModel, uiState)

                ButtonBundleTop(
                    onClick = {
                        viewModel?.addApplication(
                            viewModel.userId,
                            post?.postId!!,
                            post.postName,
                            post.entrepriseName,
                            post.urlLogo!!,
                            post.salary,
                            uiState?.coverLetter!!,
                            post.city,
                            post.jobType,
                            uiState.userInformations?.name!!,
                            uiState.userInformations.urlPhoto!!,
                            context = context
                        )
                    }
                ) {
                    Text("Postuler maintenant")
                }
            }
        }
    }
}
