package com.sardes.thegabworkproject.ui.screens.skill

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.ui.theme.BlueFlag

/*
@SuppressLint("CoroutineCreationDuringComposition", "MaterialDesignInsteadOrbitDesign")
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
*/
/*
            OutlinedTextField(
                value = skillUiState.niveau_de_competence,
                onValueChange = { skillViewModel?.onNiveauDeCompetenceChange(it) },
                label = { Text(text = "Niveau de compétence")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(30.dp)

            )*//*


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
                        skillViewModel?.onSkillLevelChange(skill)
                    }
                }
            }
        }
    }
}
*/


@SuppressLint("MaterialDesignInsteadOrbitDesign")
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



/*
@Preview(showSystemUi = true)
@Composable
fun PreviewSkillScreen(){
    TheGabworkProjectTheme {
        SkillScreen(skillViewModel = null, skillId = "") {
            
        }
    }
}*/
