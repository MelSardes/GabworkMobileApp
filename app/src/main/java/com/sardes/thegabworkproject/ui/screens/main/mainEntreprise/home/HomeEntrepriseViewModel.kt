package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.repository.Ressources
import com.sardes.thegabworkproject.repository.main.entreprise.HomeEntrepriseRepository
import kotlinx.coroutines.launch

class HomeEntrepriseViewModel(
    private val repository: HomeEntrepriseRepository = HomeEntrepriseRepository()
): ViewModel(){

    var homeEntrepriseUiState by mutableStateOf(HomeEntrepriseUiState())

    val user = repository.user()
    val hasUser: Boolean
        get() = repository.hasUser()

    private val entrepriseId: String
        get() = repository.getUserId()


    fun getEntrepriseInformations(){
        if (hasUser) {
            if (entrepriseId.isNotBlank()) {
                homeEntrepriseUiState = homeEntrepriseUiState.copy(isLoading = true)

                repository.getInformations(
                    onError = {}
                ) {
                    homeEntrepriseUiState = homeEntrepriseUiState.copy(entrepriseInformations = it)
                }
                homeEntrepriseUiState = homeEntrepriseUiState.copy(isLoading = false)
            }
        }
    }

    fun loadPosts(){
        if (hasUser){
            if (entrepriseId.isNotBlank())
                getActivePosts(entrepriseId)
        }
        else{
            homeEntrepriseUiState = homeEntrepriseUiState.copy(postList = Ressources.Error(
                throwable = Throwable(message = "Utilisateur non connecté")
            ))
        }
    }

    private fun getActivePosts(entrepriseId : String) = viewModelScope.launch {
        repository.getActivePosts(entrepriseId).collect{
            homeEntrepriseUiState = homeEntrepriseUiState.copy(postList = it)
        }
    }
}

data class HomeEntrepriseUiState(
    val isLoading: Boolean = false,
    val entrepriseInformations: CompteEntreprise? = null,
    val postList: Ressources<List<CompteEntreprise.PostVacant>> = Ressources.Loading(),
    )