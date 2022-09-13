package com.sardes.thegabworkproject.data.models

import com.google.firebase.Timestamp

data class CompteStandard(
    val userId: String = "",
    val email: String = "",

    val nom: String = "",
    val prenom: String = "",
    val sexe: String = "",
    val nationalite: String = "",
    val dateNaissance: String = "",

    val telephone: String = "",
    val ville: String = "",
    val adresse: String = "",

    val urlPhoto: String = "",

    val dateCreationCompte: Timestamp = Timestamp.now(),
    val typeDeCompte: String = "Standard",
){
    data class JobBookmark(
        val idBookmark: String = "",
        val idPost: String = "",
        val idEntreprise: String = "",
        val nomEntreprise: String = "",
        val urlLogoEntreprise: String = "",
        val salaire: String = "",
        val dateEnregistrement: Timestamp = Timestamp.now(),
        val ville: String = "",
        val province: String = "",
        val typeDEmploi: String = "",
    )

    data class ServiceBookmark(
        val idBookmark: String = "",
        val idPost: String = "",
        val idEntreprise: String = "",
        val nomEntreprise: String = "",
        val urlLogoEntreprise: String = "",
        val salaire: String = "",
        val dateEnregistrement: Timestamp = Timestamp.now(),
        val ville: String = "",
        val province: String = "",
        val typeDEmploi: String = "",
    )

    data class InternshipBookmark(
        val idBookmark: String = "",
        val idPost: String = "",
        val idEntreprise: String = "",
        val nomEntreprise: String = "",
        val urlLogoEntreprise: String = "",
//        val status: String = "",
        val salaire: String = "",
        val dateEnregistrement: Timestamp = Timestamp.now(),
        val ville: String = "",
        val province: String = "",
        val typeDEmploi: String = "",
    )


    data class Candidature(
        val candidatureId: String = "",
        val postId: String = "",
        val postName: String = "",
        val entrepriseId: String = "",
        val entrepriseName: String = "",
        val descriptionPost: String = "",
        val salaire: String = "",
        val ville: String = "",
        val province: String = "",
        val domaine: String = "",
        val experience: String = "",
        val typeDEmploi: String = "",
        val adresse: String = "",
        val dateCandidature: Timestamp = Timestamp.now(),
        val dateLimite: Timestamp? = null,
        val prerequis: String = "",
        val status: String = ""
    )


}

data class NecessaryInformations(
    val langues: List<String> = emptyList(),
    val competences: List<Skill> = emptyList(),
    val education: List<Education> = emptyList(),
    val experience: List<Experience> = emptyList(),
    val preferencesDEmploi: List<String> = emptyList(),
    val urlCV: String? = ""
)


data class InternshipNeededInformations(
    val universiteActuelle: String = "",
    val cycleActuel: String = "",
    val filliereActuelle: String = ""
)

data class SeekerNecessaryInformations(
    val metier: String = ""
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

