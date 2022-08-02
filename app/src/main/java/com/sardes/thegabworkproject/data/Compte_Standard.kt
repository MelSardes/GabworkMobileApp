package com.sardes.thegabworkproject.data

data class Compte_Standard(
    val id_compte_standard: Int,
    val nom_compte_standard: String,
    val prenom_compte_standard: String,
    val mot_de_passe_compte_standard: String,
    val sexe_compte_standard: String,
    val telephone_compte_standard: String,
    val email_compte_standard: String,
    val id_ville: Int,
    val nationalite_compte_standard: String,
    val adress_compte_standard: String,
    val photo_de_profil_compte_standard: String,
    val coordonnees_geographique_compte_standard: String?,
    val date_creation_compte_standard: String
){

    data class Profil_Etudiant(
        val id_compte_standard: Int,
        val id_profil_etudiant: Int,
        val id_universite_actuelle: String,
        val date_debut: String,
        val cycle_actuel: String,
        val filliere_actuelle: String,
    ) {
        data class Parcours_Etudiant(
            val id_profil_etudiant: Int,
            val id_universite: Int,
            val intitule_diplome: String,
            val fichier_diplome_scanne: String,
            val filliere: String?,
            val date_debut: String?,
            val date_fin: String?,
            val diplome_prepare: String,
            val date_obtention_diplome:String?,
            val profil_actif: Boolean,
        )


        data class Competances_Profil_Etudiant(
            val id_competance_etudiant : Int,
            val id_profil_demandeur : Int,
            val id_competance : Int?,
            val niveau_de_competance : String,
        )


        data class Experience_Profil_Etudiant(
            val id_experience_etudiant : Int,
            val id_profil_etudiant : Int,
            val Titre_post_occupe : Int,
            val nom_entreprise : Int,
            val id_ville: Int,
            val description: Int,
            val date_debut : String?,
            val date_fin : String?,
        )
    }



    data class Profil_Demandeur(
        val id_compte_standard: Int,
        val id_profil_demandeur: Int,
        val cv_scanee: String?,
        val date_creation_profil: String,
        val profil_actif: Boolean
    ){
        data class Experience_Profil_Demandeur(
            val id_experience_demandeur: Int,
            val id_profil_demandeur: Int,
            val Titre_post_occupe: Int,
            val nom_entreprise: Int,
            val id_ville: Int,
            val description: String,
            val date_debut: String?,
            val date_fin: String?,
        )


        data class Competances_Profil_Demandeur(
            val id_competance_demandeur: Int,
            val id_profil_demandeur: Int,
            val id_competance: Int?,
            val niveau_de_competance: String,
        )
    }
}