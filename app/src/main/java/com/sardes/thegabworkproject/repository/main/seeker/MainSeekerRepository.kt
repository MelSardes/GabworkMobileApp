package com.sardes.thegabworkproject.repository.main.seeker

import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sardes.thegabworkproject.data.models.CompteDemandeur
import com.sardes.thegabworkproject.repository.ressources.Ressources
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


const val COMPTES_DEMANDEUR_REF = "ComptesDemandeur"
const val BOOKMARKS_REF = "Favoris"
const val APPLICATIONS_REF = "candidatures"

class MainSeekerRepository {

    fun user() = Firebase.auth.currentUser
    fun hasUser(): Boolean = Firebase.auth.currentUser != null

    fun getUserId(): String = Firebase.auth.currentUser?.uid.orEmpty()


    private val seekerRef: CollectionReference = Firebase
        .firestore.collection(COMPTES_DEMANDEUR_REF)


    private val postBookmarkedRef: CollectionReference = Firebase
        .firestore.collection(COMPTES_DEMANDEUR_REF + "/" + getUserId() + "/" + BOOKMARKS_REF)


    private val applicationsRef: CollectionReference = Firebase
        .firestore.collection(COMPTES_DEMANDEUR_REF + "/" + getUserId() + "/" + APPLICATIONS_REF)


    /*
        @BOOKMARKS THIS SECTION CONCERNS EVERYTHING ABOUT BOOKMARKS
     */
    fun getAllBookmarks(userId: String): Flow<Ressources<List
    <CompteDemandeur.Bookmark>>> = callbackFlow {

        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = postBookmarkedRef
                .orderBy("dateEnregistrement")
                .whereEqualTo("userId", userId)
                .addSnapshotListener { snapshot, e ->
                    val response = if (snapshot != null) {
                        val bookmark =
                            snapshot.toObjects(CompteDemandeur.Bookmark::class.java)
                        Ressources.Success(data = bookmark)
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

    fun addBookmark(
        idPost: String,
        idEntreprise: String,
        nomEntreprise: String,
        urlLogoEntreprise: String,
        salaire: String,
        dateEnregistrement: Timestamp,
        ville: String,
        province: String,
        typeDEmploi: String,
        onComplete: (Boolean) -> Unit,
    ) {
        val documentId = postBookmarkedRef.document().id

        val bookmark = CompteDemandeur.Bookmark(
            documentId,
            idPost,
            idEntreprise,
            nomEntreprise,
            urlLogoEntreprise,
            salaire,
            dateEnregistrement,
            ville,
            province,
            typeDEmploi,
        )

        postBookmarkedRef
            .document(documentId)
            .set(bookmark)
            .addOnCompleteListener { result ->
                onComplete.invoke(result.isSuccessful)
            }

    }


    /*
        @APPLICATIONS THIS SECTION CONCERNS EVERYTHING ABOUT APPLICATIONS
     */

    fun getAllApplications(userId: String): Flow<Ressources<List
    <CompteDemandeur.Candidature>>> = callbackFlow {
        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = applicationsRef
                .orderBy("dateCandidature")
                .whereEqualTo("userId", userId)
                .addSnapshotListener { snapshot, e ->
                    val response = if (snapshot != null) {
                        val bookmark =
                            snapshot.toObjects(CompteDemandeur.Candidature::class.java)
                        Ressources.Success(data = bookmark)
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

    fun getPendingApplications(userId: String): Flow<Ressources<List
    <CompteDemandeur.Candidature>>> = callbackFlow {

        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = applicationsRef
                .orderBy("dateCandidature")
                .whereEqualTo("userId", userId)
                .whereEqualTo("status", "pending")
                .addSnapshotListener { snapshot, e ->
                    val response = if (snapshot != null) {
                        val bookmark =
                            snapshot.toObjects(CompteDemandeur.Candidature::class.java)
                        Ressources.Success(data = bookmark)
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

    fun getRejectedApplications(userId: String): Flow<Ressources<List
    <CompteDemandeur.Candidature>>> = callbackFlow {

        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = applicationsRef
                .orderBy("dateCandidature")
                .whereEqualTo("userId", userId)
                .whereEqualTo("status", "rejected")
                .addSnapshotListener { snapshot, e ->
                    val response = if (snapshot != null) {
                        val bookmark =
                            snapshot.toObjects(CompteDemandeur.Candidature::class.java)
                        Ressources.Success(data = bookmark)
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

    fun getScheduledApplications(userId: String): Flow<Ressources<List
    <CompteDemandeur.Candidature>>> = callbackFlow {

        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = applicationsRef
                .orderBy("dateCandidature")
                .whereEqualTo("userId", userId)
                .whereEqualTo("status", "scheduled")
                .addSnapshotListener { snapshot, e ->
                    val response = if (snapshot != null) {
                        val bookmark =
                            snapshot.toObjects(CompteDemandeur.Candidature::class.java)
                        Ressources.Success(data = bookmark)
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


    fun addApplication(
        candidatureId: String,
        postId: String,
        postName: String,
        entrepriseId: String,
        entrepriseName: String,
        descriptionEmploi: String,
        salaire: String,
        ville: String,
        province: String,
        domaine: String,
        experience: String,
        typeDEmploi: String,
        adresse: String,
        dateCandidature: Timestamp,
        dateLimite: Timestamp,
        prerequis: String,
        status: String,
    ) {
        val documentId = applicationsRef.document().id

        val application = CompteDemandeur.Candidature(
            documentId,
            postId,
            postName,
            entrepriseId,
            entrepriseName,
            descriptionEmploi,
            salaire,
            ville,
            province,
            domaine,
            experience,
            typeDEmploi,
            adresse,
            dateCandidature,
            dateLimite,
            prerequis,
            status,
        )

        applicationsRef
            .document(documentId)
            .set(application)
        TODO("END WRITE addApplication FUNCTION")
//            .addOnCompleteListener {result -> onComplete.invoke(result.isSuccessful)}
    }


    fun getSeekerInformations(
        seekerId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (CompteDemandeur?) -> Unit,
    ) {
        seekerRef
            .document(seekerId)
            .get()
            .addOnSuccessListener {
                onSuccess.invoke(it?.toObject(CompteDemandeur::class.java))
            }
            .addOnFailureListener { result ->
                result.cause?.let { onError.invoke(it) }
            }
    }


}