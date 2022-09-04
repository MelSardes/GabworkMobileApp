package com.sardes.thegabworkproject.data.models

import com.google.firebase.Timestamp

data class CompteEntreprise(
    val entrepriseId: String = "",
    val nom: String = "",
    val activite: String = "",
    val description: String = "",
    val ville: String = "",
    val province: String = "",
    val email: String = "",
    val telephone: String = "",
    val adresse: String = "",
    val siteWeb: String? = "",
    val nombreEmployes: String = "",
    val urlLogo: String? = "",
    val dateCreationCompte: Timestamp? = Timestamp.now(),
    val dateCreationEntreprise: Timestamp? = null,
    val typeDeCompte:String = "Entreprise",
){
    data class Post(
        val postId: String = "",
        val postName: String = "",
        val entrepriseId: String = "",
        val entrepriseName: String = "",
        val dateCreationPost: Timestamp = Timestamp.now(),
        val descriptionEmploi: String = "",
        val salaire: String = "",
        val ville: String = "",
        val province: String = "",
        val domaine: String = "",
        val experience: String = "",
        val typeDEmploi: String = "",
        val adresse: String = "",
        val dateLimite: Timestamp? = null,
        val competences: List<String> = emptyList(),
        val emploiOuStage: String = "",
        val actif: Boolean = true
    ){
        data class CompetencesDuPost(
            val competenceId: String,
            val PostEmploiId: String,
            val niveauDeCompetence: String,
        )


        data class Candidat(
            val PostId: String = "",
            val candidatId: String = "",
            val nomComplet: String = "",
            val occupation: String = "",
            val urlPhoto: String? = null,
            val dateCandidature: Timestamp? = null,
        )

        data class EtudiantCandidat(
            val PostEmploiId: String,
            val ProfilEtudiantId: String,
            val dateSoumission: String,
        )
    }

}