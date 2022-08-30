package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.repository.main.entreprise.HomeEntrepriseRepository
import com.sardes.thegabworkproject.repository.ressources.Ressources
import kotlinx.coroutines.launch

class PostsEntrepriseViewModel (
    private val repository: HomeEntrepriseRepository = HomeEntrepriseRepository()
): ViewModel() {


    var postsEntrepriseUiState by mutableStateOf(PostsEntrepriseUiState())

    val user = repository.user()
    val hasUser: Boolean
        get() = repository.hasUser()

    private val entrepriseId: String
        get() = repository.getUserId()



    fun loadActivePosts(){
        if (hasUser){
            if (entrepriseId.isNotBlank())
                getActivePosts(entrepriseId)
        }
        else{
            postsEntrepriseUiState = postsEntrepriseUiState.copy(postList = Ressources.Error(
                throwable = Throwable(message = "Utilisateur non connecté")
            ))
        }
    }



    private fun getActivePosts(entrepriseId : String) = viewModelScope.launch {
        repository.getActivePosts(entrepriseId).collect{
            postsEntrepriseUiState = postsEntrepriseUiState.copy(postList = it)
        }
    }


}

data class PostsEntrepriseUiState(
    val isLoading: Boolean = false,
    val entrepriseInformations: CompteEntreprise? = null,
    val postList: Ressources<List<CompteEntreprise.PostVacant>> = Ressources.Loading(),
)