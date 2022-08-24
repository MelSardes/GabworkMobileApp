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
import com.sardes.thegabworkproject.ui.theme.BlueFlag
import kotlinx.coroutines.launch

@SuppressLint("MaterialDesignInsteadOrbitDesign", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileEntrepriseScreen(
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
            .background(Color.White),
    ) {
        item {
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(20.dp)
                    .clip(RoundedCornerShape(30.dp))
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
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(BlueFlag)
                    .clip(
                        RoundedCornerShape(30.dp)
                    ),
                elevation = (15.dp)
            ){
                Row(modifier = Modifier.fillMaxSize()){
                    Column {
                        Text(text = "27", style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold))
                        Text(text = "Posts \n publiés", style = MaterialTheme.typography.body2)
                    }
                    Column {
                        Text(text = "19", style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold))
                        Text(text = "Personnes \n recrutées", style = MaterialTheme.typography.body2)
                    }
                    Column {
                        Text(text = "7", style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold))
                        Text(text = "Étudiants \n recrutés", style = MaterialTheme.typography.body2)
                    }
                }
            }
        }

        item{
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(BlueFlag)
                    .clip(
                        RoundedCornerShape(30.dp)
                    )
            ) {
                Column(
                ) {
                    Text("A propos")
                    Text(""+LoremIpsum(50))
                }
            }
        }

        item {
            Column {
                Text("Site web")
                Text("https://www.sardes-corp.com")
            }

        }
        item {
            Column {
                Text("Ville")
                Text("Sardesville")
            }

        }
        item {
            Column {
                Text("Secteur d'activité")
                Text("Nouvelles technologies")
            }

        }
        item {
            Column {
                Text("Existe depuis")
                Text("2042")
            }

        }
        item {
            Column {
                Text("Adresse")
                Text("99 Boulevard Sardes, Sardesville")
            }

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
    ProfileEntrepriseScreen()
}
