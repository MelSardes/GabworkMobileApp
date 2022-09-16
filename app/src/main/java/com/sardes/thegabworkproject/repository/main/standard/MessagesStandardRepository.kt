package com.sardes.thegabworkproject.repository.main.standard

import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.data.models.CompteStandard
import com.sardes.thegabworkproject.data.models.Conversation
import com.sardes.thegabworkproject.data.models.UserType
import com.sardes.thegabworkproject.repository.ressources.Ressources
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

private const val COMPTES_STANDARD_REF = "ComptesStandard"
private const val COMPTES_ENTREPRISE_REF = "ComptesEntreprise"
private const val MESSAGES_REF = "Messages"
private const val CONVERSATIONS_REF = "Conversations"
private const val USERS_COLLECTION_REF = "Users"


class MessagesStandardRepository {
    fun user() = Firebase.auth.currentUser
    fun hasUser(): Boolean = Firebase.auth.currentUser != null

    fun getUserId(): String = Firebase.auth.currentUser?.uid.orEmpty()

    private val standardRef: CollectionReference = Firebase
        .firestore.collection(COMPTES_STANDARD_REF)

    private val entrepriseRef: CollectionReference = Firebase
        .firestore.collection(COMPTES_ENTREPRISE_REF)

    private val messagesRef: CollectionReference = Firebase
        .firestore.collection(MESSAGES_REF)

    private val conversationRef: CollectionReference = Firebase
        .firestore.collection(COMPTES_STANDARD_REF + "/" + getUserId() + "/" + CONVERSATIONS_REF)


    private val usersRef: CollectionReference = Firebase
        .firestore.collection(USERS_COLLECTION_REF)

