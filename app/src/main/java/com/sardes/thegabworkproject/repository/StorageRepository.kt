package com.sardes.thegabworkproject.repository

import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sardes.thegabworkproject.models.Competences_Profil_Etudiant
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

const val COMPETENCES_COLLECTION_REF = "Competences"

class StorageRepository(){

    fun user() = Firebase.auth.currentUser
    fun hasUser(): Boolean = Firebase.auth.currentUser != null

    fun getUserId(): String = Firebase.auth.currentUser?.uid.orEmpty()

    private val competencesRef: CollectionReference = Firebase
        .firestore.collection(COMPETENCES_COLLECTION_REF)

    fun getUserSkills(userId:String):Flow<Ressources<List
            <Competences_Profil_Etudiant>>> = callbackFlow{

        var snapshotStateListener : ListenerRegistration? = null

        try {
            snapshotStateListener = competencesRef
                .orderBy("competence")
                .whereEqualTo("id_compte_standard", userId)
                .addSnapshotListener{ snapshot, e ->
                    val response = if (snapshot != null){
                        val competences = snapshot.toObjects(Competences_Profil_Etudiant::class.java)
                        Ressources.Success(data = competences)
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


    fun getSkill(
        skillId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (Competences_Profil_Etudiant?) -> Unit
    ){
        competencesRef
            .document(skillId)
            .get()
            .addOnSuccessListener {
                onSuccess.invoke(it?.toObject(Competences_Profil_Etudiant::class.java))
            }
            .addOnFailureListener{result ->
                result.cause?.let { onError.invoke(it) }

            }
    }

    fun addSkill(
        userId: String,
        competence: String,
        timestamp: Timestamp,
        niveau_de_comptence: String,
        onComplete: (Boolean) -> Unit
    ){
        val documentId = competencesRef.document().id
        val skill = Competences_Profil_Etudiant(
            userId,
            documentId,
            competence,
            niveau_de_comptence,
            timestamp
        )

        competencesRef
            .document(documentId)
            .set(skill)
            .addOnCompleteListener {result ->
                onComplete.invoke(result.isSuccessful)
            }
    }

    fun deleteSkill(skillId: String, onComplete: (Boolean) -> Unit){
        competencesRef.document(skillId)
            .delete()
            .addOnCompleteListener {
                onComplete.invoke(it.isSuccessful)
            }
    }


    fun updateSkill(
        competence: String,
        niveau_de_comptence: String,
        skillId: String,
        onResult: (Boolean) -> Unit
    ){
        val updateData = hashMapOf<String, Any>(
            "competence" to competence,
            "niveau_de_competence" to niveau_de_comptence,
        )

        competencesRef.document(skillId)
            .update(updateData)
            .addOnCompleteListener {
                onResult(it.isSuccessful)
            }
    }

    fun signOut() = Firebase.auth.signOut()


}


sealed class Ressources<T>(
    val data: T? = null,
    val throwable: Throwable? = null,
){
    class Loading<T>: Ressources<T>()
    class Success<T>(data: T?):Ressources<T>(data = data)
    class Error<T>(throwable: Throwable?):Ressources<T>(throwable = throwable)


}