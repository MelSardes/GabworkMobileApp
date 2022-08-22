package com.sardes.thegabworkproject.models

import com.google.firebase.Timestamp

data class CompteDemandeur(
    val idCompteDemandeur: String = "",
    val nom: String = "",
    val prenom: String = "",
    val motDePasse: String = "",
    val sexe: String = "",
    val telephone: String = "",
    val email: String= "",
    val ville: String = "",
    val nationalite: String = "",
    val adress: String = "",
    val urlPhotoProfil: String = "",
    val dateCreation: Timestamp = Timestamp.now(),
    val urlCV: String? = "",
    val occupation: String = "",
    val typeDeCompte:String = "Demandeur",
){
    data class Experience_Profil_Demandeur(
        val idExperienceDemandeur: Int,
        val TitrePostOccupe: Int,
        val nomEntreprise: Int,
        val ville: String,
        val description: String,
        val dateDebut: String?,
        val dateFin: String?,
    )


    data class Competences_Profil_Demandeur(
        val idCompetenceDemandeur: Int,
        val idCompetence: Int?,
        val niveauDeCompetence: String,
    )
}