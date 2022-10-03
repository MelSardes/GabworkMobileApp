package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.data.models.CompteStandard
import com.sardes.thegabworkproject.repository.main.entreprise.MainEntrepriseRepository
import com.sardes.thegabworkproject.repository.ressources.Ressources
import kotlinx.coroutines.launch

class PostsEntrepriseViewModel (
    private val repository: MainEntrepriseRepository = MainEntrepriseRepository()
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
    fun loadAllPosts(){
        if (hasUser){
            if (entrepriseId.isNotBlank())
                getAllPosts(entrepriseId)
        }
        else{
            postsEntrepriseUiState = postsEntrepriseUiState.copy(postList = Ressources.Error(
                throwable = Throwable(message = "Utilisateur non connecté")
            ))
        }
    }
    fun loadInactivePosts(){
        if (hasUser){
            if (entrepriseId.isNotBlank())
                getInactivePosts(entrepriseId)
        }
        else{
            postsEntrepriseUiState = postsEntrepriseUiState.copy(postList = Ressources.Error(
                throwable = Throwable(message = "Utilisateur non connecté")
            ))
        }
    }


//===================================================================
//      GET DATA FORM REPOSITORY
//===================================================================

//-------------------------------------------------------------------
//      GET POSTS
//-------------------------------------------------------------------
    private fun getActivePosts(entrepriseId : String) = viewModelScope.launch {
        repository.getActivePosts(entrepriseId).collect{
            postsEntrepriseUiState = postsEntrepriseUiState.copy(postList = it)
        }
    }
    private fun getAllPosts(entrepriseId : String) = viewModelScope.launch {
        repository.getAllPosts(entrepriseId).collect{
            postsEntrepriseUiState = postsEntrepriseUiState.copy(postList = it)
        }
    }
    private fun getInactivePosts(entrepriseId : String) = viewModelScope.launch {
        repository.getInactivePosts(entrepriseId).collect{
            postsEntrepriseUiState = postsEntrepriseUiState.copy(postList = it)
        }
    }





    private fun getPostApplicants(postId: String) = viewModelScope.launch {
        repository.getPostApplicants(postId).collect {
            postsEntrepriseUiState = postsEntrepriseUiState.copy(postApplicantsList = it)
        }
    }

    fun loadPostApplicants(postId: String){
        if (hasUser) {
            if (entrepriseId.isNotBlank())
                getPostApplicants(postId)
        } else {
            postsEntrepriseUiState = postsEntrepriseUiState.copy(
                postApplicantsList = Ressources.Error(
                    throwable = Throwable(message = "Utilisateur non connecté")
                )
            )
        }
    }


    fun updateApplication(
        postId: String,
        userId: String,
        status: String,
        message: String,
        context: Context
    ) {
        if (hasUser) {
            repository.updateApplication(
                postId, userId, status, message
            ) {
                if (it) {
                    Toast.makeText(
                        context,
                        "Réponse envoyée",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        "OOPS! Une erreur est survenue",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


}

data class PostsEntrepriseUiState(
    val isLoading: Boolean = false,
    val entrepriseInformations: CompteEntreprise? = null,
    val postList: Ressources<List<CompteEntreprise.Post>> = Ressources.Loading(),
    val postApplicantsList: Ressources<List<CompteStandard.Application>> = Ressources.Loading()
)