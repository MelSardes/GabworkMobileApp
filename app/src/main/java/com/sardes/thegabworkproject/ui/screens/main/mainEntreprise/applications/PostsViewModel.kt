package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.applications

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sardes.thegabworkproject.models.CompteEntreprise
import com.sardes.thegabworkproject.repository.Ressources
import com.sardes.thegabworkproject.repository.main.entreprise.ApplicationsEntrepriseRepository
import kotlinx.coroutines.launch

class PostsEntrepriseViewModel(
    private val repository: ApplicationsEntrepriseRepository = ApplicationsEntrepriseRepository()
) : ViewModel() {

    var applicationsUiState by mutableStateOf(PostsEntrepriseUiState())

    val user = repository.user()
    val hasUser: Boolean
        get() = repository.hasUser()

    private val entrepriseId: String
        get() = repository.getUserId()


    fun loadPosts(){
        if (hasUser){
            if (entrepriseId.isNotBlank())
                getEntreprisePosts(entrepriseId)
        }
        else{
            applicationsUiState = applicationsUiState.copy(postList = Ressources.Error(
                throwable = Throwable(message = "Utilisateur non connecté")
            ))
        }
    }

    private fun getEntreprisePosts(entrepriseId : String) = viewModelScope.launch {
        repository.getEntreprisePosts(entrepriseId).collect{
            applicationsUiState = applicationsUiState.copy(postList = it)
        }
    }
}


data class PostsEntrepriseUiState(
    val postList: Ressources<List<CompteEntreprise.PostVacant>> = Ressources.Loading(),
)