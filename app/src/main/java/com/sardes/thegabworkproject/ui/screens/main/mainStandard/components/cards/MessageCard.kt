package com.sardes.thegabworkproject.ui.screens.main.mainStandard.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette.EauBlue
import com.sardes.thegabworkproject.ui.theme.GWpalette.Gunmetal
import com.sardes.thegabworkproject.ui.theme.TailwindCSSColor.Pink900
import kiwi.orbit.compose.ui.controls.Text

@Composable
fun MessageCard(
    modifier: Modifier = Modifier
) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(10.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Pink900)
            .padding(20.dp),

//        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = "q vaoeivs vbs v0ihe v[0ihaev [9wueavh[s vuusuliu vbs vivuzbp zbux izuxgizgz;wqui; cgz iu;uiapw ",
            style = GWTypography.body1.copy(fontWeight = FontWeight.Medium),
            color = Gunmetal
        )
        Text(
            text = "15h53",
            style = GWTypography.body2,
            color = EauBlue
        )
    }


}

@Preview(name = "MessageCard", showSystemUi = true)
@Composable
private fun PreviewMessageCard() {
    MessageCard()
}