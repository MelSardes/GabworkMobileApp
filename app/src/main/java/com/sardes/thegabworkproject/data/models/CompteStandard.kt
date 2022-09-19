package com.sardes.thegabworkproject.data.models

import com.google.firebase.Timestamp

data class CompteStandard(
    val userId: String? = null,
    val email: String? = null,

    val nom: String? = null,
    val prenom: String? = null,
    val sexe: String? = null,
    val nationalite: String? = null,
    val dateNaissance: String? = null,

    val telephone: String? = null,
    val ville: String? = null,
    val adresse: String? = null,

    val urlPhoto: String? = null,

    val dateCreationCompte: Timestamp = Timestamp.now(),
    val typeDeCompte: String = "Standard",
){
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
        val postName: String? = null,
        val entrepriseName: String? = null,
        val urlLogoEntreprise: String? = null,
        val salary: String? = null,
        val city: String? = null,
        val jobType: String? = null,
        val applicationDate: Timestamp = Timestamp.now(),
        val status: String? = null
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

data class NecessaryInformations(
    val langues: List<String> = emptyList(),
    val competences: List<Skill> = emptyList(),
    val education: List<Education> = emptyList(),
    val experience: List<Experience> = emptyList(),
    val preferencesDEmploi: List<String> = emptyList(),
    val urlCV: String? = null
)


data class InternshipNeededInformations(
    val universiteActuelle: String? = null,
    val cycleActuel: String? = null,
    val filliereActuelle: String? = null
)

data class SeekerNecessaryInformations(
    val metier: String? = null
)

data class ParcoursEtudiant(
    val idCompteEtudiant: Int,
    val idUniversite: Int,
    val intituleDiplome: String,
    val fichierDiplomeScanne: String,
    val filliere: String?,
    val dateDebut: String?,
    val dateFin: String?,
    val diplomePrepare: String,
    val dateObtentionDiplome:String?,
)


data class ExperienceStandard(
    val idExperienceEtudiant : Int,
    val idProfilEtudiant : Int,
    val titrePostOccupe : Int,
    val nomEntreprise : Int,
    val idVille: Int,
    val description: Int,
    val dateDdebut : String?,
    val dateFin : String?,
)

