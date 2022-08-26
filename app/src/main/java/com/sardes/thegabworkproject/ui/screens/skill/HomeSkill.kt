package com.sardes.thegabworkproject.ui.screens.skill

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.Timestamp
import com.sardes.thegabworkproject.data.models.Competences_Profil_Etudiant
import com.sardes.thegabworkproject.repository.ressources.Ressources
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("MaterialDesignInsteadOrbitDesign", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeSkill(
    homeSkillViewModel: HomeSkillViewModel?,
    onSkillClick: (id: String) -> Unit,
    navToSkillPage: () -> Unit,
    navToLoginPage: () -> Unit
) {
    val homeSkillUiState = homeSkillViewModel?.homeSkillUiState ?: HomeSkillUiState()

    var openDialog by remember{
        mutableStateOf(false)
    }

    var selectedSkill: Competences_Profil_Etudiant? by remember {
        mutableStateOf(null)
    }

//    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = Unit){
        homeSkillViewModel?.loadSkills()
    }


    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = { navToSkillPage.invoke() }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        },
        topBar = {
            TopAppBar(
                navigationIcon = {},
                actions = {
                          IconButton(onClick = {
                              homeSkillViewModel?.signOut()
                          }) {
                              Icon(
                                  imageVector = Icons.Default.ExitToApp,
                                  contentDescription = null,
                              )
                          }
                },
                title = {
                    Text(text = "Mes competences")
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding()) {
            when(homeSkillUiState.skillList){
                is Ressources.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )
                }
                is Ressources.Success -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(16.dp),
                    ){
                        items(
                            homeSkillUiState.skillList.data ?: emptyList()
                        ){
                            skill ->
                            SkillItem(
                                competences = skill,
                                onLongClick = {
                                    openDialog = true
                                    selectedSkill = skill
                                }
                            ) {
                                onSkillClick.invoke(skill.idCompetenceEtudiant)
                            }
                        }
                    }
                    AnimatedVisibility(visible = openDialog) {
                        AlertDialog(onDismissRequest = {
                            openDialog= false
                        },
                            title = {Text(text = "Supprimer la note ?")},
                            confirmButton = {
                                Button(
                                    onClick = {
                                        selectedSkill?.idCompetenceEtudiant?.let{
                                            homeSkillViewModel?.deleteSkill(it)
                                        }
                                        openDialog = false
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color.Red
                                    )
                                ) {
                                    Text(text = "Supprimer")
                                }
                            },
                            dismissButton = {
                                Button(onClick = {openDialog = false }) {
                                    Text(text = "Anuller")
                                }
                            }
                        )
                    }
                }
                else -> {
                    Text(
                        text = homeSkillUiState
                            .skillList.throwable?.localizedMessage ?: "OOPS!\nUne erreur s'est produite",
                        color = Color.Red
                    )
                }
            }

        }
    }

    LaunchedEffect(key1 = homeSkillViewModel?.hasUser){
        if (homeSkillViewModel?.hasUser == false){
            navToLoginPage.invoke()
        }
    }

}


@SuppressLint("MaterialDesignInsteadOrbitDesign")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SkillItem(
    competences: Competences_Profil_Etudiant,
    onLongClick: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .combinedClickable(
                onLongClick = { onLongClick.invoke() },
                onClick = { onClick.invoke() }
            )
            .padding(8.dp)
            .fillMaxWidth(),
    ) {
        Column {
            Text(
                text = competences.competence,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Clip,
                modifier = Modifier.padding(4.dp)
            )
            Spacer(modifier = Modifier.size(4.dp))
            CompositionLocalProvider (
                LocalContentAlpha provides ContentAlpha.disabled
            ){
                Text(
                    text = competences.niveauDeCompetence,
                    style = MaterialTheme.typography.body1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(4.dp)
                        .align(Alignment.End),
                    maxLines = 1
                )
            }
            CompositionLocalProvider (
                LocalContentAlpha provides ContentAlpha.disabled
            ){
                Text(
                    text = formatDate(competences.timestamp),
                    style = MaterialTheme.typography.body1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(4.dp)
                        .align(Alignment.End),
                    maxLines = 1
                )
            }
        }
    }
}


private fun formatDate(timestamp: Timestamp):String{
    val sdf = SimpleDateFormat("MM-dd-yy-hh:mm", Locale.getDefault())
    return sdf.format(timestamp.toDate())
}

@Preview(name = "HomeSkill")
@Composable
private fun PreviewHomeSkill() {
    SkillItem(Competences_Profil_Etudiant(),{},{} )
}
