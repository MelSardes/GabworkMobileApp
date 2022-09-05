package com.sardes.thegabworkproject.repository.signuprepository

import android.net.Uri
import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.sardes.thegabworkproject.data.models.CompteIndependant


const val COMPTES_INDEPENDANT_COLLECTION_REF = "ComptesIndependant"
private const val USERS_COLLECTION_REF = "Users"


class IndependantSignUpRepository {

    var storageRef = Firebase.storage.reference

    private val comptesIndependantRef: CollectionReference = Firebase
        .firestore.collection(COMPTES_INDEPENDANT_COLLECTION_REF)
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
        competences: List<String>,
        siteWeb: String,
        dateCreationCompte: Timestamp,
        typeDeCompte:String = "Independant",

        onComplete: (Boolean) -> Unit,
    ) {
        val independantUser = CompteIndependant(
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
            competences,
            siteWeb,
            dateCreationCompte,
            typeDeCompte,
        )

        val userType = hashMapOf(
            "UID" to userId,
            "account" to typeDeCompte
        )



        if (photo != null) {
            storageRef.child(
                "userProfile/independant/${userId}__profile__independant.jpg"
            )
                .putFile(photo)
        }

        comptesIndependantRef
            .document(userId)
            .set(independantUser)
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

