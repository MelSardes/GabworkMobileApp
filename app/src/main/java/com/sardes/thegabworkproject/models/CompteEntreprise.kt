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
    val dateCreationEntreprise: Timestamp?,
    val typeDeCompte:String = "Entreprise",
){
    data class PostEmploi(
        val id_emploi: Int,
        val id_entreprise: Int,
        val date_creation_post: String,
        val description_emploi: String,
        val salaire: Int,
        val temps_de_travail: Int,
        val type_d_emploi: Int,
        val nombre_de_posts: Int,
        val date_limite: String,
        val actif: Boolean,
        val legitimite: String
    ){
        data class CompetencesDuPost(
            val id_competence: Int,
            val id_post_emploi: Int,
            val niveau_de_competence: String,
        )


        data class ListeDemandeurs(
            val id_post_emploi: Int,
            val id_profil_demandeur: Int,
            val date_soumission: String,
        )

        data class ListeEtudiants(
            val id_post_emploi: Int,
            val id_profil_etudiant: Int,
            val date_soumission: String,
        )
    }

}