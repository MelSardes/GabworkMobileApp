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
import com.sardes.thegabworkproject.data.models.Skill
import com.sardes.thegabworkproject.repository.main.standard.MainStandardRepository
import com.sardes.thegabworkproject.repository.ressources.Ressources
import kotlinx.coroutines.launch

class HomeStandardViewModel(
    private val repository: MainStandardRepository = MainStandardRepository()
) : ViewModel() {

    var homeStandardUiState by mutableStateOf(HomeStandardUiState())

    val user = repository.user()
    val hasUser: Boolean
        get() = repository.hasUser()

    val userId: String
        get() = repository.getUserId()

    fun getUserInformations() {
        if (hasUser) {
            if (userId.isNotBlank()) {
                homeStandardUiState = homeStandardUiState.copy(isLoading = true)

                repository.getUserInformations(
                    onError = {},
                    seekerId = userId
                ) {
                    homeStandardUiState = homeStandardUiState.copy(userInformations = it)
                }
                homeStandardUiState = homeStandardUiState.copy(isLoading = false)
            }
        }
    }


    fun getPost(postId: String) {
        repository.getPost(
            postId = postId,
            onError = {}
        ) {
            homeStandardUiState = homeStandardUiState.copy(selectedPost = it)
        }
    }


    fun loadFiveLatestPosts() {
        if (hasUser) {
            getFiveLatestPosts()
        } else {
            homeStandardUiState = homeStandardUiState.copy(
                fiveLatestPostsList = Ressources.Error(
                    throwable = Throwable(message = "Utilisateur non connecté")
                )
            )
        }
    }

    private fun getFiveLatestPosts() = viewModelScope.launch {
        repository.getFiveLatestPosts().collect {
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
    ) {
        if (hasUser) {
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
            ) {

                if (it) {
                    Toast.makeText(
                        context,
                        "Post sauvegardé",
                        Toast.LENGTH_SHORT
                    ).show()
                    homeStandardUiState = homeStandardUiState.copy(addToBookmarksStatus = it)
                } else {
                    Toast.makeText(
                        context,
                        "Erreur lors de la sauvegarde",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    fun removeFromBookmarks(postId: String?, context: Context) {
        if (hasUser) {
            repository.removeFromBookmarks(userId, postId) {
                if (it) {
                    Toast.makeText(
                        context,
                        "Retiré des sauvegardes",
                        Toast.LENGTH_SHORT
                    ).show()
                    homeStandardUiState = homeStandardUiState.copy(removeFromBookmarksStatus = it)
                } else {
                    Toast.makeText(
                        context,
                        "Une erreur s'est produite",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


    fun loadAllBookmarks() {
        if (hasUser) {
            getAllBookmarks()
        } else {
            homeStandardUiState = homeStandardUiState.copy(
                bookmarks = Ressources.Error(
                    throwable = Throwable(message = "Utilisateur non connecté")
                )
            )
        }
    }

    private fun getAllBookmarks() = viewModelScope.launch {
        repository.getAllJobBookmarks(userId).collect {
            homeStandardUiState = homeStandardUiState.copy(bookmarks = it)
        }
    }


    fun addComment(
        post: CompteEntreprise.Post?,
        reviewerId: String?,
        reviewerName: String,
        reviewerContent: String,
        urlPhoto: String,
    ) {
        if (hasUser) {
            if (post != null) {
                if (reviewerId != null) {
                    setAddComment(
                        post,
                        reviewerId,
                        reviewerName,
                        reviewerContent,
                        urlPhoto
                    )
                }
            }
        }
    }

    private fun setAddComment(
        post: CompteEntreprise.Post,
        reviewerId: String,
        reviewerName: String,
        reviewerContent: String,
        urlPhoto: String,
    ) {
        repository.addComment(
            post.postId,
            reviewerId,
            reviewerName,
            reviewerContent,
            urlPhoto
        ) {
            homeStandardUiState = homeStandardUiState.copy(commentAddedStatus = it)
        }
    }


    private fun additionalInformationsValidation() = homeStandardUiState.HQH.isNullOrBlank() &&
            homeStandardUiState.languages.isEmpty() &&
            homeStandardUiState.skills.isEmpty() &&
            homeStandardUiState.education.isEmpty() &&
            homeStandardUiState.experience.isEmpty() &&
            homeStandardUiState.preferredJob.isNullOrBlank() &&
            homeStandardUiState.wishJobs.isEmpty()


    private fun internshipInforamtionsValidation() =
        homeStandardUiState.actualSchool.isNullOrBlank() &&
                homeStandardUiState.cycleActuel.isNullOrBlank() &&
                homeStandardUiState.filliereActuelle.isNullOrBlank()


    fun addInternshipNeededInformations(context: Context){
        if (hasUser){
            if(internshipInforamtionsValidation()){
                throw IllegalArgumentException("Vous dévez renseigner tous les champs")
            }else{
                repository.addInternshipNeededInformations(
                    userId = userId,
                    actualSchool = homeStandardUiState.actualSchool!!,
                    cycleActuel = homeStandardUiState.cycleActuel!!,
                    filliereActuelle = homeStandardUiState.filliereActuelle!!
                ){
                    if (it) {
                        Toast.makeText(
                            context,
                            "Vous pourrez désormais postuler à toutes les offres de stages",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Une erreur s'est produite lors de l'ajout de vos données.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    fun addAdditionalInformations(context: Context) {
        if (hasUser) {
            if (additionalInformationsValidation()) {
                throw IllegalArgumentException("Vous dévez renseigner tous les champs")
            } else {
                homeStandardUiState = homeStandardUiState.copy(isLoading = true)
                repository.addAdditionalInformations(
                    userId = userId,
                    experiences = homeStandardUiState.experience,
                    educations = homeStandardUiState.education,
                    HQH = homeStandardUiState.HQH!!,
                    skills = homeStandardUiState.skills,
                    languages = homeStandardUiState.languages,
                    preferredJob = homeStandardUiState.preferredJob!!,
                    wishJobs = homeStandardUiState.wishJobs
                ) {
                    homeStandardUiState = homeStandardUiState.copy(isLoading = true)

                    if (it) {
                        Toast.makeText(
                            context,
                            "Vous pourrez désormais postuler à toutes les offres d'emploi",
                            Toast.LENGTH_LONG
                        ).show()
                        homeStandardUiState = homeStandardUiState.copy(additionalInformationsAddedStatus = true)
                    } else {
                        Toast.makeText(
                            context,
                            "Une erreur s'est produite lors de l'ajout de vos données.",
                            Toast.LENGTH_LONG
                        ).show()

                    }
                }
            }
        }
    }

    fun addApplication(
        userId: String,
        postId: String,
        postName: String,
        entrepriseName: String,
        urlLogo: String,
        salary: String,
        coverLetter: String,
        city: String,
        jobType: String,
        applicantName: String,
        urlPhoto: String,
        context: Context
    ){
        if (hasUser){
            repository.addApplication(
                userId = userId,
                postId = postId,
                postName = postName,
                entrepriseName = entrepriseName,
                urlLogoEntreprise = urlLogo,
                salary = salary,
                coverLetter = coverLetter,
                city = city,
                jobType = jobType,
                applicantName = applicantName,
                urlPhoto = urlPhoto,
            ){
                if (it) {
                    Toast.makeText(
                        context,
                        "Post sauvegardé",
                        Toast.LENGTH_SHORT
                    ).show()
                    homeStandardUiState = homeStandardUiState.copy(addToBookmarksStatus = it)
                } else {
                    Toast.makeText(
                        context,
                        "Erreur lors de la sauvegarde du post",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }
    }


    private fun getAllApplications() = viewModelScope.launch {
        repository.getAllApplications(userId).collect{
            homeStandardUiState = homeStandardUiState.copy(applications = it)
        }
    }

    fun loadAllApplications() {
        if (hasUser){
            getAllApplications()
        }else{
            homeStandardUiState = homeStandardUiState.copy(
                applications = Ressources.Error(
                    throwable = Throwable(message = "Utilisateur non connecté")
                )
            )
        }
    }





    fun onCommentChange(comment: String?) {
        homeStandardUiState = homeStandardUiState.copy(comment = comment)
    }


    fun onPreferredJobChange(preferredJob: String) {
        homeStandardUiState = homeStandardUiState.copy(preferredJob = preferredJob)
    }

    fun onCompetencesChange(skills: List<Skill>) {
        homeStandardUiState = homeStandardUiState.copy(skills = skills)
    }

    fun onHQHChange(HQH: String) {
        homeStandardUiState = homeStandardUiState.copy(HQH = HQH)
    }

    fun onPreferencesDEmploiChange(wishJobs: List<String>) {
        homeStandardUiState = homeStandardUiState.copy(wishJobs = wishJobs)
    }

    fun onLanguagesChange(languages: List<String>) {
        homeStandardUiState = homeStandardUiState.copy(languages = languages)
    }

    fun onEducationChange(education: List<CompteStandard.Education>) {
        homeStandardUiState = homeStandardUiState.copy(education = education)
    }

    fun onExperienceChange(experience: List<CompteStandard.Experience>) {
        homeStandardUiState = homeStandardUiState.copy(experience = experience)
    }

    fun onCoverLetterChange(coverLetter: String){
        homeStandardUiState = homeStandardUiState.copy(coverLetter = coverLetter)
    }

}

data class HomeStandardUiState(
    val comment: String? = null,
    val userInformations: CompteStandard? = null,

    val isLoading: Boolean = false,
    val applications: Ressources<List<CompteStandard.Application>> = Ressources.Loading(),
    val postsList: Ressources<List<CompteEntreprise.Post>> = Ressources.Loading(),
    val fiveLatestPostsList: Ressources<List<CompteEntreprise.Post>> = Ressources.Loading(),
    val bookmarks: Ressources<List<CompteStandard.JobBookmark>> = Ressources.Loading(),

    val entreprises: Ressources<List<CompteEntreprise>> = Ressources.Loading(),

    val selectedPost: CompteEntreprise.Post? = null,

    val additionalInformationsAddedStatus: Boolean = false,

    val addToBookmarksStatus: Boolean = false,
    val commentAddedStatus: Boolean = false,
    val removeFromBookmarksStatus: Boolean = false,

    val HQH: String? = null,

    val languages: List<String> = emptyList(),
    val skills: List<Skill> = emptyList(),
    val education: List<CompteStandard.Education> = emptyList(),
    val experience: List<CompteStandard.Experience> = emptyList(),

    val urlCV: String? = null,
    val preferredJob: String? = null,
    val wishJobs: List<String> = emptyList(),


    val actualSchool: String? = null,
    val cycleActuel: String? = null,
    val filliereActuelle: String? = null,

    val coverLetter: String = "",
)