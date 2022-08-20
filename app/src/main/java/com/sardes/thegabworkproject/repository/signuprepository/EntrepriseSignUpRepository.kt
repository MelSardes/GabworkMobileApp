package com.sardes.thegabworkproject.repository.signuprepository

import android.net.Uri
import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.sardes.thegabworkproject.models.CompteEntreprise


const val COMPTES_ENTREPRISE_COLLECTION_REF = "ComptesEntreprise"



class EntrepriseSignUpRepository {

    var storageRef = Firebase.storage.reference


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
        Logo: Uri?,
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

        if (Logo != null) {
            storageRef.child(
                "userProfile/entreprise/${entrepriseId}__profile__entreprise.jpg")
                .putFile(Logo)
        }

        comptesEntrepriseRef
            .document(entrepriseId)
            .set(entreprise)
            .addOnCompleteListener {result ->
                onComplete.invoke(result.isSuccessful)
            }


    }


}