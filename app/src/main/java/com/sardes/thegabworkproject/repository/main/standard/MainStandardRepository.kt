package com.sardes.thegabworkproject.repository.main.standard

import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.data.models.CompteStandard
import com.sardes.thegabworkproject.repository.ressources.Ressources
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


private const val COMPTES_STANDARD_REF = "ComptesStandard"
private const val BOOKMARKS_REF = "Favoris"
private const val APPLICATIONS_REF = "candidatures"
private const val POSTS_COLLECTION_REF = "Posts"

class MainStandardRepository {

    fun user() = Firebase.auth.currentUser
    fun hasUser(): Boolean = Firebase.auth.currentUser != null

    fun getUserId(): String = Firebase.auth.currentUser?.uid.orEmpty()


    private val standardRef: CollectionReference = Firebase
        .firestore.collection(COMPTES_STANDARD_REF)


    private val bookmarkedPostRef: CollectionReference = Firebase
        .firestore.collection(COMPTES_STANDARD_REF + "/" + getUserId() + "/" + BOOKMARKS_REF)


    private val applicationsRef: CollectionReference = Firebase
        .firestore.collection(COMPTES_STANDARD_REF + "/" + getUserId() + "/" + APPLICATIONS_REF)


    private val postsRef: CollectionReference = Firebase
        .firestore.collection(POSTS_COLLECTION_REF)


    /*
        @BOOKMARKS THIS SECTION CONCERNS EVERYTHING ABOUT BOOKMARKS
     */
    fun getAllJobBookmarks(userId: String): Flow<Ressources<List
    <CompteStandard.JobBookmark>>> = callbackFlow {

        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = bookmarkedPostRef
                .orderBy("dateEnregistrement")
                .whereEqualTo("userId", userId)
                .addSnapshotListener { snapshot, e ->
                    val response = if (snapshot != null) {
                        val bookmark =
                            snapshot.toObjects(CompteStandard.JobBookmark::class.java)
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

    fun addJobBookmark(
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
        val documentId = bookmarkedPostRef.document().id

        val bookmark = CompteStandard.JobBookmark(
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

        bookmarkedPostRef
            .document(documentId)
            .set(bookmark)
            .addOnCompleteListener { result ->
                onComplete.invoke(result.isSuccessful)
            }

    }


    /*********************************************************************
    @APPLICATIONS THIS SECTION CONCERNS EVERYTHING ABOUT APPLICATIONS
     *********************************************************************/

    fun getAllApplications(userId: String): Flow<Ressources<List
    <CompteStandard.Candidature>>> = callbackFlow {
        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = applicationsRef
                .orderBy("dateCandidature")
                .whereEqualTo("userId", userId)
                .addSnapshotListener { snapshot, e ->
                    val response = if (snapshot != null) {
                        val bookmark =
                            snapshot.toObjects(CompteStandard.Candidature::class.java)
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
    <CompteStandard.Candidature>>> = callbackFlow {

        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = applicationsRef
                .orderBy("dateCandidature")
                .whereEqualTo("userId", userId)
                .whereEqualTo("status", "pending")
                .addSnapshotListener { snapshot, e ->
                    val response = if (snapshot != null) {
                        val bookmark =
                            snapshot.toObjects(CompteStandard.Candidature::class.java)
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
    <CompteStandard.Candidature>>> = callbackFlow {

        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = applicationsRef
                .orderBy("dateCandidature")
                .whereEqualTo("userId", userId)
                .whereEqualTo("status", "rejected")
                .addSnapshotListener { snapshot, e ->
                    val response = if (snapshot != null) {
                        val bookmark =
                            snapshot.toObjects(CompteStandard.Candidature::class.java)
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
    <CompteStandard.Candidature>>> = callbackFlow {

        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = applicationsRef
                .orderBy("dateCandidature")
                .whereEqualTo("userId", userId)
                .whereEqualTo("status", "scheduled")
                .addSnapshotListener { snapshot, e ->
                    val response = if (snapshot != null) {
                        val bookmark =
                            snapshot.toObjects(CompteStandard.Candidature::class.java)
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




    /*********************************************************************
    @
     *********************************************************************/

    fun getUserInformations(
        seekerId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (CompteStandard?) -> Unit,
    ) {
        standardRef
            .document(seekerId)
            .get()
            .addOnSuccessListener {
                onSuccess.invoke(it?.toObject(CompteStandard::class.java))
            }
            .addOnFailureListener { result ->
                result.cause?.let { onError.invoke(it) }
            }
    }

    fun getPost(
        postId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (CompteEntreprise.Post?) -> Unit,
    ) {
        postsRef
            .document(postId)
            .get()
            .addOnSuccessListener {
                onSuccess.invoke(it?.toObject(CompteEntreprise.Post::class.java))
            }
            .addOnFailureListener { result ->
                result.cause?.let { onError.invoke(it) }

            }
    }


    /*********************************************************************
    @
     *********************************************************************/

    fun getOccupationLikePosts(occupation: String): Flow<Ressources<List
    <CompteEntreprise.Post>>> = callbackFlow {
        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = postsRef
                .orderBy("dateCreationPost")
                .whereEqualTo("competences", occupation)
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


    fun getFiveLatestOccupationLikePosts(occupation: String): Flow<Ressources<List
    <CompteEntreprise.Post>>> = callbackFlow {
        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = postsRef
                .orderBy("dateCreationPost")
                .whereEqualTo("competences", occupation)
                .limit(5)
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


    fun getFiveLatestPosts(): Flow<Ressources<List
    <CompteEntreprise.Post>>> = callbackFlow {
        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = postsRef
                .orderBy("dateCreationPost")
                .limit(5)
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


    fun getPostsJobFromCity(city: String): Flow<Ressources<List
    <CompteEntreprise.Post>>> = callbackFlow {
        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = postsRef
                .orderBy("dateCreationPost")
                .whereEqualTo("ville", city)
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





    /*********************************************************************
    % ADD
     *********************************************************************/

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

        val application = CompteStandard.Candidature(
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






}