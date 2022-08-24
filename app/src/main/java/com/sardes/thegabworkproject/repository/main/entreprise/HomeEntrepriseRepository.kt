package com.sardes.thegabworkproject.repository.main.entreprise

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sardes.thegabworkproject.models.CompteEntreprise
import com.sardes.thegabworkproject.repository.Ressources
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


const val COMPTES_ENTREPRISE_REF = "ComptesEntreprise"

class HomeEntrepriseRepository {

    fun user() = Firebase.auth.currentUser
    fun hasUser(): Boolean = Firebase.auth.currentUser != null

    fun getUserId(): String = Firebase.auth.currentUser?.uid.orEmpty()

    private val informationsRef: DocumentReference = Firebase
        .firestore.collection(COMPTES_ENTREPRISE_REF).document(getUserId())


    fun getInformations(
        onError: (Throwable) -> Unit,
        onSuccess: (CompteEntreprise?) -> Unit
    ){
        informationsRef
            .get()
            .addOnSuccessListener {
                onSuccess.invoke(it?.toObject(CompteEntreprise::class.java))
            }
            .addOnFailureListener{result ->
                result.cause?.let { onError.invoke(it) }

            }
    }

    private val postsRef: CollectionReference = Firebase
        .firestore.collection(POSTS_COLLECTION_REF)

    fun getActivePosts(entrepriseId:String): Flow<Ressources<List
    <CompteEntreprise.PostVacant>>> = callbackFlow{

        var snapshotStateListener : ListenerRegistration? = null

        try {
            snapshotStateListener = postsRef
                .orderBy("dateCreationPost")
                .whereEqualTo("entrepriseId", entrepriseId)
                .whereEqualTo("actif", true)
                .addSnapshotListener{ snapshot, e ->
                    val response = if (snapshot != null){
                        val postsVacant = snapshot.toObjects(CompteEntreprise.PostVacant::class.java)
                        Ressources.Success(data = postsVacant)
                    }else{
                        Ressources.Error(throwable = e?.cause)
                    }
                    trySend(response)

                }
        }catch (e:Exception){
            trySend(Ressources.Error(e.cause))
            e.printStackTrace()
        }

        awaitClose{
            snapshotStateListener?.remove()
        }
    }



}