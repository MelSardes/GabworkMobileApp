package com.sardes.thegabworkproject.data.models

import com.google.firebase.Timestamp

data class CompteStandard(
    val idCompteStandard: String,
    val nomCompteStandard: String,
    val prenomCompteStandard: String,
    val motDePasseCompteStandard: String,
    val sexeCompteStandard: String,
    val telephoneCompteStandard: String,
    val emailCompteStandard: String= "",
    val ville: String,
    val nationaliteCompteStandard: String,
    val adressCompteStandard: String,
    val urlPhotoProfilCompteStandard: String,
    val dateCreationCompteStandard: Timestamp,
    val typeDeCompte:String = "Standard",
    )