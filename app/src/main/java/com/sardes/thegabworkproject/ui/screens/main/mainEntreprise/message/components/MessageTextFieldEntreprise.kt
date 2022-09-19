package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.message.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.message.EntrepriseMessagesUiState
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.message.MessagesEntrepriseViewModel
import com.sardes.thegabworkproject.ui.theme.GWpalette.CoolGrey
import com.sardes.thegabworkproject.ui.theme.GWpalette.EauBlue
import com.sardes.thegabworkproject.ui.theme.GWpalette.LackCoral
import kiwi.orbit.compose.ui.R
import kiwi.orbit.compose.ui.controls.Card
import kiwi.orbit.compose.ui.controls.TextField

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun MessageTextFieldEntreprise(
    viewModel: MessagesEntrepriseViewModel?,
    uiState: EntrepriseMessagesUiState?,
) {

    Card(
        backgroundColor = CoolGrey,
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min), elevation = 20.dp
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_orbit_attachment),
                    contentDescription = null,
                    tint = EauBlue
                )
            }

            TextField(
                modifier = Modifier.weight(1f),
                value = uiState?.messageContent ?: "",
                singleLine = false,
                maxLines = 10,
                onValueChange = { viewModel?.onMessageContentChange(it) },
                trailingIcon = {
                    IconButton(onClick = {viewModel?.addMessage()}) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_orbit_send),
                            contentDescription = null,
                            tint = LackCoral
                        )
                    }
                },
            )
        }
    }
}

