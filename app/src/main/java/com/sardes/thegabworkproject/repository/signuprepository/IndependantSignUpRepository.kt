package com.sardes.thegabworkproject.repository.signuprepository

import android.net.Uri
import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.sardes.thegabworkproject.models.CompteIndependant


const val COMPTES_INDEPENDANT_COLLECTION_REF = "ComptesIndependant"


class IndependantSignUpRepository {

    var storageRef = Firebase.storage.reference

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
        photo: Uri?,
        competences: String,
        website: String,
        timestamp: Timestamp,
        onComplete: (Boolean) -> Unit
    ){
        val independantUser = CompteIndependant(
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

        if (photo != null) {
            storageRef.child(
                "userProfile/standard/${userId}__profile__independant.jpg")
                .putFile(photo)
        }

        comptesIndependantRef
            .document(userId)
            .set(independantUser)
            .addOnCompleteListener {result ->
                onComplete.invoke(result.isSuccessful)
            }
    }
}

