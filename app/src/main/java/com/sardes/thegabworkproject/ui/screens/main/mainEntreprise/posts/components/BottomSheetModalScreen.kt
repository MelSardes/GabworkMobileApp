package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PostAdd
import androidx.compose.runtime.*
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
import com.sardes.thegabworkproject.models.CompteDemandeur
import com.sardes.thegabworkproject.models.CompteEntreprise
import com.sardes.thegabworkproject.repository.Ressources
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.PostsEntrepriseUiState
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.PostsEntrepriseViewModel
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.standalonepost.PostUiState
import com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.standalonepost.StandalonePostViewModel
import com.sardes.thegabworkproject.ui.theme.NewBlue
import com.sardes.thegabworkproject.ui.theme.SoftBlue
import kotlinx.coroutines.launch


@SuppressLint("MaterialDesignInsteadOrbitDesign", "UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetModalScreen(
    modifier: Modifier = Modifier,
    standalonePostViewModel: StandalonePostViewModel? = StandalonePostViewModel(),
    postsViewModel: PostsEntrepriseViewModel? = PostsEntrepriseViewModel(),
    navToNewPostPage: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    var selectedPost: CompteEntreprise.PostVacant? by remember {
        mutableStateOf(null)
    }


    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(18.dp),
        sheetContent = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFE9E9E9))
            ) {
                var postUiState = standalonePostViewModel?.postUiState ?: PostUiState()

                LaunchedEffect(key1 = Unit){
                    try {
                        postUiState = postUiState.copy(isLoading = true)
                        standalonePostViewModel?.getPost(selectedPost!!.postId)
                        postUiState = postUiState.copy(postError = null)
                    }catch (e: Exception){
                        postUiState = postUiState.copy(postError = e.localizedMessage)
                        e.printStackTrace()
                    }finally {
                        postUiState = postUiState.copy(isLoading = false)
                    }
                }

                val scaffoldState = rememberScaffoldState()

                Scaffold(
                    topBar = { CustomTopAppBar(title = stringResource(id = R.string.posts)) }
                ) { padding ->
                    Column(modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()) {

                        Card(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                                .height(160.dp)
                                .clickable {  },
                            backgroundColor = SoftBlue,
                            shape = RoundedCornerShape(10.dp),
                            elevation = 10.dp,
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(20.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly,
                            ) {
                                Column(
                                    modifier = Modifier,
                                    verticalArrangement = Arrangement.SpaceEvenly,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        text = selectedPost!!.postName,
                                        style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
                                        color = NewBlue
                                    )
                                    Text(
                                        text = selectedPost!!.typeDEmploi,
                                        style = MaterialTheme.typography.h6,
                                        color = NewBlue
                                    )
                                    Text(
                                        text = selectedPost!!.adresse,
                                        style = MaterialTheme.typography.body2,
                                        color = NewBlue
                                    )
                                }

                                Spacer(Modifier.width(10.dp))

                                Column(
                                    modifier = Modifier,
                                    verticalArrangement = Arrangement.SpaceEvenly,
                                    horizontalAlignment = Alignment.End
                                ) {
                                    Text(
                                        text = if (selectedPost!!.actif) "Actif" else "Inactif",
                                        color = if (selectedPost!!.actif) Color(0xFF0F730C) else Color(
                                            0xFFDB1E1E
                                        ),
                                        modifier = if (selectedPost!!.actif) {
                                            Modifier
                                                .clip(RoundedCornerShape(20.dp))
                                                .background(Color(0xFFEDF9F0))
                                                .padding(5.dp)
                                        } else {
                                            Modifier
                                                .clip(RoundedCornerShape(20.dp))
                                                .background(Color(0xFFFFEFEF))
                                                .padding(5.dp)
                                        },
                                        style = MaterialTheme.typography.body1

                                    )
                                    Text(
                                        text = "${selectedPost!!.salaire} Fcfa/mois",
                                        color = NewBlue,
                                        style = MaterialTheme.typography.h6,
                                    )
                                }
                            }
                        }


                        Spacer(Modifier.height(20.dp))

                        Text(
                            text = stringResource(id = R.string.demandeurs),
                            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold)
                        )

                        Spacer(Modifier.height(10.dp))

                        LazyColumn {
                            item {
                                SeekerCardComponent(
                                    demandeur = CompteDemandeur(
                                        nom = "SARDES",
                                        prenom = "Mel",
                                        occupation = "Developpeur blockchain",
                                        urlPhotoProfil = "${R.drawable.black_businessman_in_blue_suit_waving_hello}"
                                    )
                                )
                            }
                        }
                    }
                }

            }
        }
    ) {

        val applicationUiState = postsViewModel?.postsUiState ?: PostsEntrepriseUiState()

        val scaffoldState = rememberScaffoldState()

        LaunchedEffect(key1 = Unit){
            postsViewModel?.loadPosts()
        }

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    navigationIcon = {},
                    actions = {
                        IconButton(onClick = {navToNewPostPage.invoke()}) {
                            Icon(
                                imageVector = Icons.Rounded.PostAdd,
                                contentDescription = "Add Post",
                            )
                        }
                    },
                    title = {
                        Text(text = "Posts", textAlign = TextAlign.Center)
                    }
                )
            }
        ) {

            when(applicationUiState.postList){
                is Ressources.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )
                }

                is Ressources.Success -> {
                    LazyColumn(modifier = Modifier.padding(6.dp)){
                        items(applicationUiState.postList.data ?: emptyList()) {
                                post ->
                            PostCardComponent(
                                post,
                                onCardClick = {
                                    selectedPost = post
                                    scope.launch {
                                        modalBottomSheetState.animateTo(ModalBottomSheetValue.HalfExpanded)
                                    }
                                }
                            )
                        }
                    }
                }
                else -> {
                    Text(
                        text = applicationUiState
                            .postList.throwable?.localizedMessage ?: "OOPS!\nUne Erreur s'est produite",
                        color = Color.Red
                    )
                }

            }

        }




/*
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {

            Button(
                onClick = {
                    scope.launch {
                        modalBottomSheetState.animateTo(ModalBottomSheetValue.HalfExpanded)
                    }
                },
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text("Half Expand")
            }

            Button(
                onClick = {
                    scope.launch {
                        modalBottomSheetState.animateTo(ModalBottomSheetValue.Expanded)
                    }
                },
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text("Expand")
            }

        }
*/
    }

}


@Preview(name = "BottomSheetModalScreen")
@Composable
private fun PreviewBottomSheetModalScreen() {
    BottomSheetModalScreen(
        navToNewPostPage = {}
    )
}
