package com.sardes.thegabworkproject.data

data class Compte_Entreprise(
    val id_compte_entreprise: Int,
    val nom_entreprise: String,
    val mot_de_passe_compte_entreprise: String,
    val id_secteur_d_activite: Int,
    val description_entreprise: String,
    val id_ville: Int,
    val email_entreprise: String,
    val adress_entreprise: String,
    val site_web_entreprise: String?,
    val date_creation_entreprise: String?,
    val coordonnees_geographiques_entreprise: String?
){

    data class Post_Emploi(
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
        data class Competances_Du_Post(
            val id_competance: Int,
            val id_post_emploi: Int,
            val niveau_de_competance: String,
        )


        data class Liste_Demandeurs(
            val id_post_emploi: Int,
            val id_profil_demandeur: Int,
            val date_soumission: String,
        )

        data class Liste_Etudiants(
            val id_post_emploi: Int,
            val id_profil_etudiant: Int,
            val date_soumission: String,
        )
    }

}