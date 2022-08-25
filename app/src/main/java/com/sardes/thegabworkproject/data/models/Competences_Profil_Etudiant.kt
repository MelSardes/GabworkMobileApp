package com.sardes.thegabworkproject.data.models

import com.google.firebase.Timestamp

data class Competences_Profil_Etudiant(

    val idCompteStandard: String = "",
    val idCompetenceEtudiant : String = "",
    val competence : String = "",
    val niveauDeCompetence : String = "",
    val timestamp: Timestamp = Timestamp.now()
)
