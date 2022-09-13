package com.sardes.thegabworkproject.ui.screens.main.mainStandard.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.data.models.Conversation
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette
import kiwi.orbit.compose.ui.controls.Card as OrbitCard

@Composable
fun ConversationCard(
    conversation: Conversation,
    onCardClick: () -> Unit
) {
    OrbitCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp), elevation = 0.dp,
        onClick = {onCardClick.invoke()}
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
                    .height(80.dp)
                    .fillMaxWidth(0.2f)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(conversation.receiverUrlPhoto)
                        .crossfade(true)
                        .placeholder(R.drawable.ic_account_100)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
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
                        text = "${conversation.lastMessageDate}",
                        style = GWTypography.body2,
                        color = GWpalette.EauBlue,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.weight(0.2f))

                Text(
                    text = "${conversation.lastMessageContent}",
                    style = GWTypography.body1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
            }

        }
    }
}

@Preview(name = "ConversationCard")
@Composable
private fun PreviewConversationCard() {
    ConversationCard(
        conversation = Conversation(
            receiverName = "Mel Sardes",
            lastMessageContent = "Mel Sardes is about to finish his work",
            lastMessageDate = "Jeudi, 22h52",
            receiverUrlPhoto = "https://firebasestorage.googleapis.com/v0/b/thegabworkprojecttest.appspot.com/o/userProfile%2Fstandard%2FfLDAh5ubSVRQdc9jd00MRMRC2Jl1__profile.jpg?alt=media&token=ea8fc984-0f44-4b36-83dc-2e09966bfaf1"
        )
    ){}
}