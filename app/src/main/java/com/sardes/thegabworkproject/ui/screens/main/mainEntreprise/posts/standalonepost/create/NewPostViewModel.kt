package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.standalonepost.create

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseUser
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.repository.main.entreprise.MainEntrepriseRepository

class NewPostViewModel(
    private val repository: MainEntrepriseRepository = MainEntrepriseRepository(),
) : ViewModel() {

    var postUiState by mutableStateOf(PostUiState())
        private set

    private val hasUser: Boolean
        get() = repository.hasUser()

    private val user: FirebaseUser?
        get() = repository.user()


    fun onPostNameChange(postName: String) {
        postUiState = postUiState.copy(postName = postName)
    }

    fun onDateCreationPostChange(dateCreationPost: Timestamp) {
        postUiState = postUiState.copy(dateCreationPost = dateCreationPost)
    }

    fun onDescriptionEmploiChange(descriptionEmploi: String) {
        postUiState = postUiState.copy(descriptionEmploi = descriptionEmploi)
    }

    fun onSalaireChange(salaire: String) {
        postUiState = postUiState.copy(salaire = salaire)
    }

    fun onTypeEmploiChange(typeEmploi: String) {
        postUiState = postUiState.copy(typeEmploi = typeEmploi)
    }

    fun onAdresseChange(adresse: String) {
        postUiState = postUiState.copy(adresse = adresse)
    }
    fun onVilleChange(ville: String) {
        postUiState = postUiState.copy(ville = ville)
    }
    fun onProvinceChange(province: String) {
        postUiState = postUiState.copy(province = province)
    }

    fun onDateLimiteChange(dateLimite: Timestamp) {
        postUiState = postUiState.copy(dateLimite = dateLimite)
    }

    fun onSkillsChange(competences: List<String>) {
        postUiState = postUiState.copy(competences = competences)
    }
    fun onExperienceChange(experience: String) {
        postUiState = postUiState.copy(experience = experience)
    }
    fun onDomaineChange(domaine: String) {
        postUiState = postUiState.copy(domaine = domaine)
    }

    fun onEmploiOuStagehange(emploiOuStage: String) {
        postUiState = postUiState.copy(emploiOuStage = emploiOuStage)
    }


    fun addPost() {
        if (hasUser) {
            repository.addPost(
                postName = postUiState.postName,
                entrepriseId = user!!.uid,
                entrepriseName = postUiState.entrepriseName,
                dateCreationPost = postUiState.dateCreationPost,
                descriptionEmploi = postUiState.descriptionEmploi,
                salaire = postUiState.salaire,
                domaine = postUiState.domaine,
                experience = postUiState.experience,
                typeDEmploi = postUiState.typeEmploi,
                adresse = postUiState.adresse,
                ville = postUiState.ville,
                province = postUiState.province,
                dateLimite = postUiState.dateLimite,
                competences = postUiState.competences,
                emploiOuStage = postUiState.emploiOuStage,
            ) {
                postUiState = postUiState.copy(postAddedStatus = it)
            }
        }
    }

    private fun setEditFields(post: CompteEntreprise.Post) {
        postUiState = postUiState.copy(
            postName = post.postName,
            descriptionEmploi = post.descriptionEmploi,
            salaire = post.salaire,
            typeEmploi = post.typeDEmploi,
            adresse = post.adresse,
            ville = post.ville,
            province = post.province,
            domaine = post.domaine,
            experience = post.experience,
            dateLimite = post.dateLimite,
            competences = post.competences,
            emploiOuStage = post.emploiOuStage,
        )
    }

/*
    fun getPost(postId: String) {
        repository.getPost(
            postId = postId,
            onError = {}
        ) {
            postUiState = postUiState.copy(selectedPost = it)
            postUiState.selectedPost?.let { it1 -> setEditFields(it1) }
        }
    }
*/

/*
    fun updatePost(postId: String) {
        repository.updatePost(
            postId = postId,
            postName = postUiState.postName,
            entrepriseId = user!!.uid,
            entrepriseName = postUiState.entrepriseName,
            dateCreationPost = postUiState.dateCreationPost,
            descriptionEmploi = postUiState.descriptionEmploi,
            salaire = postUiState.salaire,
            typeDEmploi = postUiState.typeEmploi,
            adresse = postUiState.adresse,
            ville = postUiState.ville,
            province = postUiState.province,
            domaine = postUiState.domaine,
            experience = postUiState.experience,
            dateLimite = postUiState.dateLimite!!,
            prerequis = postUiState.prerequis,
            emploiOuStage = postUiState.emploiOuStage,
            actif = postUiState.actif
        ) {
            postUiState = postUiState.copy(updateAddedPost = it)
        }
    }
*/

    fun resetPostAddedStatus() {
        postUiState = postUiState.copy(
            postAddedStatus = false,
            updateAddedPost = false
        )
    }

    fun resetState() {
        postUiState = PostUiState()
    }
}


data class PostUiState(
    val postName: String = "",
    val entrepriseName: String = "",
    val dateCreationPost: Timestamp = Timestamp.now(),
    val descriptionEmploi: String = "",
    val salaire: String = "",
    val typeEmploi: String = "",
    val adresse: String = "",
    val ville: String = "",
    val province: String = "",
    val domaine: String = "",
    val experience: String = "",
    val dateLimite: Timestamp? = null,
    val competences: List<String> = emptyList(),
    val emploiOuStage: String = "",

    val postAddedStatus: Boolean = false,
    val updateAddedPost: Boolean = false,
    val selectedPost: CompteEntreprise.Post? = null,

    val isLoading: Boolean = false,

    val postError: String? = null,
)