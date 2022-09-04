package com.sardes.thegabworkproject.repository.signuprepository

import android.net.Uri
import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.sardes.thegabworkproject.data.models.CompteStandard


const val COMPTES_STANDARD_COLLECTION_REF = "ComptesStandard"
private const val USERS_COLLECTION_REF = "Users"


class StandardSignUpRepository {

    var storageRef = Firebase.storage.reference

    private val comptesStandardRef: CollectionReference = Firebase
        .firestore.collection(COMPTES_STANDARD_COLLECTION_REF)


    private val usersRef: CollectionReference = Firebase
        .firestore.collection((USERS_COLLECTION_REF))


    fun addUserInformations(
        userId: String,
        nom: String,
        prenom: String,
        sexe: String,
        telephone: String,
        email: String,
        ville: String,
        nationalite: String,
        adresse: String,
        urlPhoto: String,
        photo: Uri?,
        dateCreationCompte: Timestamp,
        typeDeCompte:String = "Standard",

        onComplete: (Boolean) -> Unit,
    ) {
        val standardUser = CompteStandard(
            userId,
            nom,
            prenom,
            sexe,
            telephone,
            email,
            ville,
            nationalite,
            adresse,
            urlPhoto,
            dateCreationCompte,
            typeDeCompte,
        )

        val userType = hashMapOf(
            "UID" to userId,
            "account" to typeDeCompte
        )




        if (photo != null) {
            storageRef.child(
                "userProfile/standard/${userId}__profile__standard.jpg"
            )
                .putFile(photo)
        }

        comptesStandardRef
            .document(userId)
            .set(standardUser)
            .addOnCompleteListener { result ->
                onComplete.invoke(result.isSuccessful)
            }


        usersRef
            .document(userId)
            .set(userType)
            .addOnCompleteListener { result ->
                onComplete.invoke(result.isSuccessful)
            }

    }
}

