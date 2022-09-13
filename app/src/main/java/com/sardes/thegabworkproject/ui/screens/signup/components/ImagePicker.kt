@file:Suppress("DEPRECATION")

package com.sardes.thegabworkproject.ui.screens.signup

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage


var storageRef = Firebase.storage.reference
val folderImagesRef = storageRef.child("images/mountains.jpg")
var userImageUrl by mutableStateOf("")

//test
const val UID = "876g89hbibjuidw"
val httpsReference = storageRef.child("users/me/${UID}__profile.png").downloadUrl

var userUrl by mutableStateOf("")


@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Suppress("DEPRECATION")
@Composable
fun ImagePicker(
    modifier: Modifier = Modifier,
) {
    Surface(modifier) {
        var imageUri by remember {
            mutableStateOf<Uri?>(null)
        }
        val context = LocalContext.current
        val bitmap =  remember {
            mutableStateOf<Bitmap?>(null)
        }

        val launcher = rememberLauncherForActivityResult(contract =
        ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
        }
        Column() {
            Button(onClick = {
                launcher.launch("image/*")
            }) {
                Text(text = "Choisir une image")
            }

            Spacer(modifier = Modifier.height(12.dp))

            imageUri?.let {
                if (Build.VERSION.SDK_INT < 28) {
                    bitmap.value = MediaStore.Images
                        .Media.getBitmap(context.contentResolver,it)

                } else {
                    val source = ImageDecoder
                        .createSource(context.contentResolver,it)
                    bitmap.value = ImageDecoder.decodeBitmap(source)
                }

                bitmap.value?.let {  btm ->
                    Image(bitmap = btm.asImageBitmap(),
                        contentDescription =null,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape),
                        contentScale = Crop
                    )
                }

            }

        }
    }
}


var imageUri = mutableStateOf<Uri?>(null)


//@OptIn(DelicateCoroutinesApi::class)
@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun Picker(context: ComponentActivity) {
val selectImage =
    rememberLauncherForActivityResult( contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri.value = uri
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(3f)
        ) {
            LazyColumn{
                item {
                    Box(modifier = Modifier.fillMaxSize()) {
                        IconButton(
                            modifier = Modifier.align(Alignment.BottomStart),
                            onClick = {
                                selectImage.launch("image/*")
                            }) {
                            Icon(
                                Icons.Filled.Add,
                                "add",
                                tint = Color.Blue
                            )
                        }

                        if (imageUri.value != null) {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                painter = rememberAsyncImagePainter(model = imageUri.value
                                ),
                                contentDescription = "image"
                            )


                            IconButton(
                                modifier = Modifier.align(Alignment.BottomCenter),


                                onClick = {
                                    storageRef.child("userProfile/${UID}__profile.jpg")
                                        .putFile(imageUri.value!!)

//                                    userUrl = storageRef.child("userProfile/${UID}__profile.jpg").downloadUrl.toString()
                                }
                            ) {
                                Icon(
                                    Icons.Default.PlayArrow,
                                    "scan",
                                    tint = Color.Blue
                                )
                            }
                        }

                        Column {
                            Text(text = "${imageUri.value}")
                            Text(text = userUrl)
                        }
                    }
                }

                item {
                    Surface(
                        modifier = Modifier
                            .size(100.dp)
                    ) {
                        AsyncImage(model = ImageRequest
                            .Builder(LocalContext.current)
                            .data("https://firebasestorage.googleapis.com/v0/b/thegabworkprojecttest.appspot.com/o/userProfile%2Fmel_monogram_logo_by_infinity_r_deibmn0.png?alt=media")
                            .crossfade(true).build(),
                            contentScale = Crop,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}
/*

suspend fun addImageToFirebaseStorage(imageUri: Uri) = flow {
    try {
        val downloadUrl = storageRef.child("userProfile/${UID}__profile.jpg")
            .putFile(imageUri).await()
            .storage.downloadUrl.await()
        emit(downloadUrl)
    } catch (e: Exception) {
    }
}
*/

@Preview(name = "Picker", showSystemUi = true, showBackground = true)
@Composable
private fun PreviewPicker() {
    Picker(context = ComponentActivity())
}