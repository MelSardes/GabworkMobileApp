package com.sardes.thegabworkproject.models

import com.google.firebase.Timestamp

data class Competences_Profil_Etudiant(

    val id_compte_standard: String = "",
    val id_competence_etudiant : String = "",
    val competence : String = "",
    val niveau_de_competence : String = "",
    val timestamp: Timestamp = Timestamp.now()
)
