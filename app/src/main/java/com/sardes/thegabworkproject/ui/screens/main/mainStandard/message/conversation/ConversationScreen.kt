package com.sardes.thegabworkproject.ui.screens.main.mainStandard.message.conversation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.message.MessagesStandardViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.message.components.MessageCard
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.message.components.MessageTextField
import com.sardes.thegabworkproject.ui.theme.GWpalette.CoolGrey
import com.sardes.thegabworkproject.ui.theme.GWpalette.Gunmetal
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun ConversationScreen(
    viewModel: MessagesStandardViewModel?,
    conversationId: String,
) {

    LaunchedEffect(Unit) {
        viewModel?.getConversation(conversationId)
        delay(2000)
        viewModel?.loadAllMessages(conversationId)
    }

    val uiState = viewModel?.standardMessagesUiState


    Scaffold(
        topBar = { ConversationTopBar(messagesUiState = uiState) },
        bottomBar = { MessageTextField(viewModel, uiState) }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(Gunmetal)
                .padding(it)
                .clip(RoundedCornerShape(24.dp))
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Gunmetal,
                            CoolGrey
                        )
                    )
                )
        ) {

            LazyColumn {
                uiState?.messagesList?.data?.forEach {
                    item {
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.Transparent,
                        ) {
                            when (it.senderUID) {
                                viewModel.userId -> MessageCard(
                                    message = it,
                                    modifier = Modifier
                                        .fillMaxWidth(0.7f)
                                        .align(alignment = Alignment.CenterEnd)
                                )

                                else -> MessageCard(
                                    message = it,
                                    shape = RoundedCornerShape(24.dp, 24.dp, 24.dp),
                                    background = Color.White,
                                    dateColor = CoolGrey,
                                    modifier = Modifier
                                        .fillMaxWidth(0.7f)
                                        .align(alignment = Alignment.CenterStart)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(name = "ConversationScreen")
@Composable
private fun PreviewConversationScreen() {
    ConversationScreen(null, "")
}