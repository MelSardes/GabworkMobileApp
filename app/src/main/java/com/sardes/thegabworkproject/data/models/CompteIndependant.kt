package com.sardes.thegabworkproject.data.models

import com.google.firebase.Timestamp

data class CompteIndependant(
    val id_compte_independant: String,
    val nom_compte_independant: String,
    val prenom_compte_independant: String,
    val mot_de_passe_compte_independant: String,
    val sexe_compte_independant: String,
    val telephone_compte_independant: String,
    val email_compte_independant: String,
    val ville: String,
    val nationalite_compte_independant: String,
    val adress_compte_independant: String,
    val photo_de_profil_compte_independant: String,
    val competances: String,
    val site_web: String,
    val date_creation_compte_independant: Timestamp,
    val typeDeCompte:String = "Independant"
)