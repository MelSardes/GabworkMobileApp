package com.sardes.thegabworkproject.repository.signuprepository

import android.net.Uri
import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.sardes.thegabworkproject.data.models.CompteStandard
import com.sardes.thegabworkproject.data.models.Skill
import com.sardes.thegabworkproject.data.models.UserType


private const val COMPTES_STANDARD_COLLECTION_REF = "ComptesStandard"
private const val USERS_COLLECTION_REF = "Users"


class StandardSignUpRepository {

    private val db = Firebase.firestore

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
            name = nom,
            forename = prenom,
            sex = sexe,
            phone = telephone,
            email = email,
            city = ville,
            nationality = nationalite,
            address = adresse,
            urlPhoto = urlPhotoProfil,
            bornDate = dateNaissance,
            accountCreationDate = dateCreationCompte,
            accountType = typeDeCompte,
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

    fun updateUserInformations(
        userId: String,
        educations: List<CompteStandard.Education>,
        experiences: List<CompteStandard.Experience>,
        HQH: String,
        skills: List<Skill>,
        languages: List<String>,
        urlCV: String = "",
        preferredJob: String,
        wishJobs: List<String>,
        onComplete: (Boolean) -> Unit
    ) {

        val educationRef: CollectionReference = comptesStandardRef
            .document(userId)
            .collection("Education")

        val experienceRef: CollectionReference = comptesStandardRef
            .document(userId)
            .collection("Experience")

        val skillsRef: CollectionReference = comptesStandardRef
            .document(userId)
            .collection("Competences")


        db.runBatch { batch ->
            batch.update(
                comptesStandardRef.document(userId), mapOf(
                    "languages" to languages,
                    "wishJobs" to wishJobs,
//                    "urlCV" to urlCV,
                    "preferredJob" to preferredJob,
                    "HQH" to HQH,
                    "isNecessaryInformationsComplete" to true
                )
            )

            educations.forEach {
                batch.set(educationRef.document(), it)
            }

            experiences.forEach {
                batch.set(experienceRef.document(), it)
            }

            skills.forEach {
                batch.set(skillsRef.document(), it)
            }

        }

/*
        comptesStandardRef.document(userId)
            .update(
                mapOf(
                    "languages" to languages,
//                    "skills" to skills,
//                    "education" to educations,
//                    "experience" to experiences,
                    "wishJobs" to wishJobs,
//                    "urlCV" to urlCV,
                    "preferredJob" to preferredJob,
                    "HQH" to HQH,
                    "isNecessaryInformationsComplete" to true
                )
            )
*/
            .addOnCompleteListener { result ->
                onComplete.invoke(result.isSuccessful)
            }


    }
}

