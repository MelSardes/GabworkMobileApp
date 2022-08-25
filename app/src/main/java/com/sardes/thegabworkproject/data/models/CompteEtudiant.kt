package com.sardes.thegabworkproject.data.models

import com.google.firebase.Timestamp

data class CompteEtudiant(
    val idCompteEtudiant: String,
    val nomCompteEtudiant: String,
    val prenomCompteEtudiant: String,
    val motDePasseCompteEtudiant: String,
    val sexeCompteEtudiant: String,
    val telephoneCompteEtudiant: String,
    val emailCompteEtudiant: String= "",
    val ville: String,
    val nationaliteCompteEtudiant: String,
    val adressCompteEtudiant: String,
    val urlPhotoProfilCompteEtudiant: String,
    val dateCreationCompteEtudiant: Timestamp,
    val universiteActuelle: String,
    val dateDebut: Timestamp,
    val cycleActuel: String,
    val filliereActuelle: String,
    val typeDeCompte:String = "Etudiant"
) {
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

    data class ExperienceProfilEtudiant(
        val idExperienceEtudiant : Int,
        val idProfilEtudiant : Int,
        val titrePostOccupe : Int,
        val nomEntreprise : Int,
        val idVille: Int,
        val description: Int,
        val dateDdebut : String?,
        val dateFin : String?,
    )
}
