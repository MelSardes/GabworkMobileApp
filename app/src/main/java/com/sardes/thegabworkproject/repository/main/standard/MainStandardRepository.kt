package com.sardes.thegabworkproject.repository.main.standard

import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sardes.thegabworkproject.data.*
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.data.models.CompteStandard
import com.sardes.thegabworkproject.data.models.Skill
import com.sardes.thegabworkproject.repository.ressources.Ressources
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


class MainStandardRepository {

    private val db = Firebase.firestore

    fun user() = Firebase.auth.currentUser
    fun hasUser(): Boolean = Firebase.auth.currentUser != null

    fun getUserId(): String = Firebase.auth.currentUser?.uid.orEmpty()


    private val standardRef: CollectionReference = Firebase
        .firestore.collection(COMPTES_STANDARD_REF)


    private val bookmarkedPostRef: CollectionReference = Firebase
        .firestore.collection(COMPTES_STANDARD_REF + "/" + getUserId() + "/" + BOOKMARKS_REF)


    private val applicationsRef: CollectionReference = Firebase
        .firestore.collection(COMPTES_STANDARD_REF + "/" + getUserId() + "/" + APPLICANTS_REF)


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
                .orderBy("saveDate")
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


    /*********************************************************************
    @  APPLICATIONS
     *********************************************************************/

    fun getApplication(
        userId: String,
        postId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (CompteStandard.Application?) -> Unit
    ){
        standardRef
            .document(userId)
            .collection("Applications")
            .document(postId)
            .get()
            .addOnSuccessListener {
                onSuccess.invoke(it?.toObject(CompteStandard.Application::class.java))
            }
            .addOnFailureListener{result ->
                result.cause?.let{ onError.invoke(it)}
            }
    }

