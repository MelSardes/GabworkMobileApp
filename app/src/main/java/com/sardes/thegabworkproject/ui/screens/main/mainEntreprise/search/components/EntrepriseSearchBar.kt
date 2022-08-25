package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.search.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun EntrepriseSearchBar(
    modifier: Modifier = Modifier,
) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clip(RoundedCornerShape(4.dp)),
        backgroundColor = MaterialTheme.colors.onSurface
    ) {
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.padding(4.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = Color.LightGray,
                    contentDescription = null
                )
            },
            placeholder = {
                Text(
                    text = "Metier ou secteur d'activité",
                    color = MaterialTheme.colors.surface,
                    modifier = Modifier.padding(8.dp),
                    style = typography.h6.copy(fontSize = 14.sp),
                )
            }
        )
    }
}

@Preview(name = "SearchBar")
@Composable
private fun PreviewSearchBar() {
    EntrepriseSearchBar()
}