package com.sardes.thegabworkproject.ui.screens.main.mainStandard.message

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.repository.ressources.Ressources
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.components.cards.ConversationCard
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.home.HomeStandardViewModel
import com.sardes.thegabworkproject.ui.theme.GWTypography
import kiwi.orbit.compose.ui.controls.Scaffold

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun MessagesStandardScreen(
    messageViewModel: MessagesStandardViewModel?,
    homeViewModel: HomeStandardViewModel?,
    onCardClick: (conversationId: String) -> Unit
) {

    val uiState = messageViewModel?.standardMessagesUiState
    val homeUiStateStandard = homeViewModel?.homeStandardUiState

    LaunchedEffect(Unit){
        messageViewModel?.loadAllConversations()
    }

    Scaffold(
        backgroundColor = Color.White,
        topBar = { MessageTopBar(homeUiStateStandard) },
    ) {
        LazyColumn(
            Modifier
                .padding(it)
                .clip(RoundedCornerShape(24.dp))
        ) {
            when (uiState?.conversationsList) {
                is Ressources.Loading -> {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(align = Alignment.Center),
                            verticalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .wrapContentSize(align = Alignment.Center)
                            )
                            Text(
                                text = "CHARGEMENT DE VOS CONVERSATIONS...",
                                style = GWTypography.h4,
                            )
                        }
                    }
                }

                is Ressources.Success -> {
                    uiState.conversationsList.data?.forEach {
                        item {
                            ConversationCard(
                                conversation = it,
                                onCardClick = { onCardClick(it.conversationId!!) },
                                viewModel = messageViewModel
                            )
                        }
                    }
                }
                else -> {
                    item {
                        androidx.compose.material3.Text(
                            text = uiState?.conversationsList?.throwable?.localizedMessage
                                ?: "OOPS!\nUne erreur s'est produite",
                            style = GWTypography.h4,
                            color = Color.Red
                        )
                    }
                }
            }
        }
    }
}

@Preview(name = "MessagesScreen")
@Composable
private fun PreviewMessagesScreen() {
    MessagesStandardScreen(null, null) {}
}