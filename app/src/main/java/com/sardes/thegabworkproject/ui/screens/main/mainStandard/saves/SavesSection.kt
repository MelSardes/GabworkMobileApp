package com.sardes.thegabworkproject.ui.screens.main.mainStandard.saves

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.repository.ressources.Ressources
import com.sardes.thegabworkproject.ui.composition.AnimatedShimmer
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.components.cards.BookmarkCard
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.home.HomeStandardUiState
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.home.HomeStandardViewModel
import com.sardes.thegabworkproject.ui.theme.GWTypography

@Composable
fun SavesSection(
    viewModel: HomeStandardViewModel?,
    uiState: HomeStandardUiState?,
) {

    LaunchedEffect(Unit) {
        viewModel?.loadAllBookmarks()
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        when (uiState?.bookmarks) {
            is Ressources.Loading ->
                LazyColumn(modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 10.dp)
                ) {
                    items(count = 4) {
                        Spacer(modifier = Modifier.height(20.dp))
                        AnimatedShimmer()
                    }
                }
            is Ressources.Success -> LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                item {
                    Text(
                        text =
                        if(uiState.bookmarks.data.isNullOrEmpty())
                         "${uiState.bookmarks.data?.size} posts sauvegardés"
                        else
                            "C'est encore vide içi",
                        style = GWTypography.h4
                    )
                }

                uiState.bookmarks.data?.forEach {
                    item {
                        Spacer(modifier = Modifier.height(20.dp))
                        BookmarkCard(bookmark = it) {
                        }
                    }
                }
            }
            else -> {
                Text(text = "Erreur")
            }
        }
    }
}

@Preview(name = "SavesSection")
@Composable
private fun PreviewSavesSection() {
    SavesSection(null, null)
}