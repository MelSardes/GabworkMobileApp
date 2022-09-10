package com.sardes.thegabworkproject.ui.composition

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun CustomListItem(
    text: String,
    modifier: Modifier = Modifier,
    oncClick: () -> Unit
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp, 4.dp)
            .shadow(2.dp, RoundedCornerShape(4.dp))
            .clip(RoundedCornerShape(4.dp))
            .clickable {
                // do things here.
            }
            .background(MaterialTheme.colors.surface)
            .padding(16.dp, 8.dp),
        text = text,
        textAlign = TextAlign.Center,
    )
}

@Preview(name = "CustomListItem")
@Composable
private fun PreviewCustomListItem() {
    CustomListItem("Mel SARDES"){}
}