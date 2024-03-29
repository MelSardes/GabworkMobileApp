package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.search.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.data.models.ActivityArea
import com.sardes.thegabworkproject.data.provider.ActivityAreaDataProvider
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.extensions.generateDominantColorState
import com.sardes.thegabworkproject.ui.theme.modifiers.horizontalGradientBackground
import com.sardes.thegabworkproject.ui.verticalgrid.VerticalGrid

@Composable
fun GabworkSearchGrid(onClick: (index: Int) -> Unit) {
    val items = remember { ActivityAreaDataProvider.secteurs }
    //This is not Lazy at the moment Soon we will have LazyLayout coming then will
    //Update it so we have better performance
    VerticalGrid {
        items.forEach {
            SearchGridItem(it, onClick = {onClick.invoke(it.id)})
        }
    }
}

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun SearchGridItem(area: ActivityArea, onClick: () -> Unit) {
    val context = LocalContext.current
    val imageBitmap = ImageBitmap.imageResource(context.resources, area.imageId).asAndroidBitmap()
    val swatch = remember(area.id) { imageBitmap.generateDominantColorState() }
    val dominantGradient =
        remember { listOf(Color(swatch.rgb), Color(swatch.rgb).copy(alpha = 0.6f)) }
    Row(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = { onClick.invoke()
                //Disclaimer: We should pass event top level and there should startActivity
//                context.startActivity(SpotifyDetailActivity.newIntent(context, album))
            })
            .height(100.dp)
            .clip(RoundedCornerShape(12.dp))
            .horizontalGradientBackground(dominantGradient),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = area.name,
            style = GWTypography.body1,
            modifier = Modifier.padding(start = 8.dp, top = 8.dp).fillMaxWidth(0.6f)
        )
        Image(
            painter = painterResource(id = area.imageId),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .size(70.dp)
                .align(Alignment.Bottom)
                .graphicsLayer(translationX = 25f, rotationZ = 30f, shadowElevation = 16f)
        )
    }
}
