package com.sardes.thegabworkproject.repository.main.entreprise

import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.repository.ressources.PostsRessources
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


const val POSTS_COLLECTION_REF = "PostsVacant"

class PostsEntrepriseRepository {

    fun user() = Firebase.auth.currentUser
    fun hasUser(): Boolean = Firebase.auth.currentUser != null

    fun getUserId(): String = Firebase.auth.currentUser?.uid.orEmpty()

    private val postsRef: CollectionReference = Firebase
        .firestore.collection(POSTS_COLLECTION_REF)


    fun getEntreprisePosts(entrepriseId:String): Flow<PostsRessources<List
    <CompteEntreprise.PostVacant>>> = callbackFlow{

        var snapshotStateListener : ListenerRegistration? = null

        try {
            snapshotStateListener = postsRef
                .orderBy("dateCreationPost")
                .whereEqualTo("entrepriseId", entrepriseId)
                .addSnapshotListener{ snapshot, e ->
                    val response = if (snapshot != null){
                        val postVacant = snapshot.toObjects(CompteEntreprise.PostVacant::class.java)
                        PostsRessources.Success(data = postVacant)
                    }else{
                        PostsRessources.Error(throwable = e?.cause)
                    }
                    trySend(response)

                }
        }catch (e:Exception){
            trySend(PostsRessources.Error(e.cause))
            e.printStackTrace()
        }

        awaitClose{
            snapshotStateListener?.remove()
        }
    }


    fun getPost(
        postId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (CompteEntreprise.PostVacant?) -> Unit
    ){
        postsRef
            .document(postId)
            .get()
            .addOnSuccessListener {
                onSuccess.invoke(it?.toObject(CompteEntreprise.PostVacant::class.java))
            }
            .addOnFailureListener{result ->
                result.cause?.let { onError.invoke(it) }

            }
    }

    fun addPost(
        postName: String,
        entrepriseId: String,
        entrepriseName: String,
        dateCreationPost: Timestamp,
        descriptionEmploi: String,
        salaire: Int,
        typeDEmploi: String,
        adresse: String,
        dateLimite: Timestamp,
        prerequis: String,
        emploiOuStage: String,
        actif: Boolean,
        onComplete: (Boolean) -> Unit
    ){
        val documentId = postsRef.document().id
        val post = CompteEntreprise.PostVacant(
            documentId,
            postName,
            entrepriseId,
            entrepriseName,
            dateCreationPost,
            descriptionEmploi,
            salaire,
            typeDEmploi,
            adresse,
            dateLimite,
            prerequis,
            emploiOuStage,
            actif
        )


        postsRef
            .document(documentId)
            .set(post)
            .addOnCompleteListener {result ->
                onComplete.invoke(result.isSuccessful)
            }
    }

    fun deletePost(postId: String, onComplete: (Boolean) -> Unit){
        postsRef.document(postId)
            .delete()
            .addOnCompleteListener {
                onComplete.invoke(it.isSuccessful)
            }
    }


    fun updatePost(
        postId: String,
        postName: String,
        entrepriseId: String,
        entrepriseName: String,
        dateCreationPost: Timestamp,
        descriptionEmploi: String,
        salaire: Int,
        typeDEmploi: String,
        adresse: String,
        dateLimite: Timestamp,
        prerequis: String,
        emploiOuStage: String,
        actif: Boolean,
        onResult: (Boolean) -> Unit
    ){
        val updateData = hashMapOf<String, Any>(
            "postId" to postId,
            "intitulePost" to postName,
            "entrepriseId" to entrepriseId,
            "entrepriseName" to entrepriseName,
            "dateCreationPost" to dateCreationPost,
            "descriptionEmploi" to descriptionEmploi,
            "salaire" to salaire,
            "typeDEmploi" to typeDEmploi,
            "adresse" to adresse,
            "dateLimite" to dateLimite,
            "prerequis" to prerequis,
            "emploiOuStage" to emploiOuStage,
            "actif" to actif,
        )

        postsRef.document(entrepriseId)
            .update(updateData)
            .addOnCompleteListener {
                onResult(it.isSuccessful)
            }
    }


}