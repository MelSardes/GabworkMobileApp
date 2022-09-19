package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.message.conversation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.repository.ressources.Ressources
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.message.MessagesEntrepriseViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.message.components.MessageTextFieldEntreprise
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.message.components.MessageCard
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette.CoolGrey
import com.sardes.thegabworkproject.ui.theme.GWpalette.Gunmetal

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun EntrepriseConversationScreen(
    viewModel: MessagesEntrepriseViewModel?,
    conversationId: String,
) {
    val uiState = viewModel?.entrepriseMessagesUiState
    LaunchedEffect(Unit) {
        viewModel?.getUserAccountType(conversationId)
    }


    LaunchedEffect(uiState?.chatUserType?.account) {
        when (uiState?.chatUserType?.account) {
            "Standard" -> {
                viewModel.getStandardInformations(conversationId)
            }
            "Entreprise" -> {
                viewModel.getEntrepriseInformations(conversationId)
            }
            else -> {
                viewModel?.getUserAccountType(conversationId)
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel?.loadAllMessages(conversationId)
    }

    LaunchedEffect(Unit) {
        viewModel?.getUserInformations()
    }

    Scaffold(
        containerColor = Gunmetal,
        topBar = { ConversationTopBarEntreprise(messagesUiState = uiState) },
        bottomBar = { MessageTextFieldEntreprise(viewModel, uiState) }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            CoolGrey
                        )
                    )
                )
                .padding(it)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White)
        ) {

            LazyColumn {
                when (uiState?.messagesList) {
                    is Ressources.Loading -> {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .wrapContentSize(align = Alignment.Center),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .wrapContentSize(align = Alignment.Center)
                                )
                                androidx.compose.material.Text(
                                    text = "RÉCUPÉRATION DE LA CONVERSATION...",
                                    style = GWTypography.h4,
                                )
                            }
                        }
                    }

                    is Ressources.Success -> {
                        uiState.messagesList.data?.forEach {
                            item {
                                Spacer(modifier = Modifier.height(10.dp))
                                Surface(
                                    modifier = Modifier.fillMaxWidth(),
                                    color = Color.Transparent,
                                ) {
                                    when (it.senderUID) {
                                        viewModel.userId -> MessageCard(
                                            message = it,
                                            modifier = Modifier,
                                        )

                                        else -> MessageCard(
                                            message = it,
                                            shape = RoundedCornerShape(24.dp, 24.dp, 24.dp),
                                            background = Color.White,
                                            dateColor = CoolGrey,
                                            modifier = Modifier,
                                            contentAlignment = Alignment.Start
                                        )
                                    }
                                }
                            }
                        }
                    }

                    else -> {
                        item {
                            Text(
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
}
