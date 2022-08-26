package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sardes.thegabworkproject.R
import kotlinx.coroutines.launch

@SuppressLint("MaterialDesignInsteadOrbitDesign", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileEntrepriseScreenDeprecated(
    profileViewModel: ProfileEntrepriseViewModel? = ProfileEntrepriseViewModel()
) {
    val informationsUiState = profileViewModel?.informationsUiState ?: InformationsUiState()

    LaunchedEffect(key1 = Unit){
        profileViewModel?.loadInformations()
    }


    // create a scaffold state, set it to close by default
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    // Create a coroutine scope. Opening of Drawer
    // and snackbar should happen in background
    // thread without blocking main thread
    val coroutineScope = rememberCoroutineScope()

    // Scaffold Composable
    Scaffold(

        // pass the scaffold state
        scaffoldState = scaffoldState,

        // pass the topbar we created
/*
        topBar = {
            TopBar(
                // When menu is clicked open the
                // drawer in coroutine scope
                onMenuClicked = {
                    coroutineScope.launch {
                        // to close use -> scaffoldState.drawerState.close()
                        scaffoldState.drawerState.open()
                    }
                })
        },
*/

        // pass the bottomBar we created
//        bottomBar = { BottomBar() },

        // Pass the body in
        // content parameter
        content = {
            Body()
        },

        // pass the drawer
        drawerContent = {
            Drawer()
        },

        floatingActionButton = {
            // Create a floating action button in
            // floatingActionButton parameter of scaffold
            FloatingActionButton(
                onClick = {
                    // When clicked open Snackbar
                    coroutineScope.launch {
                        when (scaffoldState.snackbarHostState.showSnackbar(
                            // Message In the snackbar
                            message = "Snack Bar",
                            actionLabel = "Dismiss"
                        )) {
                            SnackbarResult.Dismissed -> {
                                // do something when
                                // snack bar is dismissed
                            }

                            SnackbarResult.ActionPerformed -> {
                                // when it appears
                            }

                        }
                    }
                }) {
                // Simple Text inside FAB
                Text(text = "Modifier les informations")
            }
        }
    )
}

// A function which will receive a
// callback to trigger to opening the drawer
@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun TopBar(onMenuClicked: () -> Unit) {
    // TopAppBar Composable
    TopAppBar(
        // Provide Title
        title = {
            Text(text = "Scaffold||GFG", color = Color.White)
        },
        // Provide the navigation Icon ( Icon on the left to toggle drawer)
        navigationIcon = {
            IconButton(onClick = { onMenuClicked }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",

                    // When clicked trigger onClick
                    // Callback to trigger drawer open
                    tint = Color.White
                )
            }
        },
        // background color of topAppBar
        backgroundColor = Color(0xFF0F9D58)
    )
}

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun BottomBar() {
    // BottomAppBar Composable
    BottomAppBar(
        backgroundColor = Color(0xFF0F9D58)
    ) {
        Text(text = "Bottom App Bar", color = Color.White)
    }
}

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun Body() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        item {
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(top = 20.dp)
                    .clip(RoundedCornerShape(15.dp))
            ){
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(R.drawable.perroquet)
                        .crossfade(true)
                        .placeholder(R.drawable.ic_account_100)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(color = Color.Black.copy(alpha = 0.2f)),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally,

                ){
                    Text(text = "SARDES CORP", color = Color.White, style = MaterialTheme.typography.h6)
                    Text(text = "Entreprise de technologies", color = Color.White, style = MaterialTheme.typography.body1)
                }
            }

            Card(
                elevation = (15.dp),
                backgroundColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(30.dp)),
            ){
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    Column {
                        Text(text = "27", style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold))
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Posts \n publiés", style = MaterialTheme.typography.body2)
                    }
                    Column {
                        Text(text = "19", style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold))
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Personnes \n recrutées", style = MaterialTheme.typography.body2)
                    }
                    Column {
                        Text(text = "7", style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold))
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Étudiants \n recrutés", style = MaterialTheme.typography.body2)
                    }
                }
            }
        }

        item{
            InfoCardComponent(title = "A propos", infos = ""+ LoremIpsum(50).toString())
        }
        item {
            InfoCardComponent(title = "Site web", infos = "https://www.sardes-corp.com")
        }
        item {
            InfoCardComponent(title = "Ville", infos = "Sardesville")
        }
        item {
            InfoCardComponent(title = "Secteur d'activité", infos = "Nouvelles technologies")
        }
        item {
            InfoCardComponent(title = "Existe depuis", infos = "2042")
        }
        item {
            InfoCardComponent(title = "Adresse", infos = "99 Boulevard Sardes, Sardesville")
        }
    }
}

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
private fun InfoCardComponent(title: String, infos: String) {
    Card(
        elevation = 5.dp,
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .padding(10.dp)
    ) {
        Column(verticalArrangement = Arrangement.SpaceBetween) {
            Text(title, style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold))
            Text(infos, style = MaterialTheme.typography.body2)
        }
    }
}


@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun Drawer() {
    // Column Composable
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        // Repeat is a loop which
        // takes count as argument
        repeat(5) { item ->
            Text(text = "Item number $item", modifier = Modifier.padding(8.dp), color = Color.Black)
        }
    }
}


@Preview(device = "id:TECNO POP3")
@Composable
fun PreviewProfileEntrepriseScreen() {
    ProfileEntrepriseScreenDeprecated()
}
