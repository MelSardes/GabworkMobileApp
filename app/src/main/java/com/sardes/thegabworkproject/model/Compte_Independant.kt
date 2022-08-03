package com.sardes.thegabworkproject.model

data class Compte_Independant(
    val id_compte_independant: Int,
    val nom_compte_independant: String,
    val prenom_compte_independant: String,
    val mot_de_passe_compte_independant: String,
    val sexe_compte_independant: String,
    val telephone_compte_independant: String,
    val email_compte_independant: String,
    val id_ville: Int,
    val nationalite_compte_independant: String,
    val adress_compte_independant: String,
    val photo_de_profil_compte_independant: String,
    val coordonnees_geographique_compte_independant: String?,
    val date_creation_compte_independant: String
)