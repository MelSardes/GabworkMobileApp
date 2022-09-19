package com.sardes.thegabworkproject.test

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.sardes.thegabworkproject.ui.theme.GWTypography
import kiwi.orbit.compose.ui.controls.ButtonSecondary
import kiwi.orbit.compose.ui.controls.Text



@SuppressLint("MaterialDesignInsteadOrbitDesign", "UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun Test(
) {

    var fileUri = mutableStateOf<Uri?>(null)

    val uriPathFinder = UriPathFinder()
    val context = LocalContext.current

    val path = fileUri.value?.let { uriPathFinder.getPath(context, it) }


    val selectFile = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        fileUri.value = uri
    }

    Column() {
        Text("Path :")
        Text(text = fileUri.toString())

        ButtonSecondary(
            onClick = { selectFile.launch("*/*") },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                "Choose a file",
                style = GWTypography.h6,
                textAlign = TextAlign.Center
            )
        }

    }


}



