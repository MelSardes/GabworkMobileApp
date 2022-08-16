/*
package com.sardes.thegabworkproject.ui.screens.home

import com.google.firebase.firestore.ListenerRegistration
import com.sardes.thegabworkproject.models.Competences_Profil_Etudiant
import com.sardes.thegabworkproject.repository.Ressources
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class HomeViewModel {

    fun getUserSkills(userId:String): Flow<Ressources<List
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

}*/
