package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sardes.thegabworkproject.models.CompteEntreprise
import com.sardes.thegabworkproject.repository.main.entreprise.HomeEntrepriseRepository

class HomeEntrepriseViewModel(
    private val repository: HomeEntrepriseRepository = HomeEntrepriseRepository()

): ViewModel(){
    var homeEntrepriseUiState by mutableStateOf(HomeEntrepriseUiState())

    val entreprise = repository.user()

    val hasUser: Boolean
        get() = repository.hasUser()

    private val entrepriseId: String
        get() = repository.getUserId()


    fun getEntrepriseInformations(){
        if (hasUser) {
            if (entrepriseId.isNotBlank()) {
                repository.getInformations(
                    onError = {}
                ) {
                    homeEntrepriseUiState = homeEntrepriseUiState.copy(entrepriseInformations = it)
                }
            }
        }
    }
}

data class HomeEntrepriseUiState(
    val entrepriseInformations: CompteEntreprise? = null,
)