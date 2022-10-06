package com.sardes.thegabworkproject.ui.screens.components

//import android.annotation.SuppressLint
//import androidx.compose.animation.core.animateDpAsState
//import androidx.compose.animation.core.animateFloatAsState
//import androidx.compose.foundation.*
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.Icon
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Text
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material.icons.filled.MoreVert
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.alpha
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.ImageBitmap
//import androidx.compose.ui.graphics.asAndroidBitmap
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.imageResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.sardes.thegabworkproject.R
//import com.sardes.thegabworkproject.data.models.ActivityArea
//import com.sardes.thegabworkproject.data.provider.ActivityAreaDataProvider.secteurs
//import com.sardes.thegabworkproject.data.provider.GabworkDataProvider
//import com.sardes.thegabworkproject.ui.screens.main.SearchViewModel
//import com.sardes.thegabworkproject.ui.screens.main.mainStandard.components.cards.HorizontalSearchCard
//import com.sardes.thegabworkproject.ui.theme.GWTypography
//import com.sardes.thegabworkproject.ui.theme.extensions.generateDominantColorState
//import com.sardes.thegabworkproject.ui.theme.modifiers.horizontalGradientBackground
//import com.sardes.thegabworkproject.ui.theme.modifiers.verticalGradientBackground

/*
@Composable
fun ActivityAreaDetailScreen(
//    area: ActivityArea,
    index: Int,
    viewModel: SearchViewModel?,
    onPostClick: (id: String) -> Unit
) {

    val uiState = viewModel?.searchUiState

    val area = secteurs[index]

    val scrollState = rememberScrollState(0)
    val context = LocalContext.current
    val image = ImageBitmap.imageResource(context.resources, id = area.imageId).asAndroidBitmap()
    val swatch = remember(area.id) { image.generateDominantColorState() }
    val dominantColors = listOf(Color(swatch.rgb), MaterialTheme.colors.surface)
    val dominantGradient = remember { dominantColors }
    val surfaceGradient = GabworkDataProvider
        .gabworkSurfaceGradient(isSystemInDarkTheme()).asReversed()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalGradientBackground(dominantGradient)
    ) {
        BoxTopSection(area = area, scrollState = scrollState)
        TopSectionOverlay(scrollState = scrollState)
        BottomScrollableContent(
            scrollState = scrollState,
            surfaceGradient = surfaceGradient,
            uiState = uiState,
            domain = area.name,
            onPostClick = onPostClick
        )
        AnimatedToolBar(area, scrollState, surfaceGradient)
    }
}
*/


/*
@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun BoxTopSection(area: ActivityArea, scrollState: ScrollState) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
        )
        //animate as scroll value increase but not fast so divide by random number 50
        val dynamicValue =
            if (250.dp - Dp(scrollState.value / 50f) < 10.dp) 10.dp //prevent going 0 cause crash
            else 250.dp - Dp(scrollState.value / 20f)
        val animateImageSize = animateDpAsState(dynamicValue).value
        Image(
            painter = painterResource(id = area.imageId),
            contentDescription = null,
            modifier = Modifier
                .size(animateImageSize)
                .padding(8.dp)
        )
        Text(
            text = area.name,
            style = GWTypography.h5.copy(fontWeight = FontWeight.ExtraBold),
            modifier = Modifier.padding(8.dp),
            color = MaterialTheme.colors.onSurface
        )
        Text(
            text = "FOLLOWING",
            color = MaterialTheme.colors.onSurface,
            style = GWTypography.h6.copy(fontSize = 12.sp),
            modifier = Modifier
                .padding(4.dp)
                .border(
                    border = BorderStroke(2.dp, MaterialTheme.colors.primaryVariant),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(vertical = 4.dp, horizontal = 24.dp)
        )


        Text(
            text = area.description,
            style = GWTypography.subtitle2,
            modifier = Modifier.padding(4.dp)
        )
    }
}
*/


/*
@Composable
fun TopSectionOverlay(scrollState: ScrollState) {
    //slowly increase alpha till it reaches 1
    val dynamicAlpha = ((scrollState.value + 0.00f) / 1000).coerceIn(0f, 1f)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(
                MaterialTheme.colors.surface.copy(
                    alpha = animateFloatAsState(dynamicAlpha).value
                )
            )
    )
}
*/


/*
@Composable
fun BottomScrollableContent(
    scrollState: ScrollState,
    surfaceGradient: List<Color>,
    uiState: SearchViewModel.SearchUiState?,
    domain: String,
    onPostClick: (id: String) -> Unit
) {
    Column(modifier = Modifier.verticalScroll(state = scrollState)) {
        Spacer(modifier = Modifier.height(480.dp))
        Column(modifier = Modifier.horizontalGradientBackground(surfaceGradient)) {
            PostListScrollingSection(uiState = uiState, domain = domain, onPostClick = onPostClick)
        }
        Spacer(modifier = Modifier.height(50.dp))
    }
}
*/

/*
@Composable
fun PostListScrollingSection(
    uiState: SearchViewModel.SearchUiState?,
    domain: String,
    onPostClick: (id: String) -> Unit
) {

    val postList = uiState?.allPosts?.data?.filter { post ->
        post.domain
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
                domain
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

    postList.forEach {
        HorizontalSearchCard(post = it) { onPostClick.invoke(it.postId) }
    }
}
*/


/*
@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun AnimatedToolBar(area: ActivityArea, scrollState: ScrollState, surfaceGradient: List<Color>) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .horizontalGradientBackground(
                if (Dp(scrollState.value.toFloat()) < 1080.dp)
                    listOf(Color.Transparent, Color.Transparent) else surfaceGradient
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack, tint = MaterialTheme.colors.onSurface,
            contentDescription = stringResource(id = R.string.cd_back)
        )
        Text(
            text = area.name,
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier
                .padding(16.dp)
                .alpha(((scrollState.value + 0.001f) / 1000).coerceIn(0f, 1f))
        )
        Icon(
            imageVector = Icons.Default.MoreVert, tint = MaterialTheme.colors.onSurface,
            contentDescription = null
        )
    }
}
*/


