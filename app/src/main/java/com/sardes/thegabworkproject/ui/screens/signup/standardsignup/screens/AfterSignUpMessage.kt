package com.sardes.thegabworkproject.ui.screens.signup.standardsignup.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.ui.screens.signup.components.Hero
import com.sardes.thegabworkproject.ui.theme.GWTypography
import com.sardes.thegabworkproject.ui.theme.GWpalette
import com.sardes.thegabworkproject.ui.theme.TailwindCSSColor
import kiwi.orbit.compose.ui.controls.Card

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun AfterSignUpMessage(
    navToHome: () -> Unit,
    goCompleteProfile: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(TailwindCSSColor.Red500)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                .background(TailwindCSSColor.Green500)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                    .background(Color.White)
            ) {

                Hero("Vous êtes désormais gabworker", R.drawable.paul_with_pink_hat)

                Text(
                    """
                            Vous pouvez désormais explorer toutes les offres et possibilités de Gabwork
                        
                            Si vous souhaitez postuler à des offres d'emploi, des informations sur 
                        votre parcours scolaire et votre expérience professionnelle seront nécessaire

                        
                    """.trimIndent(),
                    style = GWTypography.body1,
                    color = GWpalette.Gunmetal,
                    softWrap = true,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(20.dp),
                )
            }

//            StartSignUpBottomButton(stepZero, stepOne)
            Card(
                onClick = { goCompleteProfile.invoke() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                backgroundColor = TailwindCSSColor.Green500,
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        stringResource(id = R.string.complete_profile),
                        style = GWTypography.h6.copy(fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 5.dp)
                    )
                }
            }
        }

        Card(
            onClick = { navToHome.invoke() },
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            backgroundColor = TailwindCSSColor.Red500,
            elevation = 0.dp,
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    "Aller à l'acceuil",
                    style = GWTypography.h6,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 5.dp)

                )
            }
        }

    }
}

@Preview(name = "AfterSignUpMessage")
@Composable
private fun PreviewAfterSignUpMessage() {
    AfterSignUpMessage({}) {}
}