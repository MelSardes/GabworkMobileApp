package com.sardes.thegabworkproject.repository.signuprepository

import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sardes.thegabworkproject.models.Compte_Independant


const val COMPTES_INDEPENDANT_COLLECTION_REF = "ComptesIndependant"


class IndependantSignUpRepository {


    private val comptesIndependantRef: CollectionReference = Firebase
        .firestore.collection(COMPTES_INDEPENDANT_COLLECTION_REF)

    fun addUserInformations(
        userId: String,
        userName: String,
        userForeName: String,
        userPassword: String,
        sex: String,
        phone: String,
        email: String,
        city: String,
        nationality: String,
        address: String,
        urlPhoto: String,
        competences: String,
        website: String,
        timestamp: Timestamp,
        onComplete: (Boolean) -> Unit
    ){
        val standardUser = Compte_Independant(
            userId,
            userName,
            userForeName,
            userPassword,
            sex,
            phone,
            email,
            city,
            nationality,
            address,
            urlPhoto,
            competences,
            website,
            timestamp
        )

        comptesIndependantRef
            .document(userId)
            .set(standardUser)
            .addOnCompleteListener {result ->
                onComplete.invoke(result.isSuccessful)
            }
    }
}

