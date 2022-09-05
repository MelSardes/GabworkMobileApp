package com.sardes.thegabworkproject.repository.signuprepository

import android.net.Uri
import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.sardes.thegabworkproject.data.models.CompteDemandeur


private const val COMPTES_DEMANDEUR_COLLECTION_REF = "ComptesDemandeur"
private const val USERS_COLLECTION_REF = "Users"


class SeekerSignUpRepository {

    var storageRef = Firebase.storage.reference

    private val comptesIndependantRef: CollectionReference = Firebase
        .firestore.collection(COMPTES_DEMANDEUR_COLLECTION_REF)

    private val usersRef: CollectionReference = Firebase
        .firestore.collection((USERS_COLLECTION_REF))


    fun addUserInformations(
        userId: String,
        nom: String,
        prenom: String,
        sexe: String,
        telephone: String,
        HQH: String,
        langues: List<String>,
        competences: List<String>,
        email: String,
        ville: String,
        nationalite: String,
        adresse: String,
        photo: Uri?,
        urlPhotoProfil: String,
        dateCreationCompte: Timestamp,
        urlCV: String,
        occupation: String,
        typeDeCompte: String = "Demandeur",

        onComplete: (Boolean) -> Unit,
    ) {
        val seekerUser = CompteDemandeur(
            userId,
            nom,
            prenom,
            HQH,
            langues,
            competences,
            sexe,
            telephone,
            email,
            ville,
            nationalite,
            adresse,
            urlPhotoProfil,
            dateCreationCompte,
            urlCV,
            occupation,
            typeDeCompte,
        )

        val userType = hashMapOf(
            "UID" to userId,
            "account" to typeDeCompte
        )



        if (photo != null) {
            storageRef.child(
                "userProfile/demandeur/${userId}__profile__demandeur.jpg"
            )
                .putFile(photo)
        }

        comptesIndependantRef
            .document(userId)
            .set(seekerUser)
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

