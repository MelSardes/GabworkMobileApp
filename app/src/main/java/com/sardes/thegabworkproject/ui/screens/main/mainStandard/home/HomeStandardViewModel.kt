package com.sardes.thegabworkproject.ui.screens.main.mainStandard.home

import android.content.Context
import android.widget.Toast
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

    val userId: String
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

    fun addToBookmarks(
        postId: String,
        entrepriseId: String?,
        postName: String?,
        entrepriseName: String?,
        urlLogo: String?,
        salary: String?,
        city: String?,
        province: String?,
        jobType: String?,
        context: Context
    ){
        if (hasUser){
            repository.addToBookmarks(
                userId,
                postId,
                entrepriseId,
                postName,
                entrepriseName,
                urlLogo,
                salary,
                city,
                province,
                jobType
            ){

                if(it){
                    Toast.makeText(
                        context,
                        "Post sauvegardé",
                        Toast.LENGTH_SHORT
                    ).show()
                    homeStandardUiState = homeStandardUiState.copy(addToBookmarksStatus = it)
                }else{
                    Toast.makeText(
                        context,
                        "Erreur lors de la sauvegarde du post",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    fun removeFromBookmarks(postId: String?){
        if (hasUser){
            repository.removeFromBookmarks(userId, postId){
                homeStandardUiState = homeStandardUiState.copy(removeFromBookmarksStatus = it)
            }
        }
    }


    fun loadAllBookmarks(){
        if (hasUser){
            getAllBookmarks()
        }else{
            homeStandardUiState = homeStandardUiState.copy(bookmarks = Ressources.Error(
                throwable = Throwable(message = "Utilisateur non connecté")
            ))
        }
    }

    private fun getAllBookmarks() = viewModelScope.launch{
            repository.getAllJobBookmarks(userId).collect{
                homeStandardUiState = homeStandardUiState.copy(bookmarks = it)
            }
    }
}

data class HomeStandardUiState(
    val isLoading: Boolean = false,
    val userInformations: CompteStandard? = null,
    val postsList: Ressources<List<CompteEntreprise.Post>> = Ressources.Loading(),
    val fiveLatestPostsList: Ressources<List<CompteEntreprise.Post>> = Ressources.Loading(),
    val bookmarks: Ressources<List<CompteStandard.JobBookmark>> = Ressources.Loading(),

    val entreprises: Ressources<List<CompteEntreprise>> = Ressources.Loading(),

    val selectedPost: CompteEntreprise.Post? = null,

    val addToBookmarksStatus: Boolean = false,
    val removeFromBookmarksStatus: Boolean = false,
)