package com.sardes.thegabworkproject.repository.signuprepository

import android.net.Uri
import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.sardes.thegabworkproject.data.models.CompteStandard


const val COMPTES_STANDARD_COLLECTION_REF = "ComptesStandard"


class StandardSignUpRepository {

    var storageRef = Firebase.storage.reference

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
        photo: Uri?,
        timestamp: Timestamp,
        onComplete: (Boolean) -> Unit
    ){
        val standardUser = CompteStandard(
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
        if (photo != null) {
            storageRef.child(
                "userProfile/standard/${userId}__profile__standard.jpg")
                .putFile(photo)
        }

        comptesStandardRef
            .document(userId)
            .set(standardUser)
            .addOnCompleteListener {result ->
                onComplete.invoke(result.isSuccessful)
            }
    }
}

