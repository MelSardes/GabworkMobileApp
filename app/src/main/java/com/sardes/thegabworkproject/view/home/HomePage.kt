package com.sardes.thegabworkproject.view.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.ui.theme.BlueFlag
import com.sardes.thegabworkproject.ui.theme.TheGabworkProjectTheme
import com.sardes.thegabworkproject.ui.theme.YellowFlag


//@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Home(){
    TheGabworkProjectTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .fillMaxSize()
        ) {
            LazyColumn (Modifier.weight(0.9f)){
                item {
                    TopBar(avatarUrl = "https://cdn.dribbble.com/users/8060558/avatars/normal/data?1622508563")
                }
                item{
                    SearchBar(
                        onTextChange = {/* TODO */},
                    )
                }
            }

            TabBar()
        }
    }
}

@Composable
fun TopBar(avatarUrl: String) {

    Surface{
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Bonjour Loïck")
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
                    }
            )
        }
    }
}


@Composable
fun SearchBar(onTextChange: (String) -> Unit){
    var text by rememberSaveable {mutableStateOf("")}

    Surface{
        Row{
            TextField(
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = YellowFlag,
                    textColor = BlueFlag
                ),
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = {
                    text = it
                    onTextChange(it)
                                },
                placeholder = {R.string.look_for}
            )
        }
    }
}


data class TabItem(
    val icon: ImageVector,
    val contentDescription: String
)


@Composable
private fun TabBar() {
    Surface {
        var tabIndex by remember { mutableStateOf(0) }

        TabRow(
            selectedTabIndex = tabIndex,
            backgroundColor = Color(0xFF2A3855),
            contentColor = YellowFlag
        ) {
            val tabs = listOf(
                TabItem(Icons.Rounded.Home, ""),
                TabItem(Icons.Rounded.Folder, ""),
                TabItem(Icons.Rounded.Search, ""),
                TabItem(Icons.Rounded.Favorite, ""),
                TabItem(Icons.Rounded.Person, ""),
            )

            tabs.forEachIndexed{i, item ->
                Tab(
                    selected = tabIndex == i,
                    onClick = { tabIndex = i},
                    modifier = Modifier
                        .heightIn(48.dp)
//                        .background(
//                            if (i == tabIndex) Color.White
//                            else BlueFlag
//                        )
                ){
                    Icon(item.icon, contentDescription = item.contentDescription,
                        tint = if (i == tabIndex){
                            YellowFlag
                        }else{
                            Color.White.copy(alpha = 0.5f)
                        }
                    )
                }
            }
        }
    }
}