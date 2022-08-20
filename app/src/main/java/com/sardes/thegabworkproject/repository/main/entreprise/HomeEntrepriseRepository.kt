package com.sardes.thegabworkproject.repository.main.entreprise

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sardes.thegabworkproject.models.CompteEntreprise


const val COMPTES_ENTREPRISE_REF = "ComptesEntreprise"

class HomeEntrepriseRepository {

    fun user() = Firebase.auth.currentUser
    fun hasUser(): Boolean = Firebase.auth.currentUser != null

    fun getUserId(): String = Firebase.auth.currentUser?.uid.orEmpty()

    private val informationsRef: CollectionReference = Firebase
        .firestore.collection(COMPTES_ENTREPRISE_REF)


    fun getInformations(
        entrepriseId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (CompteEntreprise?) -> Unit
    ){
        informationsRef
            .document(entrepriseId)
            .get()
            .addOnSuccessListener {
                onSuccess.invoke(it?.toObject(CompteEntreprise::class.java))
            }
            .addOnFailureListener{result ->
                result.cause?.let { onError.invoke(it) }

            }
    }


}