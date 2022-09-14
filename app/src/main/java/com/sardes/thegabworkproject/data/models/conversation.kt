package com.sardes.thegabworkproject.data.models

import com.google.firebase.Timestamp

data class Conversation(
    val conversationId: String? = null,
    val lastMessageContent: String? = null,
    val lastMessageDate: String? = null,
    val latMessageSender: String? = null,

    val receiverName: String? = null,
    val receiverDocRef: String? = null,
    val receiverUrlPhoto: String? = null,
    val receiverUID: String? = null,

    val senderName: String? = null,
    val senderDocRef: String? = null,
    val senderUrlPhoto: String? = null,
    val receiverAccountType: String? = null

) {
    data class Message(
        val messageId: String? = null,

        val content: String? = null,

        val senderUID: String = "",

        val sentAt: Timestamp = Timestamp.now(),
    )
}
