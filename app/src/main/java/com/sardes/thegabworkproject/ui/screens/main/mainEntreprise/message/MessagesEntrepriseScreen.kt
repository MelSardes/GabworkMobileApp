package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.message

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.repository.ressources.Ressources
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.home.HomeEntrepriseViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.message.components.ConversationEntrepriseCard
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette
import kiwi.orbit.compose.ui.controls.Scaffold
import kiwi.orbit.compose.ui.controls.Text as OrbitText

@Composable
fun MessagesEntrepriseScreen(
    messageViewModel: MessagesEntrepriseViewModel?,
    homeViewModel: HomeEntrepriseViewModel?,
    onConversationClick: (conversationId: String) -> Unit
) {

    val uiState = messageViewModel?.entrepriseMessagesUiState
    val homeUiState = homeViewModel?.homeEntrepriseUiState

    LaunchedEffect(Unit) {
        messageViewModel?.loadAllConversations()
    }




    Scaffold(
        backgroundColor = Color.White,
        topBar = { MessageTopBarEntreprise(homeUiState) },
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
                            OrbitText(
                                text = "CHARGEMENT DE VOS CONVERSATIONS...",
                                style = GWTypography.h4,
                            )
                        }
                    }
                }

                is Ressources.Success -> {
                    item {
                        OrbitText(
                            text = "Entreprises",
                            style = GWTypography.h6,
                            color = GWpalette.DarkLiver,
                            textAlign = TextAlign.Start,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.padding(top = 20.dp, start = 20.dp)
                        )
                    }

                    uiState.conversationsList.data?.forEach { conversation ->
                        if (conversation.receiverAccountType.equals("Entreprise")) {
                            item {
                                ConversationEntrepriseCard(
                                    conversation = conversation,
                                    onCardClick = { onConversationClick(conversation.conversationId!!) },
                                    viewModel = messageViewModel
                                )
                            }
                        }
                    }

                    item {
                        OrbitText(
                            text = "Personnes",
                            style = GWTypography.h6,
                            color = GWpalette.DarkLiver,
                            textAlign = TextAlign.Start,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.padding(top = 20.dp, start = 20.dp)
                        )
                    }

                    uiState.conversationsList.data?.forEach { conversation ->
                        if (conversation.receiverAccountType.equals("Standard")) {
                            item {
                                ConversationEntrepriseCard(
                                    conversation = conversation,
                                    onCardClick = { onConversationClick(conversation.conversationId!!) },
                                    viewModel = messageViewModel
                                )
                            }
                        }
                    }

                }

                else -> {
                    item {
                        OrbitText(
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


/*
@Composable
@Preview
private fun MessagesScreenPreview() {
    MessagesEntrepriseScreen(null)
}

*/
