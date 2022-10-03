package com.sardes.thegabworkproject.ui.screens.main.mainStandard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette.DarkLiver
import kiwi.orbit.compose.ui.controls.ButtonPrimary
import kiwi.orbit.compose.ui.controls.ButtonPrimarySubtle

@Composable
fun ApplicationRequiredInformationsMessage(
    modifier: Modifier = Modifier,
    navToComplete: () -> Unit = {}
) {
    Column(
        modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(30.dp),
        verticalArrangement = Arrangement.SpaceEvenly

    ) {
        Image(
            painter = painterResource(id = R.drawable.add_files_pana),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = """
            |       Il manque quelques informations à votre profil.
            |Complétez le maintenant pour postuler à cette offre.
            |Ne perdez pas de temps, ce post est peut-être fait pour vous
        """.trimMargin(),
            style = GWTypography.h3.copy(color = DarkLiver),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ButtonPrimarySubtle(
                onClick = { /*TODO*/ },
            ) {
                Text("Pas maintenant", style = GWTypography.body1)
            }

            ButtonPrimary(
                onClick = { navToComplete.invoke() },
                modifier = Modifier.clip(RoundedCornerShape(24.dp))
            ) {
                Text(
                    text = "Completer le profil",
                    style = GWTypography.subtitle1.copy(color = Color.White),
//                    modifier = Modifier.clip(RoundedCornerShape(24.dp)),
                )
            }

        }
    }
}

@Preview(name = "ApplicationRequiredInformationsMessage")
@Composable
private fun PreviewApplicationRequiredInformationsMessage() {
    ApplicationRequiredInformationsMessage()
}