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
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.LocalWindowInsets
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.ui.theme.AppBarCollapsedHeight
import com.sardes.thegabworkproject.ui.theme.AppBarExpendedHeight
import com.sardes.thegabworkproject.ui.theme.BlueFlag
import kotlin.math.max
import kotlin.math.min

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun ProfileEntrepriseConceptScreen(
    modifier: Modifier = Modifier, entreprise: CompteEntreprise
) {
    val scrollState = rememberLazyListState()

    Box {
        Content(entreprise, scrollState)
        ParallaxToolbar(entreprise, scrollState)
    }
}




@SuppressLint("FrequentlyChangedStateReadInComposition", "MaterialDesignInsteadOrbitDesign")
@Composable
fun ParallaxToolbar(entreprise: CompteEntreprise, scrollState: LazyListState) {
    val imageHeight = AppBarExpendedHeight - AppBarCollapsedHeight

    val maxOffset =
        with(LocalDensity.current) { imageHeight.roundToPx() } - LocalWindowInsets.current.systemBars.layoutInsets.top

    val offset = min(scrollState.firstVisibleItemScrollOffset, maxOffset)

    val offsetProgress = max(0f, offset * 3f - 2f * maxOffset) / maxOffset

    TopAppBar(
        contentPadding = PaddingValues(),
        backgroundColor = White,
        modifier = Modifier
            .height(
                AppBarExpendedHeight
            )
            .offset { IntOffset(x = 0, y = -offset) },
        elevation = if (offset == maxOffset) 4.dp else 0.dp
    ) {
        Column {
            Box(
                Modifier
                    .height(imageHeight)
                    .graphicsLayer {
                        alpha = 1f - offsetProgress
                    }) {

                Image(
                    painter = painterResource(id = R.drawable.perroquet),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colorStops = arrayOf(
                                    Pair(0.4f, Transparent),
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
                        entreprise.secteurDActivite,
                        fontWeight = FontWeight.Medium,
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
                    entreprise.nomEntreprise,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
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

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun CircularButton(
    @DrawableRes iconResouce: Int,
    color: Color = Gray,
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = White, contentColor = color),
        elevation = elevation,
        modifier = Modifier
            .width(38.dp)
            .height(38.dp)
    ) {
        Icon(painterResource(id = iconResouce), null)
    }
}

@Composable
fun Content(entreprise: CompteEntreprise, scrollState: LazyListState) {
    LazyColumn(contentPadding = PaddingValues(top = AppBarExpendedHeight), state = scrollState) {
        item {
            Description(entreprise)
            BasicInfo(entreprise)
            Address(entreprise)
//            IngredientsHeader()
//            ShoppingListButton()
//            Reviews(entreprise)
//            Images()
        }
    }
}

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun Address(entreprise: CompteEntreprise) {
    Text(
        text = entreprise.adressEntreprise,
        fontWeight = FontWeight.Medium,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
        maxLines = 3,
        softWrap = true,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun Images() {
    Row(Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Image(
            painter = painterResource(id = R.drawable.gabwork_on_start_illustration_medium),
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(10.dp))
        )
        Spacer(modifier = Modifier.weight(0.1f))
        Image(
            painter = painterResource(id = R.drawable.perroquet),
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(10.dp))
        )
    }
}

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun Reviews(entreprise: CompteEntreprise) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = "Stats", fontWeight = FontWeight.Bold)
            Row {
                Column {
                    Text("27", color = Black)
                    Text("Posts publiés", color = DarkGray)

                }
                Column {
                    Text("17", color = Black)
                    Text("personnes rescrutées", color = DarkGray)

                }
                Column {
                    Text("8", color = Black)
                    Text("Stagiaires rescrutées", color = DarkGray)

                }
            }
        }
        Button(
            onClick = { },
            elevation = null,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Transparent,
                contentColor = BlueFlag
            )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Tout voir")
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = null
                )
            }
        }
    }
}

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun ShoppingListButton() {
    Button(
        onClick = {},
        elevation = null,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = LightGray,
            contentColor = Black
        ), modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Voir plus sur l'entreprise", modifier = Modifier.padding(8.dp))
    }

}

