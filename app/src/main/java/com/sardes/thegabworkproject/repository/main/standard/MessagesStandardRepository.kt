package com.sardes.thegabworkproject.repository.main.standard

import com.google.firebase.Timestamp
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

private const val COMPTES_STANDARD_REF = "ComptesStandard"
private const val COMPTES_ENTREPRISE_REF = "ComptesEntreprise"
private const val MESSAGES_REF = "Messages"
private const val CONVERSATIONS_REF = "Conversations"

class MessagesStandardRepository {
    fun user() = Firebase.auth.currentUser
    fun hasUser(): Boolean = Firebase.auth.currentUser != null

    fun getUserId(): String = Firebase.auth.currentUser?.uid.orEmpty()

    private val standardRef: CollectionReference = Firebase
        .firestore.collection(COMPTES_STANDARD_REF)

    private val messagesRef: CollectionReference = Firebase
        .firestore.collection(MESSAGES_REF)

    private val conversationRef: CollectionReference = Firebase
        .firestore.collection(COMPTES_STANDARD_REF + "/" + getUserId() + "/" + CONVERSATIONS_REF)


    fun getGetAllConversations(): Flow<Ressources<List
    <Conversation>>> = callbackFlow {

        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener = conversationRef
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
                .orderBy("sentAt", Query.Direction.DESCENDING)
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


    fun writeConversation(
        lastMessageContent: String?,
        latMessageSender: String?,
        receiverName: String?,
        receiverDocRef: String?,
        receiverUrlPhoto: String?,
        senderName: String?,
        senderDocRef: String?,
        senderUrlPhoto: String?,
        senderUID: String?,
        receiverUID: String?,
        content: String?,
        sentAt: Timestamp = Timestamp.now(),
        receiverAccountType: String?,
        onComplete: (Boolean) -> Unit
    ) {
        conversationRef
            .document(receiverUID.toString())
            .get()
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    updateConversation(
                        receiverName,
                        receiverDocRef,
                        receiverUrlPhoto,
                        senderName,
                        senderDocRef,
                        senderUrlPhoto,
                        senderUID.toString(),
                        receiverUID.toString(),
                        content,
                        sentAt,
                        receiverAccountType,
                        onComplete,
                    )
                }

                else {
                    addConversation(
                        lastMessageContent,
                        latMessageSender.toString(),
                        receiverName,
                        receiverDocRef,
                        receiverUrlPhoto,
                        senderName,
                        senderDocRef,
                        senderUrlPhoto,
                        senderUID.toString(),
                        receiverUID.toString(),
                        content,
                        sentAt,
                        receiverAccountType,
                        onComplete,
                    )

                }
            }
    }


    private fun addConversation(
        lastMessageContent: String?,
        latMessageSender: String,
        receiverName: String?,
        receiverDocRef: String?,
        receiverUrlPhoto: String?,
        senderName: String?,
        senderDocRef: String?,
        senderUrlPhoto: String?,
        senderUID: String,
        receiverUID: String,
        content: String?,
        sentAt: Timestamp = Timestamp.now(),
        receiverAccountType: String?,
        onComplete: (Boolean) -> Unit
    ) {


        val senderConversation = Conversation(
            conversationId = receiverUID,
            lastMessageContent = lastMessageContent,
            lastMessageDate = sentAt.toString(),
            latMessageSender = latMessageSender,
            receiverName = receiverName,
            receiverDocRef = receiverDocRef,
            receiverUrlPhoto = receiverUrlPhoto,
            senderName = senderName,
            senderDocRef = senderDocRef,
            senderUrlPhoto = senderUrlPhoto,
        )

        val receiverConversation = Conversation(
            conversationId = getUserId(),
            lastMessageContent = lastMessageContent,
            lastMessageDate = sentAt.toString(),
            latMessageSender = latMessageSender,
            receiverName = senderName,
            receiverDocRef = senderDocRef,
            receiverUrlPhoto = senderUrlPhoto,
            senderName = receiverName,
            senderDocRef = receiverDocRef,
            senderUrlPhoto = receiverUrlPhoto,
        )

        val documentMessageId =
            conversationRef
                .document(receiverUID)
                .collection(MESSAGES_REF)
                .document().id

        val message = Conversation.Message(
            messageId = documentMessageId,
            content = content,
            senderUID = getUserId(),
            sentAt = sentAt,
        )


        conversationRef
            .document(receiverUID)
            .set(senderConversation)
            .addOnCompleteListener { task1 ->

                if (task1.isSuccessful) {

//                                          DETERMINE THE ACCOUNT TYPE OF THE RECEIVER
                    val receiverConversationRef: CollectionReference =
                        when (receiverAccountType) {
                            "Standard" -> {
                                Firebase.firestore.collection(
                                    "$COMPTES_STANDARD_REF/$receiverUID/$CONVERSATIONS_REF"
                                )
                            }

                            else -> {
                                Firebase.firestore.collection(
                                    "$COMPTES_ENTREPRISE_REF/$receiverUID/$CONVERSATIONS_REF"
                                )
                            }
                        }

//                                            AFTER CREATING CONVERSATION DOCUMENT IN SENDER SIDE
//                                            CREATE IN RECEIVER SIDE
                    receiverConversationRef
                        .document(senderUID)
                        .set(receiverConversation)
                        .addOnCompleteListener { task2 ->

                            if (task2.isSuccessful) {
                                conversationRef
                                    .document(receiverUID)
                                    .collection(MESSAGES_REF)
                                    .document(documentMessageId)
                                    .set(message)
                                    .addOnCompleteListener { task3 ->
                                        if (task3.isSuccessful) {

                                            //                    DETERMINE THE ACCOUNT TYPE OF THE RECEIVER
                                            val receiverMessageRef: CollectionReference =
                                                when (receiverAccountType) {
                                                    "Standard" ->
                                                        Firebase.firestore.collection(
                                                            "$COMPTES_STANDARD_REF/$receiverUID/$CONVERSATIONS_REF/$senderUID/$MESSAGES_REF"
                                                        )

                                                    else ->
                                                        Firebase.firestore.collection(
                                                            "$COMPTES_ENTREPRISE_REF/$receiverUID/$CONVERSATIONS_REF/$senderUID/$MESSAGES_REF"
                                                        )
                                                }

                                            //                    IF THE OPERATION HAS SUCCEED, GO TO ADD THE SAME MESSAGE
                                            //                    INTO RECEIVER MESSAGES COLLECTION
                                            receiverMessageRef
                                                .document(documentMessageId)
                                                .set(message)
                                                .addOnCompleteListener { result ->
                                                    onComplete.invoke(result.isSuccessful)
                                                }
                                        }
                                    }
                            }
                        }
                }
            }
    }


