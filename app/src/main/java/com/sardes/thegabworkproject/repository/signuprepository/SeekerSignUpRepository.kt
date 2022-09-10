package com.sardes.thegabworkproject.repository.signuprepository

import android.net.Uri
import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.sardes.thegabworkproject.data.models.CompteDemandeur
import com.sardes.thegabworkproject.data.models.Education
import com.sardes.thegabworkproject.data.models.Experience
import com.sardes.thegabworkproject.data.models.Skill


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
        preferencesDEmploi: List<String>,
        competences: List<Skill>,
        education: List<Education>,
        experience: List<Experience>,
        email: String,
        villes: String,
        nationalite: String,
        adresse: String,
        photo: Uri?,
        urlPhotoProfil: String,
        dateNaissance: String,
        dateCreationCompte: Timestamp,
        urlCV: String,
        metier: String,
        typeDeCompte: String = "Demandeur",

        onComplete: (Boolean) -> Unit,
    ) {
        val seekerUser = CompteDemandeur(
            userId,
            nom,
            prenom,
            HQH,
            langues,
            preferencesDEmploi,
            competences,
            education,
            experience,
            sexe,
            telephone,
            email,
            villes,
            nationalite,
            adresse,
            urlPhotoProfil,
            dateNaissance,
            dateCreationCompte,
            urlCV,
            metier,
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

