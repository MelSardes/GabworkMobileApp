package com.sardes.thegabworkproject.data.models

import androidx.annotation.ColorLong
import androidx.compose.ui.graphics.Color
import com.sardes.thegabworkproject.ui.theme.TailwindCSSColor
import com.sardes.thegabworkproject.ui.theme.orange

//data class CompteDemandeur(
//    val userId: String = "",
//    val nom: String = "",
//    val prenom: String = "",
//    val HQH: String = "",
//    val langues: List<String> = emptyList(),
//    val preferencesDEmploi: List<String> = emptyList(),
//    val competences: List<Skill> = emptyList(),
//    val education: List<Education> = emptyList(),
//    val experience: List<Experience> = emptyList(),
//    val sexe: String = "",
//    val telephone: String = "",
//    val email: String= "",
//    val ville: String = "",
//    val nationalite: String = "",
//    val adresse: String = "",
//    val urlPhoto: String = "",
//    val dateNaissance: String = "",
//    val dateCreationCompte: Timestamp = Timestamp.now(),
//    val urlCV: String? = "",
//    val metier: String = "",
//    val typeDeCompte:String = "Demandeur",
//){
//
//    data class Bookmark(
//        val idBookmark: String = "",
//        val idPost: String = "",
//        val idEntreprise: String = "",
//        val nomEntreprise: String = "",
//        val urlLogoEntreprise: String = "",
////        val status: String = "",
//        val salaire: String = "",
//        val dateEnregistrement: Timestamp = Timestamp.now(),
//        val ville: String = "",
//        val province: String = "",
//        val typeDEmploi: String = "",
//    )
//
//    data class Candidature(
//        val candidatureId: String = "",
//        val postId: String = "",
//        val postName: String = "",
//        val entrepriseId: String = "",
//        val entrepriseName: String = "",
//        val descriptionEmploi: String = "",
//        val salaire: String = "",
//        val ville: String = "",
//        val province: String = "",
//        val domaine: String = "",
//        val experience: String = "",
//        val typeDEmploi: String = "",
//        val adresse: String = "",
//        val dateCandidature: Timestamp = Timestamp.now(),
//        val dateLimite: Timestamp? = null,
//        val prerequis: String = "",
//        val status: String = ""
//    )


/*
    data class Experience(
        val position: String,
        val entreprise: String,
        val typeDEmploi: String,
        val ville: String,
        val description: String,
        val dateDebut: String?,
        val dateFin: String?,
    )
*/

/*
    data class Education(
        val etablissement: String,
        val diplome: String,
        val ville: String,
        val domaine: String,
        val description: String,
        val dateDebut: String?,
        val dateFin: String?,
    )
*/

//
//    data class Competences(
//        val nomCompetence: String,
//        val niveau: String,
//    )
//}

sealed class Niveau(val titre: String, @ColorLong val couleur: Color){
    object DEBUTANT: Niveau("Débutant", TailwindCSSColor.Blue500)
    object INTERMEDIAIRE: Niveau("Intermédiaire", TailwindCSSColor.Green500)
    object EXPERIMENTE: Niveau("Expérimenté", TailwindCSSColor.Yellow500)
    object AVANCE: Niveau("Avancé", orange)
    object EXPERT: Niveau("Expert", TailwindCSSColor.Red500)
}
