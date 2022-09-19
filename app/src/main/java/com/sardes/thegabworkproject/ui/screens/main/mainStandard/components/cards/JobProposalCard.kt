package com.sardes.thegabworkproject.ui.screens.main.mainStandard.components.cards

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.data.models.CompteStandard
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette

@Composable
fun JobProposalCard(
    jobProposal: CompteStandard.JobProposal,
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp, horizontal = 12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
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
                            text = jobProposal.postName.toString(),
                            style = GWTypography.subtitle1
                        )
                        Text(
                            text = jobProposal.salary + " F/m",
                            style = GWTypography.subtitle2
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = jobProposal.entrepriseName.toString(),
                            style = GWTypography.body1
                        )
                        Text(
                            text = jobProposal.city.toString(),
                            style = GWTypography.body1.copy(color = GWpalette.EauBlue)
                        )
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Non négociable",
                    textAlign = TextAlign.Start,
                    style = GWTypography.body1,
                )
                Text(
                    text = "Débutez le "+jobProposal.beginDate.toString(),
                    style = GWTypography.body1
                )
            }
        }
    }
}

@Preview(name = "JobProposalCard")
@Composable
private fun PreviewJobProposalCard() {
    JobProposalCard(
        jobProposal = CompteStandard.JobProposal(
            postName = "Développeur Kotlin",
            entrepriseName = "Sardes Corp.",
            city = "Sardesville",
            salary = "3000000",
            beginDate = "25/10/2022"
        )
    ){}
}