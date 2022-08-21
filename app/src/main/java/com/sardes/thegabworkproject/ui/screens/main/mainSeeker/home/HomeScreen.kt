package com.sardes.thegabworkproject.ui.screens.main.mainSeeker.home

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sardes.thegabworkproject.R

@Composable
fun HomeSeekerScreen(
    modifier: Modifier = Modifier,
    onNavToLoginOrSignUp: () -> Unit
) {
    LazyColumn (Modifier.fillMaxSize()){
        item {
            TopBar(
                avatarUrl = "https://cdn.dribbble.com/users/8060558/avatars/normal/data?1622508563",
                onNavToLoginOrSignUp
            )
        }
    }
}


@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun TopBar(avatarUrl: String, onNavToLoginOrSignUp: () -> Unit) {

    Surface{
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Bonjour Mel SARDES")
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(avatarUrl)
                    .crossfade(true)
                    .placeholder(R.drawable.ic_placeholder)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .clickable {
                        Firebase.auth.signOut()
                        onNavToLoginOrSignUp.invoke()
                    }
            )
        }
    }
}




@Preview(name = "HomeScreen", showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    HomeSeekerScreen(Modifier) {}
}