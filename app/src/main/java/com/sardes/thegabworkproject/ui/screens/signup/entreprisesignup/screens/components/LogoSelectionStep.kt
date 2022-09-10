package com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.screens.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.EntrepriseAccountSignUpViewModel
import com.sardes.thegabworkproject.ui.screens.signup.imageUri
import com.sardes.thegabworkproject.ui.theme.GWTypography
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.ButtonSecondary

@Composable
fun LogoSelectionStep(
    viewModel: EntrepriseAccountSignUpViewModel?,
) {

    val selectImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri.value = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OrbitTheme.colors.surface.strong)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.LightGray)
        ) {
            if (imageUri.value != null) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = rememberAsyncImagePainter(model = imageUri.value),
                    contentScale = ContentScale.Crop,
                    contentDescription = "image",
                )
            } else {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.perroquet),
                    contentScale = ContentScale.Crop,
                    contentDescription = "image"
                )
            }
            viewModel?.onLogoChange(imageUri.value)
        }


        ButtonSecondary(
            onClick = { selectImage.launch("image/*") },
            modifier = Modifier
                .fillMaxWidth()
        )
        {
            kiwi.orbit.compose.ui.controls.Text(
                "Sélectionnez votre logo",
                style = GWTypography.h6,
                textAlign = TextAlign.Center
            )
        }
    }


}

@Preview(name = "LogoSelectionStep")
@Composable
private fun PreviewLogoSelectionStep() {
    LogoSelectionStep(null)
}