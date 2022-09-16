package com.sardes.thegabworkproject.ui.screens.main.mainStandard.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.data.models.Conversation
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.message.MessagesStandardViewModel
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette
import kiwi.orbit.compose.ui.controls.Card as OrbitCard

@Composable
fun ConversationCard(
    conversation: Conversation,
    viewModel: MessagesStandardViewModel?,
    onCardClick: () -> Unit
) {
    OrbitCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp), elevation = 0.dp,
        onClick = { onCardClick.invoke() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Card(
                modifier = Modifier
                    .height(70.dp)
                    .width(70.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(conversation.receiverUrlPhoto)
                        .crossfade(true)
                        .placeholder(R.drawable.ic_account_100)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 20.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.Start
            ) {
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    Text(text = "${conversation.receiverName}", style = GWTypography.subtitle1)
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "${conversation.lastMessageDate?.toDate()}",
                        style = GWTypography.body2,
                        color = GWpalette.EauBlue,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.weight(0.2f))

                Text(
                    text =
                    when (conversation.latMessageSender) {
                        viewModel?.userId ->
                            "Vous : ${conversation.lastMessageContent}"
                        else ->
                            "${conversation.lastMessageContent}"
                    },
                    style = GWTypography.body1.copy(fontWeight = FontWeight.Normal),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
            }

        }
    }
}
