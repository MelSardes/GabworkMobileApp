package com.sardes.thegabworkproject.repository.signuprepository

import android.net.Uri
import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.sardes.thegabworkproject.data.models.CompteStandard
import com.sardes.thegabworkproject.data.models.UserType


private const val COMPTES_STANDARD_COLLECTION_REF = "ComptesStandard"
private const val USERS_COLLECTION_REF = "Users"


class StandardSignUpRepository {

    private var storageRef = Firebase.storage.reference

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
        photo: Uri?,
        urlPhotoProfil: String,
        dateNaissance: String,
        dateCreationCompte: Timestamp,
        typeDeCompte: String = "Standard",

        onComplete: (Boolean) -> Unit,
    ) {
        val seekerUser = CompteStandard(
            userId = userId,
            nom = nom,
            prenom = prenom,
            sexe = sexe,
            telephone = telephone,
            email = email,
            ville = ville,
            nationalite = nationalite,
            adresse = adresse,
            urlPhoto = urlPhotoProfil,
            dateNaissance = dateNaissance,
            dateCreationCompte = dateCreationCompte,
            typeDeCompte = typeDeCompte,
        )

        val userType = UserType(userId, typeDeCompte)



        if (photo != null) {
            storageRef.child(
                "userProfile/standard/${userId}__profile.jpg"
            )
                .putFile(photo)
        }

        comptesStandardRef
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

