package com.sardes.thegabworkproject.models

import com.google.firebase.Timestamp

data class ProfilDemandeur(
    val idCompteDemandeur: String,
    val nomCompteDemandeur: String,
    val prenomCompteDemandeur: String,
    val motDePasseCompteDemandeur: String,
    val sexeCompteDemandeur: String,
    val telephoneCompteDemandeur: String,
    val emailCompteDemandeur: String= "",
    val ville: String,
    val nationaliteCompteDemandeur: String,
    val adressCompteDemandeur: String,
    val urlPhotoProfilCompteDemandeur: String,
    val dateCreationCompteDemandeur: Timestamp,
    val idCompteStandard: Int,
    val cvScanee: String?,
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