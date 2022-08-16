package com.sardes.thegabworkproject.repository.signuprepository

import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sardes.thegabworkproject.models.CompteEntreprise


const val COMPTES_ENTREPRISE_COLLECTION_REF = "ComptesEntreprise"


class EntrepriseSignUpRepository {
    private val comptesEntrepriseRef : CollectionReference = Firebase
        .firestore.collection(COMPTES_ENTREPRISE_COLLECTION_REF)

    fun addEntrepriseInformations(
        entrepriseId: String,
        entrepriseName: String,
        entreprisePassword: String,
        activityArea: String,
        description: String,
        city: String,
        email: String,
        phone: String,
        address: String,
        website: String,
        urlLogo: String,
        timestamp: Timestamp,
        onComplete: (Boolean) -> Unit
    ){
        val entreprise = CompteEntreprise(
            entrepriseId,
            entrepriseName,
            entreprisePassword,
            activityArea,
            description,
            city,
            email,
            phone,
            address,
            website,
            urlLogo,
            timestamp,
        )

        comptesEntrepriseRef
            .document(entrepriseId)
            .set(entreprise)
            .addOnCompleteListener {result ->
                onComplete.invoke(result.isSuccessful)
            }
    }


}