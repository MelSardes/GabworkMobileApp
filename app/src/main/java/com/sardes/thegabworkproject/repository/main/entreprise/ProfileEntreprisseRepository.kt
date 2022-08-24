package com.sardes.thegabworkproject.repository.main.entreprise

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

const val ENTREPRISE_COLLECTION_REF = "ComptesEntreprise"

class ProfileEntreprisseRepository {

    fun user() = Firebase.auth.currentUser
    fun hasUser(): Boolean = Firebase.auth.currentUser != null

    fun getUserId(): String = Firebase.auth.currentUser?.uid.orEmpty()

    private val postsRef: CollectionReference = Firebase
        .firestore.collection(ENTREPRISE_COLLECTION_REF)

    

}