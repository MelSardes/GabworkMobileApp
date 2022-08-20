package com.sardes.thegabworkproject.models

import com.google.firebase.Timestamp

data class CompteEntreprise(
    val idCompteEntreprise: String,
    val nomEntreprise: String,
    val motDePasseCompteEntreprise: String,
    val secteurDActivite: String,
    val descriptionEntreprise: String,
    val ville: String,
    val emailEntreprise: String,
    val telephone: String,
    val adressEntreprise: String,
    val siteWebEntreprise: String?,
    val urlLogoEntreprise: String?,
    val dateCreationCompte: Timestamp?,
    val dateCreationEntreprise: Timestamp?,
    val typeDeCompte:String = "Entreprise",
){
    data class PostVacant(
        val EmploiId: String,
        val EntrepriseId: String,
        val dateCreationPost: String,
        val descriptionEmploi: String,
        val salaire: Int,
        val typeDEmploi: String,
        val lieu: String,
        val dateLimite: Timestamp,
        val prerequis: List<String>,
        val emploiOuStage: String,
        val actif: Boolean
    ){
        data class CompetencesDuPost(
            val competenceId: String,
            val PostEmploiId: String,
            val niveauDeCompetence: String,
        )


        data class ListeDemandeurs(
            val PostEmploiId: String,
            val profilDemandeurId: String,
            val dateSoumission: String,
        )

        data class ListeEtudiants(
            val PostEmploiId: String,
            val ProfilEtudiantId: String,
            val dateSoumission: String,
        )
    }

}