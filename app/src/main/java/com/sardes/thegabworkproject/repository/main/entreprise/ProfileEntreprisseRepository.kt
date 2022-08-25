package com.sardes.thegabworkproject.repository.main.entreprise

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sardes.thegabworkproject.data.models.CompteEntreprise

const val ENTREPRISE_COLLECTION_REF = "ComptesEntreprise"

class ProfileEntreprisseRepository {

    fun user() = Firebase.auth.currentUser
    fun hasUser(): Boolean = Firebase.auth.currentUser != null

    fun getUserId(): String = Firebase.auth.currentUser?.uid.orEmpty()

    private val entreprise_ref: CollectionReference = Firebase
        .firestore.collection(ENTREPRISE_COLLECTION_REF)


    fun getEntrepriseInformations(
        entrepriseId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (CompteEntreprise?) -> Unit
    ){
        entreprise_ref
            .document(entrepriseId)
            .get()
            .addOnSuccessListener {
                onSuccess.invoke(it?.toObject(CompteEntreprise::class.java))
            }
            .addOnFailureListener { result ->
                result.cause?.let { onError.invoke(it) }
            }
    }

    fun updateData(
        idCompteEntreprise: String,
        nomEntreprise: String,
        secteurDActivite: String,
        descriptionEntreprise: String,
        ville: String,
        emailEntreprise: String,
        telephone: String,
        adresseEntreprise: String,
        siteWebEntreprise: String,
        urlLogoEntreprise: String,
        dateCreationCompte: com.google.firebase.Timestamp,
        dateCreationEntreprise: com.google.firebase.Timestamp,
        typeDeCompte: String,
        onResult: (Boolean) -> Unit
    ){
        val updateData = hashMapOf<String, Any>(
            "idCompteEntreprise" to idCompteEntreprise,
            "nomEntreprise" to nomEntreprise,
            "secteurDActivite" to secteurDActivite,
            "descriptionEntreprise" to descriptionEntreprise,
            "ville" to ville,
            "emailEntreprise" to emailEntreprise,
            "telephone" to telephone,
            "adresseEntreprise" to adresseEntreprise,
            "siteWebEntreprise" to siteWebEntreprise,
            "urlLogoEntreprise" to urlLogoEntreprise,
            "dateCreationCompte" to dateCreationCompte,
            "dateCreationEntreprise" to dateCreationEntreprise,
            "typeDeCompte" to typeDeCompte,
        )

        entreprise_ref.document(idCompteEntreprise)
            .update(updateData)
            .addOnCompleteListener{
                onResult(it.isSuccessful)
            }

    }

}