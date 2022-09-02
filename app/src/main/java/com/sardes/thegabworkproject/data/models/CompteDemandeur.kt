package com.sardes.thegabworkproject.data.models

import com.google.firebase.Timestamp

data class CompteDemandeur(
    val idCompteDemandeur: String = "",
    val nom: String = "",
    val prenom: String = "",
    val sexe: String = "",
    val telephone: String = "",
    val email: String= "",
    val ville: String = "",
    val nationalite: String = "",
    val adresse: String = "",
    val urlPhotoProfil: String = "",
    val dateCreationCompte: Timestamp = Timestamp.now(),
    val urlCV: String? = "",
    val occupation: String = "",
    val typeDeCompte:String = "Demandeur",
){

    data class Bookmark(
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
        val descriptionEmploi: String = "",
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


    data class Experience(
        val idExperienceDemandeur: String,
        val TitrePostOccupe: String,
        val nomEntreprise: String,
        val ville: String,
        val description: String,
        val dateDebut: String?,
        val dateFin: String?,
    )


    data class Competences(
        val idCompetenceDemandeur: String,
        val idCompetence: String?,
        val niveauDeCompetence: String,
    )
}