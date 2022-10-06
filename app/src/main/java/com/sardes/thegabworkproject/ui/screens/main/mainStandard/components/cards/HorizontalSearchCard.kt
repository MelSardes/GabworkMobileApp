package com.sardes.thegabworkproject.ui.screens.main.mainStandard.components.cards

/*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette

@Composable
fun HorizontalSearchCard(
    post: CompteEntreprise.Post,
    onCardClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .width(327.dp)
            .height(114.dp)
            .clickable { onCardClick.invoke() },
        elevation = CardDefaults.cardElevation(8.dp),
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp, horizontal = 12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.painted_paul),
                contentDescription = null,
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Spacer(modifier = Modifier.width(15.dp))

            Column(
                verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = post.postName,
                        style = GWTypography.subtitle1
                    )
                    Text(
                        text = post.salary + " F/m",
                        style = GWTypography.subtitle2
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = post.entrepriseName,
                        style = GWTypography.body1
                    )
                    Text(
                        text = post.city,
                        style = GWTypography.body1.copy(color = GWpalette.EauBlue)
                    )
                }
            }
        }
    }
}

@Preview(name = "JobProposalCard")
@Composable
private fun PreviewJobProposalCard() {
    HorizontalSearchCard(
        post = CompteEntreprise.Post(
            postName = "Développeur Kotlin",
            entrepriseName = "Sardes Corp.",
            city = "Sardesville",
            salary = "3000000",
        )
    ) {}
}*/
