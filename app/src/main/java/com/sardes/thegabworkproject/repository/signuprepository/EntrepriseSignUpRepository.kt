package com.sardes.thegabworkproject.repository.signuprepository

import android.net.Uri
import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.sardes.thegabworkproject.data.models.CompteEntreprise


const val COMPTES_ENTREPRISE_COLLECTION_REF = "ComptesEntreprise"
private const val USERS_COLLECTION_REF = "Users"


class EntrepriseSignUpRepository {

    private var storageRef = Firebase.storage.reference


    private val comptesEntrepriseRef: CollectionReference = Firebase
        .firestore.collection(COMPTES_ENTREPRISE_COLLECTION_REF)


    private val usersRef: CollectionReference = Firebase
        .firestore.collection((USERS_COLLECTION_REF))

    fun addEntrepriseInformations(
        entrepriseId: String,
        nom: String,
        activite: String,
        description: String,
        ville: List<String>,
        employes: String,
        email: String,
        telephone: String,
        adresse: String,
        siteWeb: String,
        logoEntreprise: Uri?,
        urlLogo: String,
        dateCreationCompte: Timestamp,
        dateCreationEntreprise: String?,
        typeDeCompte:String = "Entreprise",

        onComplete: (Boolean) -> Unit,
    ) {
        val entreprise = CompteEntreprise(
            entrepriseId,
            nom,
            activite,
            description,
            ville,
            email,
            telephone,
            adresse,
            siteWeb,
            employes,
            urlLogo,
            dateCreationCompte,
            dateCreationEntreprise,
            typeDeCompte,
        )

        val userType = hashMapOf(
            "UID" to entrepriseId,
            "account" to typeDeCompte
        )



        if (logoEntreprise != null) {
            storageRef.child(
                "userProfile/entreprise/${entrepriseId}__profile__entreprise.jpg"
            )
                .putFile(logoEntreprise)
        }

        comptesEntrepriseRef
            .document(entrepriseId)
            .set(entreprise)
            .addOnCompleteListener { result ->
                onComplete.invoke(result.isSuccessful)
            }

        usersRef
            .document(entrepriseId)
            .set(userType)
            .addOnCompleteListener { result ->
                onComplete.invoke(result.isSuccessful)
            }
    }


}