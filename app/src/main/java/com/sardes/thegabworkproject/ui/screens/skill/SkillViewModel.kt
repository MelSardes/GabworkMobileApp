package com.sardes.thegabworkproject.ui.screens.skill

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseUser
import com.sardes.thegabworkproject.models.Competences_Profil_Etudiant
import com.sardes.thegabworkproject.repository.SkillsStorageRepository

class SkillViewModel (
    private val repository: SkillsStorageRepository = SkillsStorageRepository()
): ViewModel(){
    var skillUiState by mutableStateOf(SkillUiState())
        private set

    private val hasUser: Boolean
        get() = repository.hasUser()

    private val user: FirebaseUser?
        get() = repository.user()

    fun onCompetenceChange(competence: String){
        skillUiState = skillUiState.copy(competence = competence)
    }

    fun onSkillLevelChange(niveau_de_competence: String){
        skillUiState = skillUiState.copy(niveau_de_competence = niveau_de_competence)
    }


    fun addSkill(){
        if (hasUser){
            repository.addSkill(
                userId = user!!.uid,
                competence = skillUiState.competence,
                niveau_de_comptence = skillUiState.niveau_de_competence,
                timestamp = Timestamp.now()
            ){
                skillUiState = skillUiState.copy(skillAddedStatus = it)
            }
        }
    }


    fun setEditFields(competence: Competences_Profil_Etudiant){
        skillUiState = skillUiState.copy(
            competence = competence.competence,
            niveau_de_competence = competence.niveauDeCompetence
        )
    }

    fun getSkill(skillId: String){
        repository.getSkill(
            skillId = skillId,
            onError = {}
        ){
            skillUiState = skillUiState.copy(selectedSkill = it)
            skillUiState.selectedSkill?.let { it1 -> setEditFields(it1) }
        }
    }


    fun updateSkill(skillId: String){
        repository.updateSkill(
            competence = skillUiState.competence,
            niveau_de_comptence = skillUiState.niveau_de_competence,
            skillId = skillId
        ){
            skillUiState = skillUiState.copy(updateAddedSkill = it)
        }
    }


    fun resetSkillAddedStatus(){
        skillUiState = skillUiState.copy(
            skillAddedStatus = false,
            updateAddedSkill = false
        )
    }

    fun resetState(){
        skillUiState = SkillUiState()
    }


}

data class SkillUiState(
    val competence: String = "",
    val niveau_de_competence: String = "",
    val skillAddedStatus: Boolean = false,
    val updateAddedSkill: Boolean = false,
    val selectedSkill: Competences_Profil_Etudiant? = null,
)