package com.sardes.thegabworkproject.ui.screens.main.mainStandard.components.cards

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette.Gunmetal
import kiwi.orbit.compose.ui.controls.Card
import kiwi.orbit.compose.ui.controls.Text

@Composable
fun EntrepriseCard(
    entreprise: CompteEntreprise,
    onCardClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .width(200.dp)
            .height(200.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(24.dp),
        onClick = { onCardClick.invoke() }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(entreprise.urlLogo)
                    .crossfade(true)
                    .placeholder(R.drawable.ic_account_100)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .clip(RoundedCornerShape(24.dp)),
                contentScale = ContentScale.Crop,
            )

            Text(text = entreprise.nom, style = GWTypography.h6, color = Gunmetal)
        }
    }

}

@Preview(name = "EntrepriseCard")
@Composable
private fun PreviewEntrepriseCard() {
    EntrepriseCard(
        entreprise = CompteEntreprise(
            nom = "SARDES CORP.",
            urlLogo = R.drawable.painted_paul.toString()
        )
    ){}
}