package com.sardes.thegabworkproject.repository.main.entreprise

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sardes.thegabworkproject.data.models.Conversation
import com.sardes.thegabworkproject.repository.ressources.Ressources
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

private const val COMPTES_ENTREPRISE_REF = "ComptesEntreprise"
private const val MESSAGES_REF = "Messages"
private const val CONVERSATIONS_REF = "Conversations"

class MessagesEntrepriseRepository {
    fun user() = Firebase.auth.currentUser
    fun hasUser(): Boolean = Firebase.auth.currentUser != null

    fun getUserId(): String = Firebase.auth.currentUser?.uid.orEmpty()

    private val standardRef: CollectionReference = Firebase
        .firestore.collection(COMPTES_ENTREPRISE_REF)

    private val messagesRef: CollectionReference = Firebase
        .firestore.collection(MESSAGES_REF)

    private val conversationRef: CollectionReference = Firebase
        .firestore.collection(COMPTES_ENTREPRISE_REF + "/" + getUserId() + "/" + CONVERSATIONS_REF)


    fun getGetAllConversations(userId: String): Flow<Ressources<List
    <Conversation>>> = callbackFlow {

        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = conversationRef
                .orderBy("lastMessageDate")
                .whereEqualTo("userId", userId)
                .addSnapshotListener { snapshot, e ->
                    val reponse = if (snapshot != null) {
                        val conversation =
                            snapshot.toObjects(Conversation::class.java)
                        Ressources.Success(data = conversation)
                    } else {
                        Ressources.Error(throwable = e?.cause)
                    }
                    trySend(reponse)
                }
        } catch (e: Exception) {
            trySend(Ressources.Error(e.cause))
            e.printStackTrace()
        }

        awaitClose {
            snapshotStateListener?.remove()
        }
    }


    fun getConversation(
        conversationId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (Conversation?) -> Unit
    ){
        conversationRef
            .document(conversationId)
            .get()
            .addOnSuccessListener {
                onSuccess.invoke(it?.toObject(Conversation::class.java))
            }
            .addOnFailureListener{result ->
                result.cause?.let{onError.invoke(it)}
            }
    }


    fun getAllMessages(conversationId: String): Flow<Ressources<List<
            Conversation.Message>>> = callbackFlow {

        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = messagesRef
                .orderBy("sentAt", Query.Direction.DESCENDING)
                .whereEqualTo("conversationId", conversationId)
                .addSnapshotListener { snapshot, e ->
                    val response = if (snapshot != null) {
                        val message =
                            snapshot.toObjects(Conversation.Message::class.java)
                        Ressources.Success(data = message)
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


    fun getMessage(
        messageId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (Conversation.Message?) -> Unit
    ){
        conversationRef
            .document(messageId)
            .get()
            .addOnSuccessListener {
                onSuccess.invoke(it?.toObject(Conversation.Message::class.java))
            }
            .addOnFailureListener{result ->
                result.cause?.let{onError.invoke(it)}
            }
    }

}