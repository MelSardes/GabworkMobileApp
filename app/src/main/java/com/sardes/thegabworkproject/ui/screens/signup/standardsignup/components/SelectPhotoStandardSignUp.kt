package com.sardes.thegabworkproject.ui.screens.signup.standardsignup.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.sardes.thegabworkproject.ui.screens.signup.standardsignup.StandardSignUpViewModel
import com.sardes.thegabworkproject.ui.theme.GWTypography
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.ButtonSecondary
import kiwi.orbit.compose.ui.controls.Text

@Composable
fun SelectPhotoStandardSignUp(viewModel: StandardSignUpViewModel?) {

    var imageUri = remember{ mutableStateOf<Uri?>(null)}


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
                .weight(1f)
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

                viewModel?.onPhotoChange(imageUri.value)

            } else {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.perroquet),
                    contentScale = ContentScale.Crop,
                    contentDescription = "image"
                )
            }
        }


        ButtonSecondary(
            onClick = { selectImage.launch("image/*") },
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth()
                .padding(vertical = 7.dp)
        ) {
            Text(
                "Sélectionnez une photo de profil *",
                style = GWTypography.h6,
                textAlign = TextAlign.Center
            )
        }
    }


}

@Preview(name = "SelectPhotoSeekerSignUp")
@Composable
private fun PreviewSelectPhotoSeekerSignUp() {
    SelectPhotoStandardSignUp(null)
}