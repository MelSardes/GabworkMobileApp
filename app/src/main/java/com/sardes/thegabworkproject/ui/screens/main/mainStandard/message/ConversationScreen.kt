package com.sardes.thegabworkproject.ui.screens.main.mainStandard.message

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.sardes.thegabworkproject.ui.theme.GWTypography

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun ConversationScreen(
    messagesStandardViewModel: MessagesStandardViewModel?,
    conversationId: String,

) {
    Box(contentAlignment = Alignment.Center) {
        Text(text = "ConversationScreen", style = GWTypography.h2)
    }
}

@Preview(name = "ConversationScreen")
@Composable
private fun PreviewConversationScreen() {
    ConversationScreen( null, "")
}