package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.applicants

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseUser
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.repository.main.entreprise.MainEntrepriseRepository

class ApplicantsViewModel (
    private val repository: MainEntrepriseRepository = MainEntrepriseRepository()
){

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



}


data class ApplicantsUiState(
    val PostId: String = "",
    val candidatId: String = "",
    val nomCandidat: String = "",
    val occupationCandidat: String = "",
    val dateCandidature: Timestamp? = null,

    val selectedPost: CompteEntreprise.Post? = null,


)