    fun getUserAccountType(
        userId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserType?) -> Unit,
    ) {
        usersRef
            .document(userId)
            .get()
            .addOnSuccessListener {
                onSuccess.invoke(it?.toObject(UserType::class.java))
            }
            .addOnFailureListener { result ->
                result.cause?.let { onError.invoke(it) }
            }
    }


    fun getGetAllConversations(userId: String): Flow<Ressources<List
    <Conversation>>> = callbackFlow {

        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = standardRef
                .document(userId)
                .collection(CONVERSATIONS_REF)
                .orderBy("lastMessageDate")
                .addSnapshotListener { snapshot, e ->
                    val response = if (snapshot != null) {
                        val conversation =
                            snapshot.toObjects(Conversation::class.java)
                        Ressources.Success(data = conversation)
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


    fun getConversation(
        conversationId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (Conversation?) -> Unit
    ) {
        conversationRef
            .document(conversationId)
            .get()
            .addOnSuccessListener {
                onSuccess.invoke(it?.toObject(Conversation::class.java))
            }
            .addOnFailureListener { result ->
                result.cause?.let { onError.invoke(it) }
            }
    }


    fun getMessage(
        messageId: String,
        conversationId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (Conversation.Message?) -> Unit
    ) {
        conversationRef
            .document(conversationId)
            .collection(MESSAGES_REF)
            .document(messageId)
            .get()
            .addOnSuccessListener {
                onSuccess.invoke(it?.toObject(Conversation.Message::class.java))
            }
            .addOnFailureListener { result ->
                result.cause?.let { onError.invoke(it) }
            }
    }


    fun getAllMessages(conversationId: String): Flow<Ressources<List<
            Conversation.Message>>> = callbackFlow {

        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = conversationRef
                .document(conversationId)
                .collection(MESSAGES_REF)
                .orderBy("sentAt", Query.Direction.ASCENDING)
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


    fun createConversation(
        UID: String,
        receiverID: String,
        receiverName: String,
        receiverAccountType: String,
        receiverUrlPhoto: String?,
        senderUrlPhoto: String?,
        senderName: String?,
        content: String,
        sentAt: Timestamp = Timestamp.now(),
        onComplete: (Boolean) -> Unit,
    ) {


        val senderConversation = Conversation(
            conversationId = receiverID,
            lastMessageContent = content,
            lastMessageDate = sentAt,
            latMessageSender = UID,
            receiverName = receiverName,
            receiverUrlPhoto = receiverUrlPhoto,
            receiverAccountType = receiverAccountType
        )

        val receiverConversation = Conversation(
            conversationId = UID,
            lastMessageContent = content,
            lastMessageDate = sentAt,
            latMessageSender = UID,
            receiverName = senderName,
            receiverUrlPhoto = senderUrlPhoto,
            receiverAccountType = "Standard"
        )



        val senderMessageId =
            standardRef
                .document(UID)
                .collection(CONVERSATIONS_REF)
                .document(receiverID)
                .collection(MESSAGES_REF)
                .document()
                .id

        val receiverMessageRef =

            when (receiverAccountType) {
                "Entreprise" ->
                    entrepriseRef
                        .document(receiverID)
                        .collection(CONVERSATIONS_REF)
                        .document(UID)
                        .collection(MESSAGES_REF)
                        .document(senderMessageId)
                "Standard" ->
                    standardRef
                        .document(receiverID)
                        .collection(CONVERSATIONS_REF)
                        .document(UID)
                        .collection(MESSAGES_REF)
                        .document(senderMessageId)
                else -> return onComplete.invoke(false)
            }


        val senderMessageRef =
            standardRef
                .document(UID)
                .collection(CONVERSATIONS_REF)
                .document(receiverID)
                .collection(MESSAGES_REF)
                .document(senderMessageId)


        val senderConversationRef =
            standardRef
                .document(UID)
                .collection(CONVERSATIONS_REF)
                .document(receiverID)


        val receiverConversationRef =
            when (receiverAccountType) {
                "Standard" ->
                    standardRef
                        .document(receiverID)
                        .collection(CONVERSATIONS_REF)
                        .document(UID)
                "Entreprise" ->
                    entrepriseRef
                        .document(receiverID)
                        .collection(CONVERSATIONS_REF)
                        .document(UID)
                else -> return onComplete.invoke(false)
            }


        val message = Conversation.Message(
            messageId = senderMessageId,
            content = content,
            senderUID = UID,
            sentAt = sentAt
        )


        Firebase.firestore.runBatch { batch ->
            batch.set(senderConversationRef, senderConversation)
            batch.set(receiverConversationRef, receiverConversation)

            batch.set(receiverMessageRef, message)
            batch.set(senderMessageRef, message)
        }
            .addOnCompleteListener { result ->
                onComplete.invoke(result.isSuccessful)
            }
    }

    fun updateConversation(
        UID: String,
        receiverID: String,
        content: String,
        sentAt: Timestamp = Timestamp.now(),
        receiverAccountType: String?,
    ) {
        val senderConversationRef =
            standardRef
                .document(UID)
                .collection(CONVERSATIONS_REF)
                .document(receiverID)

        val receiverConversationRef = when (receiverAccountType) {
            "Standard" ->
                standardRef
                    .document(receiverID)
                    .collection(CONVERSATIONS_REF)
                    .document(UID)
            "Entreprise" ->
                entrepriseRef
                    .document(receiverID)
                    .collection(CONVERSATIONS_REF)
                    .document(UID)
            else -> null
        }


        val senderMessageId =
            standardRef
                .document(UID)
                .collection(CONVERSATIONS_REF)
                .document(receiverID)
                .collection(MESSAGES_REF)
                .document()
                .id

        val senderMessageRef =
            standardRef
                .document(UID)
                .collection(CONVERSATIONS_REF)
                .document(receiverID)
                .collection(MESSAGES_REF)
                .document(senderMessageId)


        val receiverMessageRef = when (receiverAccountType) {
            "Entreprise" ->
                entrepriseRef
                    .document(receiverID)
                    .collection(CONVERSATIONS_REF)
                    .document(UID)
                    .collection(MESSAGES_REF)
                    .document(senderMessageId)
            "Standard" ->
                standardRef
                    .document(receiverID)
                    .collection(CONVERSATIONS_REF)
                    .document(UID)
                    .collection(MESSAGES_REF)
                    .document(senderMessageId)
            else -> null
        }

        val message = Conversation.Message(
            messageId = senderMessageId,
            content = content,
            senderUID = UID,
            sentAt = sentAt
        )

        Firebase.firestore.runBatch { batch ->
            batch.set(receiverMessageRef!!, message)
            batch.set(senderMessageRef, message)

            batch.update(senderConversationRef, "lastMessageContent", content)
            batch.update(senderConversationRef, "lastMessageDate", sentAt)
            batch.update(senderConversationRef, "latMessageSender", UID)

            batch.update(receiverConversationRef!!, "lastMessageContent", content)
            batch.update(receiverConversationRef, "lastMessageDate", sentAt)
            batch.update(receiverConversationRef, "latMessageSender", UID)
        }
    }


    fun getStandardInformations(
        userId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (CompteStandard?) -> Unit
    ) {
        standardRef
            .document(userId)
            .get()
            .addOnSuccessListener {
                onSuccess.invoke(it?.toObject(CompteStandard::class.java))
            }
            .addOnFailureListener { result ->
                result.cause?.let { onError.invoke(it) }
            }
    }

    fun getEntrepriseInformations(
        userId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (CompteEntreprise?) -> Unit
    ) {
        entrepriseRef
            .document(userId)
            .get()
            .addOnSuccessListener {
                onSuccess.invoke(it?.toObject(CompteEntreprise::class.java))
            }
            .addOnFailureListener { result ->
                result.cause?.let { onError.invoke(it) }
            }
    }

}