package com.sardes.thegabworkproject.ui.screens.main.mainStandard.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.data.models.CompteStandard
import com.sardes.thegabworkproject.repository.main.standard.MainStandardRepository
import com.sardes.thegabworkproject.repository.ressources.Ressources
import kotlinx.coroutines.launch

class HomeStandardViewModel(
    private val repository: MainStandardRepository = MainStandardRepository()
): ViewModel() {

    var homeStandardUiState by mutableStateOf(HomeStandardUiState())

    val user = repository.user()
    val hasUser: Boolean
        get() = repository.hasUser()

    private val userId: String
        get() = repository.getUserId()

    fun getUserInformations(){
        if(hasUser){
            if(userId.isNotBlank()){
                homeStandardUiState = homeStandardUiState.copy(isLoading = true)

                repository.getUserInformations(
                    onError = {},
                    seekerId = userId
                ){
                    homeStandardUiState = homeStandardUiState.copy(userInformations = it)
                }
                homeStandardUiState = homeStandardUiState.copy(isLoading = false)
            }
        }
    }


    fun getPost(postId: String){
        repository.getPost(
            postId = postId,
            onError = {}
        ){
            homeStandardUiState = homeStandardUiState.copy(selectedPost = it)
        }
    }





    fun loadFiveLatestPosts(){
        if (hasUser){
            getFiveLatestPosts()
        }else{
            homeStandardUiState = homeStandardUiState.copy(fiveLatestPostsList = Ressources.Error(
                throwable = Throwable(message = "Utilisateur non connecté")
            ))
        }
    }
    private fun getFiveLatestPosts() =viewModelScope.launch{
        repository.getFiveLatestPosts().collect{
            homeStandardUiState = homeStandardUiState.copy(fiveLatestPostsList = it)
        }
    }


}

data class HomeStandardUiState(
    val isLoading: Boolean = false,
    val userInformations: CompteStandard? = null,
    val postsList: Ressources<List<CompteEntreprise.Post>> = Ressources.Loading(),
    val fiveLatestPostsList: Ressources<List<CompteEntreprise.Post>> = Ressources.Loading(),

    val entreprises: Ressources<List<CompteEntreprise>> = Ressources.Loading(),

    val selectedPost: CompteEntreprise.Post? = null,

)