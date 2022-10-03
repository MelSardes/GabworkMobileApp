package com.sardes.thegabworkproject.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sardes.thegabworkproject.data.POSTS_COLLECTION_REF
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.repository.ressources.Ressources
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class SearchRepository {

    /*
    FIELD TO FILTER:
        POSTNAME
        SALARY
        CITY
        PROVINCE
        DOMAINE
        EXPERIENCE
        JOB TYPE
        SKILLS
     */

    private val db = Firebase.firestore

    private val postsRef: CollectionReference = db.collection(POSTS_COLLECTION_REF)



    val queryPostAtLibreville = postsRef.whereEqualTo("city".lowercase(), "libreville")

    val queryJobTypeFullTime =      postsRef.whereEqualTo("jobType".lowercase(), "plein temps")
    val queryJobTypePartTime =      postsRef.whereEqualTo("jobType".lowercase(), "temps partiel")
    val queryJobTypeInternship =    postsRef.whereEqualTo("jobType".lowercase(), "stage")
    val queryJobTypeFreelance =     postsRef.whereEqualTo("jobType".lowercase(), "freelance")


    fun getAllPosts(): Flow<Ressources<List
    <CompteEntreprise.Post>>> = callbackFlow {
        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = postsRef
                .orderBy("creationDate")
                .addSnapshotListener { snapshot, e ->
                    val response = if (snapshot != null) {
                        val post =
                            snapshot.toObjects(CompteEntreprise.Post::class.java)
                        Ressources.Success(data = post)
                    } else {
                        Ressources.Error(throwable = e?.cause)
                    }
                    trySend(response)
                }
        } catch (e: Exception) {
            trySend(Ressources.Error(e.cause))
            e.printStackTrace()
        }

        awaitClose {
            snapshotStateListener?.remove()
        }
    }



}