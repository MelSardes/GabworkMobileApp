package com.sardes.thegabworkproject.repository.signuprepository

import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sardes.thegabworkproject.models.Compte_Standard


const val COMPTES_STANDARD_COLLECTION_REF = "ComptesStandard"


class StandardSignUpRepository {


    private val comptesStandardRef: CollectionReference = Firebase
        .firestore.collection(COMPTES_STANDARD_COLLECTION_REF)

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
        timestamp: Timestamp,
        onComplete: (Boolean) -> Unit
    ){
        val standardUser = Compte_Standard(
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
            timestamp
        )

        comptesStandardRef
            .document(userId)
            .set(standardUser)
            .addOnCompleteListener {result ->
                onComplete.invoke(result.isSuccessful)
            }
    }
}