    fun getAllApplications(userId: String): Flow<Ressources<List
    <CompteStandard.Application>>> = callbackFlow {
        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = standardRef
                .document(userId)
                .collection("Applications")
                .orderBy("applicationDate")
                .addSnapshotListener { snapshot, e ->
                    val response = if (snapshot != null) {
                        val bookmark =
                            snapshot.toObjects(CompteStandard.Application::class.java)
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
    <CompteStandard.Application>>> = callbackFlow {

        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = standardRef
                .document(userId)
                .collection("Applications")
                .orderBy("applicationDate")
                .whereEqualTo("status", "Traitement en cours...")
                .addSnapshotListener { snapshot, e ->
                    val response = if (snapshot != null) {
                        val bookmark =
                            snapshot.toObjects(CompteStandard.Application::class.java)
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
    <CompteStandard.Application>>> = callbackFlow {

        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = standardRef
                .document(userId)
                .collection("Applications")
                .orderBy("applicationDate")
                .whereEqualTo("status", "Rejeté")
                .addSnapshotListener { snapshot, e ->
                    val response = if (snapshot != null) {
                        val application =
                            snapshot.toObjects(CompteStandard.Application::class.java)
                        Ressources.Success(data = application)
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

    fun getAcceptedApplications(userId: String): Flow<Ressources<List
    <CompteStandard.Application>>> = callbackFlow {

        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = standardRef
                .document(userId)
                .collection("Applications")
                .orderBy("applicationDate")
                .whereEqualTo("status", "Accepté")
                .addSnapshotListener { snapshot, e ->
                    val response = if (snapshot != null) {
                        val application =
                            snapshot.toObjects(CompteStandard.Application::class.java)
                        Ressources.Success(data = application)
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
    <CompteStandard.Application>>> = callbackFlow {

        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = standardRef
                .document(userId)
                .collection("Applications")
                .orderBy("applicationDate")
                .whereEqualTo("status", "Programmé pour un entretien")
                .addSnapshotListener { snapshot, e ->
                    val response = if (snapshot != null) {
                        val application =
                            snapshot.toObjects(CompteStandard.Application::class.java)
                        Ressources.Success(data = application)
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
    @ END APPLICATIONS
     *********************************************************************/


    /*********************************************************************
    @ USER INFORMATIONS
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

    /*********************************************************************
    @ END USER INFORMATIONS
     *********************************************************************/


    /*********************************************************************
    @ POST
     *********************************************************************/
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
                .orderBy("creationDate")
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
                .orderBy("creationDate")
                .whereEqualTo("city", city)
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
    @ END POST
     *********************************************************************/

    /*********************************************************************
    @ BOOKMARK
     *********************************************************************/

    fun addToBookmarks(
        userId: String,
        postId: String,
        entrepriseId: String?,
        postName: String?,
        entrepriseName: String?,
        urlLogo: String?,
        salary: String?,
        city: String?,
        province: String?,
        jobType: String?,
        onComplete: (Boolean) -> Unit
    ) {
        val bookmarkRef = standardRef
            .document(userId)
            .collection(BOOKMARKS_REF)
            .document(postId)

        val bookmark = CompteStandard.JobBookmark(
            postId,
            entrepriseId,
            postName,
            entrepriseName,
            urlLogo,
            salary,
            city,
            province,
            jobType
        )

        val postRef = postsRef.document(postId)

        db.runBatch { batch ->
            batch.set(bookmarkRef, bookmark)
            batch.update(postRef, "savers", FieldValue.arrayUnion(userId))
        }
            .addOnCompleteListener { result ->
                onComplete.invoke(result.isSuccessful)
            }
    }

    fun removeFromBookmarks(
        userId: String,
        postId: String?,
        onComplete: (Boolean) -> Unit
    ) {
        val bookmarkRef = standardRef
            .document(userId)
            .collection(BOOKMARKS_REF)
            .document(postId!!)

        val postRef = postsRef.document(postId)

        db.runBatch { batch ->
            batch.delete(bookmarkRef)
            batch.update(postRef, "savers", FieldValue.arrayRemove(userId))
        }
            .addOnCompleteListener { result ->
                onComplete.invoke(result.isSuccessful)
            }

    }

    /*********************************************************************
    @ END BOOKMARK
     *********************************************************************/

    /*********************************************************************
    @ COMMENTS
     *********************************************************************/


    fun addComment(
        postId: String,
        reviewerId: String,
        reviewerName: String,
        reviewContent: String,
        urlPhoto: String,
        onComplete: (Boolean) -> Unit
    ) {
        val thisPostRef = postsRef.document(postId)

        val review = CompteEntreprise.Post.Review(
            reviewerId,
            urlPhoto,
            reviewerName,
            reviewContent,
            date = Timestamp.now()
        )

        thisPostRef.update("comments", FieldValue.arrayUnion(review))
    }

    /*********************************************************************
    @ END BOOKMARK
     *********************************************************************/


    /*********************************************************************
    @ ADDITIONAL INFORMATIONS
     *********************************************************************/

    fun addAdditionalInformations(
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

        val educationRef: CollectionReference = standardRef
            .document(userId)
            .collection("Education")

        val experienceRef: CollectionReference = standardRef
            .document(userId)
            .collection("Experience")

        val skillsRef: CollectionReference = standardRef
            .document(userId)
            .collection("Competences")


        db.runBatch { batch ->
            batch.update(
                standardRef.document(userId), mapOf(
                    "languages" to languages,
                    "wishJobs" to wishJobs,
//                    "urlCV" to urlCV,
                    "preferredJob" to preferredJob,
                    "HQH" to HQH,
                    "isComplete" to 1
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
        standardRef.document(userId)
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


    fun addInternshipNeededInformations(
        userId: String,
        actualSchool: String,
        cycleActuel: String,
        filliereActuelle: String,
        onComplete: (Boolean) -> Unit
    ) {
        standardRef.document(userId)
            .update(
                mapOf(
                    "actualSchool" to actualSchool,
                    "cycleActuel" to cycleActuel,
                    "filliereActuelle" to filliereActuelle,
                    "isInternshipNeededInformationsComplete" to true
                )
            )
            .addOnCompleteListener{result ->
                onComplete.invoke(result.isSuccessful)
            }
    }

    /*********************************************************************
    @ END ADDITIONAL INFORMATIONS
     *********************************************************************/


    /*********************************************************************
    @ APPLICATIONS
     *********************************************************************/

    fun addApplication(
        userId: String,
        postId: String,
        applicantName: String,
        urlPhoto: String,
        postName: String,
        entrepriseName: String,
        urlLogoEntreprise: String,
        salary: String,
        applicationDate: Timestamp = Timestamp.now(),
        status: String = "Traitement en cours...",
        message: String = "En attente d'une réponse ...",
        coverLetter: String,
        city: String,
        jobType: String,
        onComplete: (Boolean) -> Unit
    ){

//        val applicantId = postsRef.document(postId).collection(APPLICANTS_REF).document().id

        val application = CompteStandard.Application(
            postId,
            postName,
            applicantName,
            postName,
            entrepriseName,
            urlLogoEntreprise,
            salary,
            city,
            jobType,
            applicationDate,
            urlPhoto,
            status,
            message,
            coverLetter
        )

        val applicantInPostRef = postsRef
            .document(postId)
            .collection(APPLICANTS_REF)
            .document(userId)

        val applicantInApplicantRef = standardRef
            .document(userId)
            .collection(APPLICATIONS_REF)
            .document(postId)


        db.runBatch { batch ->
            batch.set(applicantInApplicantRef, application)
            batch.set(applicantInPostRef, application)
        }.addOnCompleteListener{result ->
            onComplete.invoke(result.isSuccessful)
        }
    }


    /*********************************************************************
    @ END APPLICATIONS
     *********************************************************************/


}



