package com.sardes.thegabworkproject.data.models

import com.google.firebase.Timestamp

data class CompteStandard(
    val userId: String? = null,
    val email: String? = null,
    val name: String? = null,
    val forename: String? = null,
    val sex: String? = null,
    val nationality: String? = null,
    val bornDate: String? = null,
    val phone: String? = null,
    val city: String? = null,
    val address: String? = null,
    val urlPhoto: String? = null,

    val isComplete: Int = 0,
    val isInternshipComplete: Int = 0,

    val accountCreationDate: Timestamp = Timestamp.now(),
    val accountType: String = "Standard",

    val preferredJob: String? = null,

    val languages: List<String> = emptyList(),
    val wishJobs: List<String> = emptyList(),
    val urlCV: String? = null,
 

    val actualSchool: String? = null,
    val cycleActuel: String? = null,
    val filliereActuelle: String? = null
){

    data class Education(
        val etablissement: String,
        val diplome: String,
        val domaine: String,
        val description: String,
        val ville: String,
        val dateDebut: String,
        val dateFin: String,
    )

    data class Experience(
        val entreprise: String,
        val position: String,
        val typeDEmploi: String,
        val description: String,
        val ville: String,
        val dateDebut: String,
        val dateFin: String,
    )


    data class JobBookmark(
        val postId: String? = null,
        val entrepriseId: String? = null,
        val postName: String? = null,
        val entrepriseName: String? = null,
        val urlLogo: String? = null,
        val salary: String? = null,
        val city: String? = null,
        val province: String? = null,
        val jobType: String? = null,
        val saveDate: Timestamp = Timestamp.now(),
    )

    data class ServiceBookmark(
        val idBookmark: String? = null,
        val idPost: String? = null,
        val idEntreprise: String? = null,
        val nomEntreprise: String? = null,
        val urlLogoEntreprise: String? = null,
        val salaire: String? = null,
        val dateEnregistrement: Timestamp = Timestamp.now(),
        val ville: String? = null,
        val province: String? = null,
        val typeDEmploi: String? = null,
    )

    data class InternshipBookmark(
        val idBookmark: String? = null,
        val idPost: String? = null,
        val idEntreprise: String? = null,
        val nomEntreprise: String? = null,
        val urlLogoEntreprise: String? = null,
//        val status: String? = null,
        val salaire: String? = null,
        val dateEnregistrement: Timestamp = Timestamp.now(),
        val ville: String? = null,
        val province: String? = null,
        val typeDEmploi: String? = null,
    )


    data class Application(
        val postId: String? = null,
        val applicantId: String = "",
        val applicantName: String = "",
        val postName: String? = null,
        val entrepriseName: String? = null,
        val urlLogoEntreprise: String? = null,
        val salary: String? = null,
        val city: String? = null,
        val jobType: String? = null,
        val applicationDate: Timestamp = Timestamp.now(),
        val urlPhoto: String? = null,
        val status: String? = null,
        val statusMessage: String? = null,
        val coverLetter: String? = null
    )

    data class JobProposal(
        val postId: String? = null,
        val postName: String? = null,
        val entrepriseName: String? = null,
        val urlLogoEntreprise: String? = null,
        val salary: String? = null,
        val city: String? = null,
        val beginDate : String? = null,
        val status: String? = null
    )
}

/*
data class NecessaryInformations(
    val languages: List<String> = emptyList(),
    val skills: List<Skill> = emptyList(),
    val education: List<Education> = emptyList(),
    val experience: List<Experience> = emptyList(),
    val preferredJob: List<String> = emptyList(),
    val urlCV: String? = null
)
*/


/*
data class InternshipNeededInformations(
    val actualSchool: String? = null,
    val cycleActuel: String? = null,
    val filliereActuelle: String? = null
)
*/

/*
data class SeekerNecessaryInformations(
    val preferredJob: String? = null
)
*/

