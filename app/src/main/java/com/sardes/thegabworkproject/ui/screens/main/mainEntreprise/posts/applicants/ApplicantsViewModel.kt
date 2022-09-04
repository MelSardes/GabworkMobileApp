package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.applicants

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseUser
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.repository.main.entreprise.MainEntrepriseRepository
import com.sardes.thegabworkproject.repository.ressources.Ressources
import kotlinx.coroutines.launch

class ApplicantsViewModel (
    private val repository: MainEntrepriseRepository = MainEntrepriseRepository()
): ViewModel() {

    var applicantsUiState by mutableStateOf(ApplicantsUiState())

    private val hasUser: Boolean
        get() = repository.hasUser()

    private val user: FirebaseUser?
        get() = repository.user()



    fun getPost(postId: String){
        repository.getPost(
            postId = postId,
            onError = {}
        ){
            applicantsUiState = applicantsUiState.copy(selectedPost = it)
        }
    }


//-------------------------------------------------------------------
//      GET APPLICANTS DATA
//-------------------------------------------------------------------
    fun getPostApplicants(postId: String) = viewModelScope.launch {
        repository.getPostApplicants(postId).collect{
            applicantsUiState = applicantsUiState.copy(applicantsList = it)
        }
    }
}


data class ApplicantsUiState(
    val PostId: String = "",
    val candidatId: String = "",
    val nomCandidat: String = "",
    val occupationCandidat: String = "",
    val dateCandidature: Timestamp? = null,

    val selectedPost: CompteEntreprise.Post? = null,

    val applicantsList: Ressources<List<CompteEntreprise.Post.Candidat>> = Ressources.Loading()

)