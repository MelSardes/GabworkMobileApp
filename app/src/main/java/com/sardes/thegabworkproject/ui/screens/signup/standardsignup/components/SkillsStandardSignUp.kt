package com.sardes.thegabworkproject.ui.screens.signup.standardsignup.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.data.models.Skill
import com.sardes.thegabworkproject.data.models.skillLevels
import com.sardes.thegabworkproject.ui.screens.signup.standardsignup.SignupUiStateStandard
import com.sardes.thegabworkproject.ui.screens.signup.standardsignup.StandardSignUpViewModel
import kiwi.orbit.compose.ui.controls.ButtonLinkCritical
import kiwi.orbit.compose.ui.controls.ButtonLinkPrimary
import kiwi.orbit.compose.ui.controls.KeyValueLarge
import kiwi.orbit.compose.ui.controls.TextField
import kiwi.orbit.compose.ui.controls.Text as OrbitText

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun SkillsStandardSignUp(
    uiStateStandard: SignupUiStateStandard?,
    viewModel: StandardSignUpViewModel?,
    focusRequester: FocusRequester,
    skills: MutableList<Skill>
) {


    var customSkill                     by remember { mutableStateOf("") }
    var expandedcustomSkillLevel        by remember { mutableStateOf(false) }
    var selectedOptionCustomSkillLevel  by remember { mutableStateOf(skillLevels[0]) }



    Column(modifier = Modifier
        .fillMaxSize()
        .padding(30.dp)
    ){

            Column {

                TextField(
                    value = customSkill,
                    onValueChange = { customSkill = it },
                    label = { Text("Compétence *") },
                    singleLine = false,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                )


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expandedcustomSkillLevel,
                        onExpandedChange = {
                            expandedcustomSkillLevel = !expandedcustomSkillLevel
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        TextField(
                            readOnly = true,
                            value = selectedOptionCustomSkillLevel,
                            onValueChange = { },
                            label = { OrbitText("Niveau de compétence") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = expandedcustomSkillLevel
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                        ExposedDropdownMenu(
                            modifier = Modifier.background(Color.Transparent),
                            expanded = expandedcustomSkillLevel,
                            onDismissRequest = { expandedcustomSkillLevel = false }
                        ) {
                            skillLevels.forEach {
                                DropdownMenuItem(
                                    onClick = {
                                        selectedOptionCustomSkillLevel = it
                                        expandedcustomSkillLevel = false
                                    }
                                ) {
                                    OrbitText(text = it)
//                            viewModel?.onActivityAreaChange(selectionOption.nom)
//                                customSkillLevel = selectedOptionCustomSkillLevel
                                }
                            }
                        }
                    }


                    ButtonLinkPrimary(
                        onClick = {
                            if (customSkill.isNotEmpty()) {

                                skills.add(
                                    Skill(
                                        customSkill,
                                        selectedOptionCustomSkillLevel
                                    )
                                )

                                customSkill = ""

                                viewModel?.onCompetencesChange(skills)
                            }
                        },
                        modifier = Modifier
                    ) {
                        Text(text = "Ajouter")
                    }

                }
            }


            Spacer(modifier = Modifier.height(50.dp))

/*
        item {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expandedGabworkSkill,
                        onExpandedChange = {
                            expandedGabworkSkill = !expandedGabworkSkill
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        TextField(
                            readOnly = true,
                            value = selectedOptionGabworkSkill,
                            onValueChange = { },
                            label = { OrbitText("Nos propositions") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = expandedGabworkSkill
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                        ExposedDropdownMenu(
                            modifier = Modifier.background(Color.Transparent),
                            expanded = expandedGabworkSkill,
                            onDismissRequest = { expandedGabworkSkill = false }
                        ) {
                            competences.forEach { selectionOption ->
                                DropdownMenuItem(
                                    onClick = {
                                        selectedOptionGabworkSkill = selectionOption.nomCompetence
                                        expandedGabworkSkill = false
                                    }
                                ) {
                                    OrbitText(text = selectionOption.nomCompetence)
    //                            viewModel?.onActivityAreaChange(selectionOption.nom)
//                                    gabworkSkill = selectedOptionGabworkSkill
                                }
                            }
                        }
                    }

                    ButtonLinkPrimary(
                        onClick = { skills.add( Skill(selectedOptionGabworkSkill, selectedOptionGabworkSkillLevel) ) },
                        modifier = Modifier
                    ) {
                        Text(text = "Ajouter")
                    }
                }


                ExposedDropdownMenuBox(
                    expanded = expandedGabworkSkillLevel,
                    onExpandedChange = {
                        expandedGabworkSkillLevel = !expandedGabworkSkillLevel
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        readOnly = true,
                        value = selectedOptionGabworkSkillLevel,
                        onValueChange = { },
                        label = { OrbitText("Niveau de compétence") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = expandedGabworkSkillLevel
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        modifier = Modifier.background(Color.Transparent),
                        expanded = expandedGabworkSkillLevel,
                        onDismissRequest = { expandedGabworkSkillLevel = false }
                    ) {
                        skillLevels.forEach { selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedOptionGabworkSkillLevel = selectionOption
                                    expandedGabworkSkillLevel = false
                                }
                            ) {
                                OrbitText(text = selectionOption)
//                            viewModel?.onActivityAreaChange(selectionOption.nom)
//                                gabworkSkillLevel = selectedOptionGabworkSkillLevel
                            }
                        }
                    }
                }
            }

        }
*/


        LazyColumn {

            skills.forEach { skill ->
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(1f),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        KeyValueLarge(
                            key = skill.level,
                            value = skill.title,
                            modifier = Modifier
                                .background(Color.White)
                                .weight(1f)
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        )

                        ButtonLinkCritical(onClick = { skills.remove(skill) }) {
                            Text(text = "Retirer")
                        }
                    }
                }
            }
        }
    }
}

@Preview(name = "SkillsSeekerSignUp", showSystemUi = false, showBackground = true)
@Composable
private fun PreviewSkillsSeekerSignUp() {
    SkillsStandardSignUp(null, null, FocusRequester.Default, emptyList<Skill>() as MutableList<Skill>)
}