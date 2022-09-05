package com.sardes.thegabworkproject.data.models

import com.google.firebase.Timestamp

data class CompteStandard(
    val userId: String = "",
    val nom: String = "",
    val prenom: String = "",
    val sexe: String = "",
    val telephone: String = "",
    val email: String = "",
    val ville: String = "",
    val nationalite: String = "",
    val adresse: String = "",
    val urlPhoto: String = "",
    val dateCreationCompte: Timestamp = Timestamp.now(),
    val typeDeCompte: String = "Standard",
)