package com.sardes.thegabworkproject.ui.screens.main.mainStandard.message.conversation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sardes.thegabworkproject.R.drawable
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.message.StandardMessagesUiState
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette
import kiwi.orbit.compose.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun ConversationTopBar(messagesUiState: StandardMessagesUiState?) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "${messagesUiState?.selectedConversation?.receiverName}",
                color = GWpalette.EauBlue,
                style = GWTypography.h6
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(GWpalette.Gunmetal),
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_orbit_menu_hamburger),
                    contentDescription = null,
                    tint = GWpalette.EauBlue
                )
            }
        },

        actions = {
            IconButton(
                onClick = { /*TODO*/ }) {
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(messagesUiState?.selectedConversation?.receiverUrlPhoto)
                        .crossfade(true)
                        .crossfade(1000)
                        .placeholder(drawable.ic_image)
                        .error(drawable.ic_broken_image)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

    )
}

