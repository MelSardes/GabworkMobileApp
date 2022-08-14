package com.sardes.thegabworkproject.view.skill

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.ui.theme.BlueFlag
import com.sardes.thegabworkproject.ui.theme.TheGabworkProjectTheme
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SkillScreen(
    skillViewModel: SkillViewModel?,
    skillId: String,
    onNavigate:() -> Unit
){
    val skillUiState = skillViewModel?.skillUiState ?: SkillUiState()

    val isFormNotBlanck = skillUiState.competence.isNotBlank() &&
            skillUiState.niveau_de_competence.isNotBlank()


    val isSkillNotBlanck = skillId.isNotBlank()
    val icon = if (isSkillNotBlanck) Icons.Default.Refresh
    else Icons.Default.Check

    LaunchedEffect(key1 = Unit){
        if (isFormNotBlanck){
            skillViewModel?.getSkill(skillId)
        }else{
            skillViewModel?.resetState()
        }

    }

    val scope = rememberCoroutineScope()
    
    val scaffoldState = rememberScaffoldState()
    
    
    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            AnimatedVisibility(visible = isFormNotBlanck) {
                FloatingActionButton(
                    onClick = {
                        if (isSkillNotBlanck){
                            skillViewModel?.updateSkill(skillId)
                        }else{
                            skillViewModel?.addSkill()
                        }
                    }
                ) {
                    Icon(imageVector = icon, contentDescription  = null)
                }
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .padding(padding)
        ) {
            if (skillUiState.skillAddedStatus){
                scope.launch {
                    scaffoldState.snackbarHostState
                        .showSnackbar("Ajouté avec succès")
                    skillViewModel?.resetSkillAddedStatus()
                    onNavigate.invoke()
                }
            }

            if (skillUiState.updateAddedSkill){
                scope.launch {
                    scaffoldState.snackbarHostState
                        .showSnackbar("Mise a jour réussie")
                    skillViewModel?.resetSkillAddedStatus()
                    onNavigate.invoke()  
                }
            }

            Text(text = "Editer la compétence", modifier = Modifier.align(CenterHorizontally))
            
            OutlinedTextField(
                value = skillUiState.competence,
                onValueChange = { skillViewModel?.onCompetenceChange(it) },
                label = { Text(text = "Compétence")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(50.dp)
            
            )

            Spacer(modifier = Modifier.height(10.dp))
/*
            OutlinedTextField(
                value = skillUiState.niveau_de_competence,
                onValueChange = { skillViewModel?.onNiveauDeCompetenceChange(it) },
                label = { Text(text = "Niveau de compétence")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(30.dp)

            )*/

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly,
                contentPadding = PaddingValues(
                    vertical = 16.dp,
                    horizontal = 8.dp
                )
            ){
                itemsIndexed(Utils.niveau_de_competence){ _, skill ->
                    SkillLevelItem(skillLevel = skill){
                        skillViewModel?.onNiveauDeCompetenceChange(skill)
                    }
                }
            }
        }
    }
}


@Composable
fun SkillLevelItem(
    skillLevel: String,
    onClick:()->Unit
){
    Surface(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                onClick.invoke()
            },
            shape = RoundedCornerShape(30.dp),
        border = BorderStroke(1.dp, BlueFlag)
    )
    {
        Text(text = skillLevel, modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
    }
}



@Preview(showSystemUi = true)
@Composable
fun PreviewSkillScreen(){
    TheGabworkProjectTheme {
        SkillScreen(skillViewModel = null, skillId = "") {
            
        }
    }
}