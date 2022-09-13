package com.sardes.thegabworkproject.ui.screens.main.mainStandard.message

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.components.cards.ConversationCard
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.home.HomeStandardViewModel
import com.sardes.thegabworkproject.ui.theme.GWpalette.Gunmetal
import kiwi.orbit.compose.ui.controls.Scaffold

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun MessagesStandardScreen(
    MessageStandardViewModel: MessagesStandardViewModel?,
    homeStandardViewModel: HomeStandardViewModel?,
    onCardClick: (conversationId: String)-> Unit
) {

    val uiState = MessageStandardViewModel?.standardMessagesUiState
    val homeUiStateStandard = homeStandardViewModel?.homeStandardUiState

    Scaffold(
        backgroundColor = Gunmetal,
        modifier = Modifier.clip(RoundedCornerShape(24.dp)),
        topBar = { MessageTopBar(homeUiStateStandard) },
    ) {
        LazyColumn {
            uiState?.conversationsList?.data?.forEach {
                item {
                    ConversationCard(
                        conversation = it,
                        onCardClick = { onCardClick(it.conversationId!!) }
                    )
                }
            }
        }
    }
}

@Preview(name = "MessagesScreen")
@Composable
private fun PreviewMessagesScreen() {
    MessagesStandardScreen(null, null){}
}