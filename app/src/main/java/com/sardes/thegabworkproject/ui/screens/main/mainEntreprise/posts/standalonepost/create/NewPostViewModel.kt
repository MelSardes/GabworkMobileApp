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

    fun onReponsabilitesChange(competences: List<String>) {
        postUiState = postUiState.copy(competences = competences)
    }
    fun onExperienceChange(experience: String) {
        postUiState = postUiState.copy(experience = experience)
    }
    fun onDomaineChange(domaine: String) {
        postUiState = postUiState.copy(domaine = domaine)
    }


    fun addPost(entrepriseName: String, urlLogo: String?) {
        if (hasUser) {
            repository.addPost(
                postName = postUiState.postName,
                entrepriseId = user!!.uid,
                entrepriseName = entrepriseName,
                urlLogo = urlLogo,
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
                responsabilites = postUiState.competences,
            ) {
                postUiState = postUiState.copy(postAddedStatus = it)
            }
        }
    }

    private fun setEditFields(post: CompteEntreprise.Post) {
        postUiState = postUiState.copy(
            postName = post.postName,
            descriptionEmploi = post.description,
            salaire = post.salary,
            typeEmploi = post.jobType,
            adresse = post.address,
            ville = post.city,
            province = post.province,
            domaine = post.domain,
            experience = post.experience,
            dateLimite = post.limit,
            competences = post.skills,
            reponsabilites = post.responsibilities,
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
    val urlLogo: String? = null,
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
    val reponsabilites: List<String> = emptyList(),
    val comments: List<CompteEntreprise.Post.Review> = emptyList(),
    val totalApplicants: Int = 0,

    val postAddedStatus: Boolean = false,
    val updateAddedPost: Boolean = false,
    val selectedPost: CompteEntreprise.Post? = null,

    val isLoading: Boolean = false,

    val postError: String? = null,
)