package com.sardes.thegabworkproject.repository.main.entreprise

import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.repository.ressources.Ressources
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


const val COMPTES_ENTREPRISE_REF = "ComptesEntreprise"
const val POSTS_COLLECTION_REF = "Posts"
const val CANDIDATS_COLLECTION_REF = "Candidats"

class MainEntrepriseRepository {

    fun user() = Firebase.auth.currentUser
    fun hasUser(): Boolean = Firebase.auth.currentUser != null

    fun getUserId(): String = Firebase.auth.currentUser?.uid.orEmpty()


    private val comptesEntrepriseRef: DocumentReference = Firebase
        .firestore.collection(COMPTES_ENTREPRISE_REF).document(getUserId())


    private val postsRef: CollectionReference = Firebase
        .firestore.collection(POSTS_COLLECTION_REF)


    private val candidatsRef: CollectionReference = Firebase
        .firestore.collection(COMPTES_ENTREPRISE_REF + "/" + getUserId() + "/" + CANDIDATS_COLLECTION_REF)



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
        prerequis: String,
        emploiOuStage: String,
        actif: Boolean = true,
        onComplete: (Boolean) -> Unit,
    ) {
        val documentId = postsRef.document().id

        val post = CompteEntreprise.Post(
            documentId,
            postName,
            entrepriseId,
            entrepriseName,
            dateCreationPost,
            descriptionEmploi,
            salaire,
            ville,
            province,
            domaine,
            experience,
            typeDEmploi,
            adresse,
            dateLimite,
            prerequis,
            emploiOuStage,
            actif,
        )

        postsRef
            .document(documentId)
            .set(post)
            .addOnCompleteListener { result ->
                onComplete.invoke(result.isSuccessful)
            }
    }



    fun getPostApplicants(entrepriseId: String, postId: String): Flow<Ressources<List
    <CompteEntreprise.Post.Candidat>>> = callbackFlow {

        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = postsRef
                .orderBy("dateCandidature")
                .whereEqualTo("entrepriseId", entrepriseId)
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
}