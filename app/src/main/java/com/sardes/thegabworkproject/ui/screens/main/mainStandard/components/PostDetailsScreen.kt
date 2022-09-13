package com.sardes.thegabworkproject.ui.screens.main.mainStandard.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.android.material.R.drawable.mtrl_ic_error
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
import kiwi.orbit.compose.ui.R as OrbitR


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostDetailsScreen(
    postId: String,
    homeStandardViewModel: HomeStandardViewModel?,
//    reviewer: CompteEntreprise.Post.Reviewer,
    onApply: () -> Unit = {},
) {

    val uiState = homeStandardViewModel?.homeStandardUiState

    LaunchedEffect(key1 = Unit) {
        homeStandardViewModel?.getPost(postId)
    }

    Scaffold(
        modifier = Modifier.background(Gunmetal),
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
                        onClick = { /*TODO*/ },
                        rippleRadius = 16.dp,
                    ) {
                        Icon(
                            painter = Icons.Bookmark,
                            contentDescription = null,
                            tint =
                            GWpalette.CoolGrey
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
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Content(
                    padding = it,
                    post = uiState?.selectedPost,
//                    reviewer, TODO: ADD REVIEWS
                    modifier = Modifier.fillMaxHeight(0.93f)
                )

                Text(
                    "POSTULER MAINTENANT",
                    style = GWTypography.h4,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Gunmetal).weight(1f)
                        .clickable { onApply.invoke() },
                )

            }


        }
    )
}

val myList = listOf(
    "qwrtyuiop qwrtyuiop qwetyuiop qwetyuiop qwtyuiop qwtyuio qwrtyuiop qwetyuiop qwrtyuio",
    "sjbvnoerv qwrtyuiop qwetyuiop qwetyuiop qwtyuiop qwtyuio qwrtyuiop qwetyuiop qwrtyuio",
    "hvbwuvbwe qwrtyuiop qwetyuiop qwetyuiop qwtyuiop qwtyuio qwrtyuiop qwetyuiop qwrtyuio",
    "onvuwueef qwrtyuiop qwetyuiop qwetyuiop qwtyuiop qwtyuio qwrtyuiop qwetyuiop qwrtyuio",
    "piowbwbbe qwrtyuiop qwetyuiop qwetyuiop qwtyuiop qwtyuio qwrtyuiop qwetyuiop qwrtyuio",
)


