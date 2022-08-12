package com.sardes.thegabworkproject.view.skill

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sardes.thegabworkproject.models.Compte_Standard
import com.sardes.thegabworkproject.repository.Ressources
import com.sardes.thegabworkproject.repository.StorageRepository
import kotlinx.coroutines.launch

class HomeSkillViewModel(
    private val repository: StorageRepository
): ViewModel() {
    var homeSkillUiState by mutableStateOf(HomeSkillUiState())

    val user = repository.user()
    val hasUser: Boolean
        get() = repository.hasUser()

    private val userId: String
        get() = repository.getUserId()


    fun loadSkills(){
        if (hasUser){
            if (userId.isNotBlank())
                getUserSkills(userId)
        }else{
            homeSkillUiState = homeSkillUiState.copy(skillList = Ressources.Error(
                throwable = Throwable(message = "Utilisateur non connecté")
            ))
        }
    }

    private  fun getUserSkills(userId: String) = viewModelScope.launch {
        repository.getUserSkills(userId).collect{
            homeSkillUiState = homeSkillUiState.copy(skillList = it)
        }
    }


    fun deleteSkill(skillId:String) = repository.deleteSkill(skillId){
        homeSkillUiState = homeSkillUiState.copy(skillDeleteStatus = it)
    }

    fun signOut() = repository.signOut()


}

data class HomeSkillUiState(
    val skillList:Ressources<List<Compte_Standard.Profil_Etudiant.Competances_Profil_Etudiant>> = Ressources.Loading(),
    val skillDeleteStatus: Boolean = false
)