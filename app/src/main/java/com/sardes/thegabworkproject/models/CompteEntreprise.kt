package com.sardes.thegabworkproject.models

import com.google.firebase.Timestamp

data class CompteEntreprise(
    val idCompteEntreprise: String = "",
    val nomEntreprise: String = "",
    val secteurDActivite: String = "",
    val descriptionEntreprise: String = "",
    val ville: String = "",
    val emailEntreprise: String = "",
    val telephone: String = "",
    val adressEntreprise: String = "",
    val siteWebEntreprise: String? = "",
    val urlLogoEntreprise: String? = "",
    val dateCreationCompte: Timestamp? = null,
    val dateCreationEntreprise: Timestamp? = Timestamp.now(),
    val typeDeCompte:String = "Entreprise",
){
    data class PostVacant(
        val postId: String = "",
        val postName: String = "",
        val entrepriseId: String = "",
        val entrepriseName: String = "",
        val dateCreationPost: Timestamp = Timestamp.now(),
        val descriptionEmploi: String = "",
        val salaire: String = "",
        val typeDEmploi: String = "",
        val adresse: String = "",
        val dateLimite: Timestamp? = null,
        val prerequis: String = "",
        val emploiOuStage: String = "",
        val actif: Boolean = true
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