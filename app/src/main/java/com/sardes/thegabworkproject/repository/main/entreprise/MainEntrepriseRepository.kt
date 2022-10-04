package com.sardes.thegabworkproject.repository.main.entreprise

import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sardes.thegabworkproject.data.*
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.data.models.CompteStandard
import com.sardes.thegabworkproject.repository.ressources.Ressources
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


const val CANDIDATS_COLLECTION_REF = "Applicants"

class MainEntrepriseRepository {

    private val db = Firebase.firestore

    fun user() = Firebase.auth.currentUser
    fun hasUser(): Boolean = Firebase.auth.currentUser != null

    fun getUserId(): String = Firebase.auth.currentUser?.uid.orEmpty()

    private val standardRef: CollectionReference = Firebase
        .firestore.collection(COMPTES_STANDARD_REF)

    //    REFER A USER WITH ENTREPRISE ACCOUNT
    private val comptesEntrepriseRef: DocumentReference = Firebase
        .firestore.collection(COMPTES_ENTREPRISE_REF).document(getUserId())

    //    REFER POSTS FROM ALL ENTREPRISES
    private val postsRef: CollectionReference = Firebase
        .firestore.collection(POSTS_COLLECTION_REF)

    //    REFER APPLICANTS TO POST FROM AN ENTREPRISE
    private val candidatsRef: CollectionReference = Firebase
        .firestore.collection(POSTS_COLLECTION_REF + "/" + getUserId() + "/" + CANDIDATS_COLLECTION_REF)


    fun getEntrepriseInformations(
        onError: (Throwable) -> Unit,
        onSuccess: (CompteEntreprise?) -> Unit,
    ) {
        comptesEntrepriseRef
            .get()
            .addOnSuccessListener {
                onSuccess.invoke(it?.toObject(CompteEntreprise::class.java))
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


    fun getActivePosts(entrepriseId: String): Flow<Ressources<List
    <CompteEntreprise.Post>>> = callbackFlow {

        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = postsRef
                .orderBy("creationDate")
                .whereEqualTo("entrepriseId", entrepriseId)
                .whereEqualTo("actif", true)
                .addSnapshotListener { snapshot, e ->
                    val response = if (snapshot != null) {
                        val activePost =
                            snapshot.toObjects(CompteEntreprise.Post::class.java)
                        Ressources.Success(data = activePost)
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


    fun getInactivePosts(entrepriseId: String): Flow<Ressources<List
    <CompteEntreprise.Post>>> = callbackFlow {

        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = postsRef
                .orderBy("creationDate")
                .whereEqualTo("entrepriseId", entrepriseId)
                .whereEqualTo("actif", false)
                .addSnapshotListener { snapshot, e ->
                    val response = if (snapshot != null) {
                        val inactivePost =
                            snapshot.toObjects(CompteEntreprise.Post::class.java)
                        Ressources.Success(data = inactivePost)
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


    fun getAllPosts(entrepriseId: String): Flow<Ressources<List
    <CompteEntreprise.Post>>> = callbackFlow {

        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = postsRef
                .orderBy("creationDate")
                .whereEqualTo("entrepriseId", entrepriseId)
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


    fun addPost(
        postName: String,
        entrepriseId: String,
        entrepriseName: String,
        urlLogo: String?,
        dateCreationPost: Timestamp,
        descriptionEmploi: String,
        salaire: String,
        ville: String,
        province: String,
        domaine: String,
        experience: String,
        typeDEmploi: String,
        adresse: String,
        dateLimite: Timestamp?,
        competences: List<String>,
        responsabilites: List<String>,
        onComplete: (Boolean) -> Unit,
    ) {
        val documentId = postsRef.document().id

        val post = CompteEntreprise.Post(
            postId = documentId,
            postName = postName,
            entrepriseId = entrepriseId,
            entrepriseName = entrepriseName,
            urlLogo = urlLogo,
            creationDate = dateCreationPost,
            description = descriptionEmploi,
            salary = salaire,
            city = ville,
            province = province,
            domain = domaine,
            experience = experience,
            jobType = typeDEmploi,
            address = adresse,
            limit = dateLimite,
            skills = competences,
            responsibilities = responsabilites,
        )

        postsRef
            .document(documentId)
            .set(post)
            .addOnCompleteListener { result ->
                onComplete.invoke(result.isSuccessful)
            }
    }


    fun getPostApplicants(postId: String): Flow<Ressources<List
    <CompteStandard.Application>>> = callbackFlow {

        var snapshotStateListener: ListenerRegistration? = null

        val applicantInPostRef = postsRef
            .document(postId)
            .collection(APPLICANTS_REF)

        try {
            snapshotStateListener = applicantInPostRef
                .orderBy("applyDate", Query.Direction.ASCENDING)
                .addSnapshotListener { snapshot, e ->
                    val response = if (snapshot != null) {
                        val applicants =
                            snapshot.toObjects(CompteStandard.Application::class.java)
                        Ressources.Success(data = applicants)
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

    fun getLastApplicants(): Flow<Ressources<List
    <CompteStandard.Application>>> = callbackFlow {

        var snapshotStateListener: ListenerRegistration? = null

        val applicantsRef = postsRef
            .document()
            .collection(APPLICANTS_REF)

        try {
            snapshotStateListener = applicantsRef
                .orderBy("applyDate", Query.Direction.ASCENDING)
                .addSnapshotListener { snapshot, e ->
                    val response = if (snapshot != null) {
                        val applicants =
                            snapshot.toObjects(CompteStandard.Application::class.java)
                        Ressources.Success(data = applicants)
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


    fun getApplicantProfile(
        applicantId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (CompteEntreprise.Post.Applicant?) -> Unit,
    ) {
        candidatsRef
            .document(applicantId)
            .get()
            .addOnSuccessListener {
                onSuccess.invoke(it?.toObject(CompteEntreprise.Post.Applicant::class.java))
            }
            .addOnFailureListener { reslut ->
                reslut.cause?.let { onError.invoke(it) }
            }
    }

    fun updateApplication(
        postId: String,
        userId: String,
        status: String,
        message: String,
        onComplete: (Boolean) -> Unit
    ){

        val statusField = "status"
        val messageField = "message"

        val applicantInPostRef = postsRef
            .document(postId)
            .collection(APPLICANTS_REF)
            .document(userId)

        val applicantInApplicantRef = standardRef
            .document(userId)
            .collection(APPLICATIONS_REF)
            .document(postId)


        db.runBatch { batch ->
            batch.update(applicantInApplicantRef, messageField, message)
            batch.update(applicantInApplicantRef, statusField, status)

            batch.update(applicantInPostRef, messageField, message)
            batch.update(applicantInPostRef, statusField, status)
        }.addOnCompleteListener{result ->
            onComplete.invoke(result.isSuccessful)
        }
    }


    fun getApplication(
        userId: String,
        postId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (CompteStandard.Application?) -> Unit
    ){
        postsRef
            .document(postId)
            .collection(APPLICANTS_REF)
            .document(userId)
            .get()
            .addOnSuccessListener {
                onSuccess.invoke(it?.toObject(CompteStandard.Application::class.java))
            }
            .addOnFailureListener{result ->
                result.cause?.let{ onError.invoke(it)}
            }
    }



}