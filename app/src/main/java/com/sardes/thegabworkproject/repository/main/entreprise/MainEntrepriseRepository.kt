package com.sardes.thegabworkproject.repository.main.entreprise

import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.repository.ressources.Ressources
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


private const val COMPTES_ENTREPRISE_REF = "ComptesEntreprise"
const val POSTS_COLLECTION_REF = "Posts"
const val CANDIDATS_COLLECTION_REF = "Candidats"

class MainEntrepriseRepository {

    fun user() = Firebase.auth.currentUser
    fun hasUser(): Boolean = Firebase.auth.currentUser != null

    fun getUserId(): String = Firebase.auth.currentUser?.uid.orEmpty()


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
                .orderBy("dateCreationPost")
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
                .orderBy("dateCreationPost")
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
                .orderBy("dateCreationPost")
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
            dateCreationPost = dateCreationPost,
            descriptionEmploi = descriptionEmploi,
            salaire = salaire,
            ville = ville,
            province = province,
            domaine = domaine,
            experience = experience,
            typeDEmploi = typeDEmploi,
            adresse = adresse,
            dateLimite = dateLimite,
            competences = competences,
            responsabilites = responsabilites,
        )

        postsRef
            .document(documentId)
            .set(post)
            .addOnCompleteListener { result ->
                onComplete.invoke(result.isSuccessful)
            }
    }


    fun getPostApplicants(postId: String): Flow<Ressources<List
    <CompteEntreprise.Post.Candidat>>> = callbackFlow {

        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = candidatsRef
                .orderBy("dateCandidature", Query.Direction.ASCENDING)
                .whereEqualTo("postId", postId)
                .addSnapshotListener { snapshot, e ->
                    val response = if (snapshot != null) {
                        val candidat =
                            snapshot.toObjects(CompteEntreprise.Post.Candidat::class.java)
                        Ressources.Success(data = candidat)
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
        onSuccess: (CompteEntreprise.Post.Candidat?) -> Unit,
    ) {
        candidatsRef
            .document(applicantId)
            .get()
            .addOnSuccessListener {
                onSuccess.invoke(it?.toObject(CompteEntreprise.Post.Candidat::class.java))
            }
            .addOnFailureListener { reslut ->
                reslut.cause?.let { onError.invoke(it) }
            }
    }
}