@Composable
private fun Skills(post: CompteEntreprise.Post?, modifier: Modifier) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Compétences",
                style = GWTypography.h6,
                color = Gunmetal
            )
            LazyColumn {
                post?.competences?.forEach {
                    item {
                        Text(
                            text = "• $it",
                            style = GWTypography.body1.copy(fontWeight = FontWeight.Medium)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}

@SuppressLint("PrivateResource")
@Composable
private fun Content(
    padding: PaddingValues,
    post: CompteEntreprise.Post?,
    review: CompteEntreprise.Post.Review = CompteEntreprise.Post.Review(),
    modifier: Modifier
) {
    var selectedTab by remember { mutableStateOf(2) }

    Box(
        modifier = modifier
            .padding(padding)
            .background(Gunmetal)
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(270.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.Start
                ) {

                    Text(
                        text = post?.postName ?: "",
                        textAlign = TextAlign.Start,
                        style = GWTypography.h3,
                        color = Gunmetal,
                        modifier = Modifier.weight(1f)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround,
                    ) {
                        Text(
                            text = post?.domaine ?: "",
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            style = GWTypography.body2,
                            color = Color.White,
                            modifier = Modifier
                                .padding(end = 5.dp)
                                .weight(1f)
                                .clip(RoundedCornerShape(20.dp))
                                .background(TailwindCSSColor.Pink900)
                                .padding(5.dp)
                        )

                        Text(
                            text = post?.typeDEmploi ?: "",
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            style = GWTypography.body2,
                            color = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                                .weight(1f)
                                .clip(RoundedCornerShape(20.dp))
                                .background(TailwindCSSColor.Pink900)
                                .padding(5.dp)
                        )

                        Text(
                            text = post?.experience ?: "",
                            textAlign = TextAlign.Center,
                            style = GWTypography.body2,
                            color = Color.White,
                            modifier = Modifier
                                .padding(start = 5.dp)
                                .weight(1f)
                                .clip(RoundedCornerShape(20.dp))
                                .background(TailwindCSSColor.Pink900)
                                .padding(5.dp)
                        )
                    }



                    Text(
                        text = (post?.salaire ?: "") + "F/mois",
                        color = GWpalette.LackCoral,
                        style = GWTypography.subtitle2,
                    )

                    Text(
                        text = post?.ville + "/" + post?.province,
                        style = GWTypography.subtitle2,
                        color = GWpalette.CoolGrey
                    )

                    Text(
                        text = if (post?.totalApplicants == 0) "Soyez le premier à postuler" else {
                            post?.totalApplicants.toString() +
                                    if (post?.totalApplicants == 1) "personne a déjà postulé"
                                    else " personnes ont déjà postulé"
                        },
                        style = GWTypography.subtitle2,
                        color = GWpalette.CoolGrey
                    )


                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(30.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Card(
                                modifier = Modifier.size(60.dp),
                                colors = CardDefaults.cardColors(Color.White),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 4.dp,
                                    pressedElevation = 8.dp
                                )
                            ) {
                                AsyncImage(
                                    model = ImageRequest
                                        .Builder(LocalContext.current)
                                        .data(post?.urlLogo)
                                        .crossfade(true)
                                        .crossfade(1000)
                                        .placeholder(R.drawable.ic_business_100)
                                        .error(mtrl_ic_error)
                                        .build(),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(4.dp))
                                )
                            }

                            Text(
                                text = post?.entrepriseName ?: "",
                                style = GWTypography.h6,
                                color = Gunmetal
                            )

                        }

                        Text(
                            text = "Il y a 3 jours",
                            style = GWTypography.body2,
                            color = GWpalette.CoolGrey
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .height(44.dp),
            ) {
                TabButton(
                    "A propos",
                    { selectedTab = 1 },
                    selected = selectedTab,
                    myCurrent = 1,
                    modifier = Modifier.weight(1f)
                )
                TabButton(
                    "Compétences",
                    { selectedTab = 2 },
                    selected = selectedTab,
                    myCurrent = 2,
                    modifier = Modifier.weight(1f)
                )
                TabButton(
                    "Commentaires",
                    { selectedTab = 3 },
                    selected = selectedTab,
                    myCurrent = 3,
                    modifier = Modifier.weight(1f)
                )
            }

            when (selectedTab) {
                1 -> About(post)
                2 -> Skills(post = post, modifier = Modifier.weight(1f))
                3 -> CardReview(review = review)
            }
        }


/*
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround,
                        ) {
                            Text(
                                text = post?.domaine ?: "",
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                                style = GWTypography.body2,
                                color = Color.White,
                                modifier = Modifier
                                    .padding(end = 5.dp)
                                    .weight(1f)
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(TailwindCSSColor.Pink900)
                                    .padding(5.dp)
                            )

                            Text(
                                text = post?.typeDEmploi ?: "",
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                                style = GWTypography.body2,
                                color = Color.White,
                                modifier = Modifier
                                    .padding(horizontal = 5.dp)
                                    .weight(1f)
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(TailwindCSSColor.Pink900)
                                    .padding(5.dp)
                            )

                            Text(
                                text = post?.experience ?: "",
                                textAlign = TextAlign.Center,
                                style = GWTypography.body2,
                                color = Color.White,
                                modifier = Modifier
                                    .padding(start = 5.dp)
                                    .weight(1f)
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(TailwindCSSColor.Pink900)
                                    .padding(5.dp)
                            )
                        }

                    }
*/
    }
}

@Composable
private fun About(post: CompteEntreprise.Post?) {
    LazyColumn {
        item {
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(8.dp),
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Description",
                        style = GWTypography.h6,
                        color = Gunmetal
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = post?.descriptionEmploi ?: "",
                        style = GWTypography.body1.copy(fontWeight = FontWeight.Medium),
                        color = Gunmetal,
                    )

                }
            }

        }
        item {
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(8.dp),
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Responsabilités",
                        style = GWTypography.h6,
                        color = Gunmetal
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    post?.responsabilites?.forEach { responsibility ->
                        Text(
                            text = "- $responsibility",
                            style = GWTypography.body1.copy(fontWeight = FontWeight.Medium),
                            color = Gunmetal,
                            modifier = Modifier.padding(bottom = 7.dp)
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun TabButton(text: String, select: () -> Unit, selected: Int, myCurrent: Int, modifier: Modifier) {
    Button(
        onClick = { select() },
        shape = RoundedCornerShape(20.dp),
        modifier = modifier.fillMaxHeight(),
        elevation = null,
        colors = if (selected == myCurrent) ButtonDefaults.buttonColors(
            containerColor = Gunmetal,
            contentColor = Color.White
        ) else {
            ButtonDefaults.buttonColors(
                containerColor = Color.LightGray,
                contentColor = Gunmetal
            )
        }
    ) { Text(text, style = GWTypography.body1.copy(fontWeight = FontWeight.Medium)) }
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

//@Preview
@Composable
fun CardReviewPreview() {
    CardReview(
        review = CompteEntreprise.Post.Review(
            urlPhoto = R.drawable.painted_paul.toString(),
            reviewerName = "Morriss Morrisson",
            reviewCotent = " 8evhp9 v98hvh n9phavspaos;bvx zxbósd'zlz.n zx szh'xc.u sf 9 GSVB " +
                    "WS SBVYGSCEAIVU;W EV WEVG7WPVE  p9sgvb acbqa8 cqwertyu",
            date = "Il y a 2 jours"
        )
    )
}


/*
@Preview(name = "PostDetailsScreen", showSystemUi = true)
@Composable
private fun PreviewPostDetailsScreen() {
    PostDetailsScreen(
        CompteEntreprise.Post(
            postName = "Developpeur Kotlin",
            typeDEmploi = "Temps Plein",
            adresse = "23 Rue des Légendes",
            ville = "Sardesville",
            province = "Haut-Ogooué",
            experience = "Junior",
            domaine = "IT",
            actif = true,
            salaire = "2000000",
            entrepriseName = "Sardes Corp.",
            descriptionEmploi = "xertcyvcbu wfwgvn uvvuhsv wsn shudv9 s9vdpn snpusvbo sp " +
                    "svib ubsv psvpsabv svpvb spv sbvsbdsuv  spuhvsugv s vb sidvb s zpavpa v" +
                    " pvbnjzk xni uiv pv [hvu vdvjbidvshib" +
                    "qwertyuiop wetyuiop qwetyuiop qwtyuiop qwrtyuiop qwertyuiop qwertyuiop qwtyuiop" +
                    "asdfghjkl asfghjkl asdfghjkl aghjkl zxcvbnm asdfghjkl aqwerthn xdtyu iknbv dtyu k" +
                    " dvb njy trfv bnj ytrv bnmklk jbvcx serty uiknb vca ertyui kn g ewqasxcv bnm, lp ln bvd w.",
            responsabilites = listOf(
                "qwrtyuiop qwrtyuiop qwetyuiop qwetyuiop qwtyuiop qwtyuio qwrtyuiop qwetyuiop qwrtyuio",
                "sjbvnoerv qwrtyuiop qwetyuiop qwetyuiop qwtyuiop qwtyuio qwrtyuiop qwetyuiop qwrtyuio",
                "hvbwuvbwe qwrtyuiop qwetyuiop qwetyuiop qwtyuiop qwtyuio qwrtyuiop qwetyuiop qwrtyuio",
                "onvuwueef qwrtyuiop qwetyuiop qwetyuiop qwtyuiop qwtyuio qwrtyuiop qwetyuiop qwrtyuio",
                "piowbwbbe qwrtyuiop qwetyuiop qwetyuiop qwtyuiop qwtyuio qwrtyuiop qwetyuiop qwrtyuio",
            ),
            totalApplicants = 15
        ),

        reviewer = CompteEntreprise.Post.Reviewer(
            urlPhoto = R.drawable.painted_paul.toString(),
            name = "Morriss Morrisson",
            review = " 8evhp9 v98hvh n9phavspaos;bvx zxbósd'zlz.n zx szh'xc.u sf 9 GSVB " +
                    "WS SBVYGSCEAIVU;W EV WEVG7WPVE  p9sgvb acbqa8 cqwertyu",
            date = "Il y a 2 jours"
        )
    )
}*/
