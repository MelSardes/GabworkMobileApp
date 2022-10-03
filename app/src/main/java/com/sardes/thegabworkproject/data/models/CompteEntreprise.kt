package com.sardes.thegabworkproject.data.models

import com.google.firebase.Timestamp

data class CompteEntreprise(
    val entrepriseId: String = "",
    val nom: String = "",
    val activite: String = "",
    val description: String = "",
    val villes: List<String> = emptyList(),
    val email: String = "",
    val telephone: String = "",
    val adresse: String = "",
    val siteWeb: String? = "",
    val employes: String = "",
    val urlLogo: String? = "",
    val dateCreationCompte: Timestamp? = Timestamp.now(),
    val dateCreationEntreprise: String? = null,
    val typeDeCompte:String = "Entreprise",
){
    data class Post(
        val postId: String = "",
        val postName: String = "",
        val entrepriseId: String = "",
        val entrepriseName: String = "",
        val urlLogo: String? = null,
        val creationDate: Timestamp = Timestamp.now(),
        val description: String = "",
        val salary: String = "",
        val city: String = "",
        val province: String = "",
        val domain: String = "",
        val experience: String = "",
        val jobType: String = "",
        val address: String = "",
        val limit: Timestamp? = null,
        val skills: List<String> = emptyList(),
        val responsibilities: List<String> = emptyList(),

        val comments: List<Review> = emptyList(),
        val savers: List<String> = emptyList(),
        val totalApplicants: Int = 0,
        val actif: Boolean = true
    ){
        data class Review(
            val reviewerId: String? = null,
            val urlPhoto: String? = null,
            val reviewerName: String? = null,
            val reviewContent: String? = null,
            val date: Timestamp? = null,
        )

        data class Applicant(
            val PostId: String = "",
            val applicantId: String = "",
            val name: String = "",
            val job: String = "",
            val urlPhoto: String? = null,
            val applyDate: Timestamp? = null,
        )
    }
}