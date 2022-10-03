package com.sardes.thegabworkproject.ui.screens.signup.standardsignup.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.sardes.thegabworkproject.ui.theme.TailwindCSSColor.Green500
import kiwi.orbit.compose.ui.controls.Card
import kiwi.orbit.compose.ui.controls.Text

@Composable
fun StandardSignUpStart(
    onNavToLoginPage: () -> Unit = {},
    startSigningUp: () -> Unit = {},
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(TailwindCSSColor.Red500)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.93f)
                .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                .background(Green500)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
                    .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                    .background(Color.White)
            ) {

                Hero("COMPTE STANDARD", R.drawable.painted_paul)

                Text(
                    "Avec ce compte, vous pourrez : \n" +
                            " Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. " +
                            "Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, " +
                            "ultricies sed, " +
                            "dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, " +
                            "semper congue, " +
                            "euismod non, mi. Proin porttitor, orci nec nonummy molestie, " +
                            "euismod non, mi. Proin porttitor, orci nec nonummy molestie, " +
                            "euismod non, mi. Proin porttitor, orci nec nonummy molestie, " +
                            "enim est eleifend mi, " +
                            "non fermentum diam nisl sit amet erat. Duis semper. ",
                    style = GWTypography.body1,
                    color = GWpalette.Gunmetal,
                    softWrap = true,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(20.dp),
                )
            }

//            StartSignUpBottomButton(stepZero, stepOne)
            Card(
                onClick = { startSigningUp.invoke() },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f),
                backgroundColor = Green500,
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        stringResource(id = R.string.start_signup),
                        style = GWTypography.h6.copy(fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            }

        }

        Card(
            onClick = { onNavToLoginPage.invoke() },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f),
            backgroundColor = TailwindCSSColor.Red500,
            elevation = 0.dp,
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    "Déjà inscrit",
                    style = GWTypography.h6,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }

    }

}

@Preview(name = "StandardSignUpStart")
@Composable
private fun PreviewStandardSignUpStart() {
    StandardSignUpStart()
}