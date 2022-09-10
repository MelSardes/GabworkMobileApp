package com.sardes.thegabworkproject.ui.screens.main.mainSeeker.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sardes.thegabworkproject.data.models.CompteDemandeur
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.repository.main.seeker.MainSeekerRepository
import com.sardes.thegabworkproject.repository.ressources.Ressources
import kotlinx.coroutines.launch

class HomeSeekerViewModel(
    private val repository: MainSeekerRepository = MainSeekerRepository()
): ViewModel() {

    var homeSeekerUiState by mutableStateOf(HomeSeekerUiState())

    val user = repository.user()
    val hasUser: Boolean
        get() = repository.hasUser()

    private val seekerId: String
        get() = repository.getUserId()


    fun getSeekerInformations(){
        if(hasUser){
            if(seekerId.isNotBlank()){
                homeSeekerUiState = homeSeekerUiState.copy(isLoading = true)

                repository.getSeekerInformations(
                    onError = {},
                    seekerId = seekerId
                ){
                    homeSeekerUiState = homeSeekerUiState.copy(seekerInformations = it)
                }
                homeSeekerUiState = homeSeekerUiState.copy(isLoading = false)
            }
        }
    }

    fun loadFiveLatestPosts(){
        if (hasUser){
            getFiveLatestPosts()
        }else{
            homeSeekerUiState = homeSeekerUiState.copy(fiveLatestPostsList = Ressources.Error(
                throwable = Throwable(message = "Utilisateur non connecté")
            ))
        }
    }
    private fun getFiveLatestPosts() =viewModelScope.launch{
        repository.getFiveLatestPosts().collect{
            homeSeekerUiState = homeSeekerUiState.copy(fiveLatestPostsList = it)
        }
    }


}

data class HomeSeekerUiState(
    val isLoading: Boolean = false,
    val seekerInformations: CompteDemandeur? = null,
    val postsList: Ressources<List<CompteEntreprise.Post>> = Ressources.Loading(),
    val fiveLatestPostsList: Ressources<List<CompteEntreprise.Post>> = Ressources.Loading()
)