//        ADD NEW MESSAGE INTO SENDER MESSAGES COLLECTION


    private fun updateConversation(
        receiverName: String?,
        receiverDocRef: String?,
        receiverUrlPhoto: String?,
        senderName: String?,
        senderDocRef: String?,
        senderUrlPhoto: String?,
        senderUID: String,
        receiverUID: String,
        content: String?,
        sentAt: Timestamp = Timestamp.now(),
        receiverAccountType: String?,
        onComplete: (Boolean) -> Unit
    ) {

        val documentMessageId =
            conversationRef
                .document(receiverUID)
                .collection(MESSAGES_REF)
                .document().id

        val message = Conversation.Message(
            messageId = documentMessageId,
            content = content,
            senderUID = getUserId(),
            sentAt = sentAt,
        )


//        ADD NEW MESSAGE INTO SENDER MESSAGES COLLECTION
        conversationRef
            .document(receiverUID)
            .collection(MESSAGES_REF)
            .document(documentMessageId)
            .set(message)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

//                    DETERMINE THE ACCOUNT TYPE OF THE RECEIVER
                    val receiverMessageRef: CollectionReference =
                        when (receiverAccountType) {
                            "Standard" ->
                                Firebase.firestore.collection(
                                    "$COMPTES_STANDARD_REF/$receiverUID/$MESSAGES_REF"
                                )

                            else ->
                                Firebase.firestore.collection(
                                    "$COMPTES_ENTREPRISE_REF/$receiverUID/$MESSAGES_REF"
                                )
                        }


//                    IF THE OPERATION HAS SUCCEED, GO TO ADD THE SAME MESSAGE
//                    INTO RECEIVER MESSAGES COLLECTION
                    receiverMessageRef
                        .document(senderUID)
                        .collection(MESSAGES_REF)
                        .document(documentMessageId)
                        .set(message)
                        .addOnCompleteListener { task2 ->

                            if (task2.isSuccessful) {

//                                AFTER WRITTING MESSAGES,
//                                THE CONVERSATION DOCUMENT WILL BE UPDATED
//                                FIST IN SENDER SIDE
                                conversationRef
                                    .document(receiverUID)
                                    .update(
                                        mapOf(
                                            "lastMessageContent" to content,
                                            "lastMessageDate" to sentAt,
                                            "latMessageSender" to getUserId(),
                                            "receiverName" to receiverName,
                                            "receiverDocRef" to receiverDocRef,
                                            "receiverUrlPhoto" to receiverUrlPhoto,
                                            "senderName" to senderName,
                                            "senderDocRef" to senderDocRef,
                                            "senderUrlPhoto" to senderUrlPhoto,
                                        )
                                    )
                                    .addOnCompleteListener { task3 ->

                                        if (task3.isSuccessful) {

//                                          DETERMINE THE ACCOUNT TYPE OF THE RECEIVER
                                            val receiverConversationRef: CollectionReference =
                                                when (receiverAccountType) {
                                                    "Standard" -> {
                                                        Firebase.firestore.collection(
                                                            "$COMPTES_STANDARD_REF/$receiverUID/$CONVERSATIONS_REF"
                                                        )
                                                    }

                                                    else -> {
                                                        Firebase.firestore.collection(
                                                            "$COMPTES_ENTREPRISE_REF/$receiverUID/$CONVERSATIONS_REF"
                                                        )
                                                    }
                                                }

//                                            AFTER UPDATING CONVERSATION DOCUMENT IN SENDER SIDE
//                                            UPDATE IN RECEIVER SIDE
                                            receiverConversationRef
                                                .document(senderUID)
                                                .update(
                                                    mapOf(
                                                        "lastMessageContent" to content,
                                                        "lastMessageDate" to sentAt,
                                                        "latMessageSender" to getUserId(),

                                                        "receiverName" to senderName,
                                                        "receiverDocRef" to senderDocRef,
                                                        "receiverUrlPhoto" to senderUrlPhoto,

                                                        "senderName" to receiverName,
                                                        "senderDocRef" to receiverDocRef,
                                                        "senderUrlPhoto" to receiverUrlPhoto
                                                    )
                                                )
//                                                    ONLY IF ALL ABOVE OPERATIONS ARE SUCCESSFULL
//                                                      «isSucceful» IS RETURNED
                                                .addOnCompleteListener { result ->
                                                    onComplete.invoke(result.isSuccessful)
                                                }
                                        }
                                    }
                            }
                        }
                }

//                onComplete.invoke(task.isSuccessful)
            }


    }


}