package com.sardes.thegabworkproject.repository.signuprepository

import android.net.Uri
import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.sardes.thegabworkproject.data.models.CompteEtudiant


private val COMPTES_ETUDIANT_COLLECTION_REF = "ComptesDemandeur"
private const val USERS_COLLECTION_REF = "Users"


class EtudiantSignUpRepository {

    var storageRef = Firebase.storage.reference

    private val comptesIndependantRef: CollectionReference = Firebase
        .firestore.collection(COMPTES_ETUDIANT_COLLECTION_REF)

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
        urlPhoto: String,
        dateCreationCompte: Timestamp,
        universiteActuelle: String,
        dateDebut: Timestamp,
        cycleActuel: String,
        filliereActuelle: String,
        typeDeCompte: String = "Etudiant",
        onComplete: (Boolean) -> Unit,
    ) {
        val studentUser = CompteEtudiant(
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
            universiteActuelle,
            dateDebut,
            cycleActuel,
            filliereActuelle,
            typeDeCompte,
        )

        val userType = hashMapOf(
            "UID" to userId,
            "account" to typeDeCompte
        )



        if (photo != null) {
            storageRef.child(
                "userProfile/etudiant/${userId}__profile__etudiant.jpg"
            )
                .putFile(photo)
        }

        comptesIndependantRef
            .document(userId)
            .set(studentUser)
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

