package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.standalonepost.create

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseUser
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.repository.main.entreprise.PostsEntrepriseRepository

class NewPostViewModel(
    private val repository: PostsEntrepriseRepository = PostsEntrepriseRepository(),
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

    fun onDateLimiteChange(dateLimite: Timestamp) {
        postUiState = postUiState.copy(dateLimite = dateLimite)
    }

    fun onPrerequisChange(prerequis: String) {
        postUiState = postUiState.copy(prerequis = prerequis)
    }

    fun onEmploiOuStagehange(emploiOuStage: String) {
        postUiState = postUiState.copy(emploiOuStage = emploiOuStage)
    }

    fun onActiveChange(actif: Boolean) {
        postUiState = postUiState.copy(actif = actif)
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
                typeDEmploi = postUiState.typeEmploi,
                adresse = postUiState.adresse,
                dateLimite = postUiState.dateLimite,
                prerequis = postUiState.prerequis,
                emploiOuStage = postUiState.emploiOuStage,
                actif = postUiState.actif
            ) {
                postUiState = postUiState.copy(postAddedStatus = it)
            }
        }
    }

    private fun setEditFields(post: CompteEntreprise.PostVacant) {
        postUiState = postUiState.copy(
            postName = post.postName,
            descriptionEmploi = post.descriptionEmploi,
            salaire = post.salaire,
            typeEmploi = post.typeDEmploi,
            adresse = post.adresse,
            dateLimite = post.dateLimite,
            prerequis = post.prerequis,
            emploiOuStage = post.emploiOuStage,
        )
    }

    fun getPost(postId: String) {
        repository.getPost(
            postId = postId,
            onError = {}
        ) {
            postUiState = postUiState.copy(selectedPost = it)
            postUiState.selectedPost?.let { it1 -> setEditFields(it1) }
        }
    }

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
            dateLimite = postUiState.dateLimite!!,
            prerequis = postUiState.prerequis,
            emploiOuStage = postUiState.emploiOuStage,
            actif = postUiState.actif
        ) {
            postUiState = postUiState.copy(updateAddedPost = it)
        }
    }

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
    val dateLimite: Timestamp? = null,
    val prerequis: String = "",
    val emploiOuStage: String = "",
    val actif: Boolean = false,

    val postAddedStatus: Boolean = false,
    val updateAddedPost: Boolean = false,
    val selectedPost: CompteEntreprise.PostVacant? = null,

    val isLoading: Boolean = false,

    val postError: String? = null,
)