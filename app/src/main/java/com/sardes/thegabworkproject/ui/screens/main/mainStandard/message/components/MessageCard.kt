package com.sardes.thegabworkproject.ui.screens.main.mainStandard.message.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.data.models.Conversation
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette.EauBlue
import com.sardes.thegabworkproject.ui.theme.GWpalette.Gunmetal
import com.sardes.thegabworkproject.ui.theme.TailwindCSSColor.Green500
import kiwi.orbit.compose.ui.controls.Card
import kiwi.orbit.compose.ui.controls.Text

@Composable
fun MessageCard(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(24.dp, 24.dp, 0.dp, 24.dp),
    background: Color = Green500,
    message: Conversation.Message,
    contentColor: Color = Gunmetal,
    dateColor: Color = EauBlue,
    contentAlignment: Alignment.Horizontal = Alignment.End
) {

    Card(
        modifier = modifier
            .fillMaxWidth(0.7f)
            .height(IntrinsicSize.Min)
            .padding(end = 3.dp),
        backgroundColor = background,
        elevation = 0.dp, shape = shape
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = contentAlignment
        ) {


            Text(
                text = message.content ?: "000",
                style = GWTypography.body1,
                modifier = Modifier.weight(1f),
                color = contentColor
            )
            Text(
                text = message.sentAt.toDate().toString(),
                style = GWTypography.body2,
                color = dateColor,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(name = "MessageCard", showSystemUi = false, showBackground = true)
@Composable
private fun PreviewMessageCard() {
    MessageCard(
        message = Conversation.Message(
            content = "qwertyuiop qwertyuiop qwertyuiop qwertyuiop qwrtyuiop qwertyuiop qwertyuiop qwetyuiop qwetyuiop qwetyuiop qwtyuiop qwrtyuiop[ qwetyuiop qwrtyuiopp qwrtyuiop qwrtyuiop qwrtyuio ",
        )
    )
}