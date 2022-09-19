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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.repository.ressources.Ressources
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.components.cards.ConversationCard
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.home.HomeStandardViewModel
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette.DarkLiver
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

    LaunchedEffect(Unit) {
        messageViewModel?.loadAllConversations()
    }

    Scaffold(
        backgroundColor = Color.White,
        topBar = { MessageTopBar(homeUiStateStandard) },
    ) {
        LazyColumn(
            Modifier
                .padding(it)
                .fillMaxSize()
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
                    item {
                        Text(
                            text = "Entreprises",
                            style = GWTypography.h6,
                            color = DarkLiver,
                            textAlign = TextAlign.Start,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.padding(top = 20.dp, start = 20.dp)
                        )
                    }

                    uiState.conversationsList.data?.forEach { conversation ->
                        if (conversation.receiverAccountType.equals("Entreprise")) {
                            item {
                                ConversationCard(
                                    conversation = conversation,
                                    onCardClick = { onCardClick(conversation.conversationId!!) },
                                    viewModel = messageViewModel
                                )
                            }
                        }
                    }

                    item {
                        Text(
                            text = "Personnes",
                            style = GWTypography.h6,
                            color = DarkLiver,
                            textAlign = TextAlign.Start,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.padding(top = 20.dp, start = 20.dp)
                        )
                    }

                    uiState.conversationsList.data?.forEach { conversation ->
                        if (conversation.receiverAccountType.equals("Standard")) {
                            item {
                                ConversationCard(
                                    conversation = conversation,
                                    onCardClick = { onCardClick(conversation.conversationId!!) },
                                    viewModel = messageViewModel
                                )
                            }
                        }
                    }

                }

                else -> {
                    item {
                        androidx.compose.material3.Text(
                            text = uiState?.conversationsList?.throwable?.localizedMessage
                                ?: "OOPS!\nUne erreur s'est produite",
                            style = GWTypography.h4,
                            color = Color.Red,
                            modifier = Modifier.wrapContentSize(Alignment.Center)
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