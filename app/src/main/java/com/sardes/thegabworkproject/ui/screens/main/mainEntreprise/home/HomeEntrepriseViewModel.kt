package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.repository.main.entreprise.MainEntrepriseRepository
import com.sardes.thegabworkproject.repository.ressources.Ressources
import kotlinx.coroutines.launch

class HomeEntrepriseViewModel(
    private val repository: MainEntrepriseRepository = MainEntrepriseRepository()
) : ViewModel() {

    var homeEntrepriseUiState by mutableStateOf(HomeEntrepriseUiState())

    val user = repository.user()
    val hasUser: Boolean
        get() = repository.hasUser()

    private val entrepriseId: String
        get() = repository.getUserId()


    fun loadInformations() {
        getEntrepriseInformations()
    }

    private fun getEntrepriseInformations() {
        if (hasUser) {
            if (entrepriseId.isNotBlank()) {
                homeEntrepriseUiState = homeEntrepriseUiState.copy(isLoading = true)

                repository.getEntrepriseInformations(
                    onError = {}
                ) {
                    homeEntrepriseUiState = homeEntrepriseUiState.copy(entrepriseInformations = it)
                }
                homeEntrepriseUiState = homeEntrepriseUiState.copy(isLoading = false)
            }
        }
    }

    fun loadActivePosts() {
        if (hasUser) {
            if (entrepriseId.isNotBlank())
                getActivePosts(entrepriseId)
        } else {
            homeEntrepriseUiState = homeEntrepriseUiState.copy(
                homePostList = Ressources.Error(
                    throwable = Throwable(message = "Utilisateur non connecté")
                )
            )
        }
    }

    private fun getActivePosts(entrepriseId: String) = viewModelScope.launch {
        repository.getActivePosts(entrepriseId).collect {
            homeEntrepriseUiState = homeEntrepriseUiState.copy(homePostList = it)
        }
    }

}

data class HomeEntrepriseUiState(
    val isLoading: Boolean = false,
    val entrepriseInformations: CompteEntreprise? = null,
    val homePostList: Ressources<List<CompteEntreprise.Post>> = Ressources.Loading(),
)