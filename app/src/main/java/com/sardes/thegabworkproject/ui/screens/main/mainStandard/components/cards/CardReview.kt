package com.sardes.thegabworkproject.ui.screens.main.mainStandard.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette

@Composable
fun CardReview(review: CompteEntreprise.Post.Review) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
        ) {

            Card(
                modifier = Modifier.size(60.dp),
                colors = CardDefaults.cardColors(Color.White),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 8.dp
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.painted_paul),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(4.dp))
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = review.reviewerName ?: "",
                    style = GWTypography.subtitle1,
                    color = GWpalette.Gunmetal,
                    textAlign = TextAlign.End
                )
                Text(
                    text = review.date.toString() ?: "",
                    style = GWTypography.body2,
                    color = GWpalette.EauBlue,
                    textAlign = TextAlign.End
                )
            }
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
                .background(GWpalette.EauBlue)
                .height(1.dp)
        )

        Text(
            text = review.reviewContent ?: "",
            style = GWTypography.body1.copy(fontWeight = FontWeight.Medium),
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
        )
    }
}


@Preview
@Composable
fun CardReviewPreview() {
    CardReview(review = CompteEntreprise.Post.Review())
}