/*
@Composable
fun IngredientsList(entreprise: CompteEntreprise) {
    EasyGrid(nColumns = 3, items = entreprise.ingredients) {
        IngredientCard(it.image, it.title, it.subtitle, Modifier)
    }
}

@Composable
fun <T> EasyGrid(nColumns: Int, items: List<T>, content: @Composable (T) -> Unit) {
    Column(Modifier.padding(16.dp)) {
        for (i in items.indices step nColumns) {
            Row {
                for (j in 0 until nColumns) {
                    if (i + j < items.size) {
                        Box(
                            contentAlignment = Alignment.TopCenter,
                            modifier = Modifier.weight(1f)
                        ) {
                            content(items[i + j])
                        }
                    } else {
                        Spacer(Modifier.weight(1f, fill = true))
                    }
                }
            }
        }
    }
}
*/


/*
@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun IngredientCard(
    @DrawableRes iconResource: Int,
    title: String,
    subtitle: String,
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(bottom = 16.dp)
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = 0.dp,
            backgroundColor = LightGray,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .padding(bottom = 8.dp)
        ) {
            Image(
                painter = painterResource(id = iconResource),
                contentDescription = null,
                modifier = Modifier.padding(16.dp)
            )
        }
        Text(text = title, modifier = Modifier.width(100.dp), fontSize = 14.sp, fontWeight = FontWeight.Medium)
        Text(text = subtitle, color = DarkGray, modifier = Modifier.width(100.dp), fontSize = 14.sp)
    }
}
*/

@Composable
fun IngredientsHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(LightGray)
            .fillMaxWidth()
            .height(44.dp)
    ) {
        TabButton("Informations", true, Modifier.weight(1f))
        TabButton("........", false, Modifier.weight(1f))
        TabButton("********", false, Modifier.weight(1f))
    }
}

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun TabButton(text: String, active: Boolean, modifier: Modifier) {
    Button(
        onClick = {},
        shape = RoundedCornerShape(10.dp),
        modifier = modifier.fillMaxHeight(),
        elevation = null,
        colors = if (active) ButtonDefaults.buttonColors(
            backgroundColor = BlueFlag,
            contentColor = White
        ) else ButtonDefaults.buttonColors(
            backgroundColor = LightGray,
            contentColor = DarkGray
        )
    ) {
        Text(text)
    }
}

/*
@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun ServingCalculator() {
    var value by remember { mutableStateOf(6) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(LightGray)
            .padding(horizontal = 16.dp)
    ) {

        Text(text = "Serving", Modifier.weight(1f), fontWeight = FontWeight.Medium)
        CircularButton(iconResouce = R.drawable.ic_minus, elevation = null, color = BlueFlag) { value-- }
        Text(text = "$value", Modifier.padding(16.dp), fontWeight = FontWeight.Medium)
        CircularButton(iconResouce = R.drawable.ic_plus, elevation = null, color = BlueFlag) { value++ }
    }
}
*/

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun Description(entreprise: CompteEntreprise) {
    Text(
        text = entreprise.descriptionEntreprise,
        fontWeight = FontWeight.Medium,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
        maxLines = 3,
        softWrap = true,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun BasicInfo(entreprise: CompteEntreprise) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            InfoColumn(R.drawable.ic_post, "27", "posts")
            InfoColumn(R.drawable.ic_people, "30", "recrutements")
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)

        ) {
            InfoColumn(R.drawable.ic_student, "8", "étudiants")
            InfoColumn(R.drawable.ic_web, entreprise.urlLogoEntreprise!!, "")
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)

        ) {
            InfoColumn(R.drawable.ic_industry, entreprise.secteurDActivite, "")
            InfoColumn(R.drawable.ic_employees, "1-50 employés", "")
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
        Text(text = text1, fontWeight = FontWeight.Bold)
        Text(text = text2, fontWeight = FontWeight.Bold)
    }
}


@Preview(showSystemUi = true, device = "id:TECNO POP3")
@Preview(showSystemUi = true, device = "spec:width=1280dp,height=800dp,dpi=480")
@Preview(showSystemUi = true, device = "id:pixel")
@Preview(showSystemUi = true, device = "id:pixel_3")
@Composable
fun ScreenConcept() {
    ProfileEntrepriseConceptScreen(entreprise = CompteEntreprise(
        nomEntreprise = "SARDES CORP.",
        secteurDActivite = "Nouvelles Technologies",
        descriptionEntreprise = "Lorem ipsum jwhvjv jfwejfwe9fwe8fwe fuwe9fiwe8f 9fu fu9fi wefuwe uf9fu wf fuf uf09ufh9 fuwugwg uwv w8uv suv8sjv vjr 8vrv hvj r8ver9ue",
        ville = "Sardesville",
        urlLogoEntreprise = "https://www.sardes-corp.com",
        adressEntreprise = "42 Boulvard Sardes, Sardesville, Estuaire, Gabon"
    )
    )

}