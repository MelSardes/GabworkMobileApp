package com.sardes.thegabworkproject.ui.screens.main.mainStandard.message

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sardes.thegabworkproject.data.models.Conversation
import com.sardes.thegabworkproject.repository.main.standard.MessagesStandardRepository
import com.sardes.thegabworkproject.repository.ressources.Ressources
import kotlinx.coroutines.launch

class MessagesStandardViewModel(
    private val repository: MessagesStandardRepository = MessagesStandardRepository()
) : ViewModel() {

    var standardMessagesUiState by mutableStateOf(StandardMessagesUiState())

    val user = repository.user()
    val hasUser: Boolean
        get() = repository.hasUser()

    val userId: String
        get() = repository.getUserId()

    //===============================================

    fun loadAllConversations() {
        if (hasUser) {
            getAllConversations()
        } else {
            standardMessagesUiState = standardMessagesUiState.copy(
                conversationsList = Ressources.Error(
                    throwable = Throwable(message = "OOPS! Utilisateur non connecté")
                )
            )
        }
    }

    private fun getAllConversations() = viewModelScope.launch {
        repository.getGetAllConversations().collect() {
            standardMessagesUiState = standardMessagesUiState.copy(conversationsList = it)
        }
    }

    fun getConversation(conversationId: String) {
        repository.getConversation(
            conversationId = conversationId,
            onError = {}
        ) {
            standardMessagesUiState = standardMessagesUiState.copy(selectedConversation = it)
        }
    }


    fun loadAllMessages(conversationId: String) {
        if (hasUser) {
            getAllMessages(conversationId)
        } else {
            standardMessagesUiState = standardMessagesUiState.copy(
                messagesList = Ressources.Error(
                    throwable = Throwable(message = "OOPS! Utilisateur non connecté")
                )
            )
        }
    }

    private fun getAllMessages(conversationId: String) = viewModelScope.launch {
        repository.getAllMessages(conversationId).collect() {
            standardMessagesUiState = standardMessagesUiState.copy(messagesList = it)
        }
    }

    fun getMessage(messageId: String, conversationId: String) {
        repository.getMessage(
            messageId = messageId,
            conversationId = conversationId,
            onError = {}
        ) {
            standardMessagesUiState = standardMessagesUiState.copy(selectedMessage = it)
        }
    }


    fun addMessage() {
        if (hasUser) {
            repository.writeConversation(
                lastMessageContent = standardMessagesUiState.messageContent,
                latMessageSender = userId,
                receiverName = standardMessagesUiState.selectedConversation?.receiverName,
                receiverDocRef = standardMessagesUiState.selectedConversation?.receiverDocRef,
                receiverUrlPhoto = standardMessagesUiState.selectedConversation?.receiverUrlPhoto,
                senderName = standardMessagesUiState.selectedConversation?.senderName,
                senderDocRef = standardMessagesUiState.selectedConversation?.senderDocRef,
                senderUrlPhoto = standardMessagesUiState.selectedConversation?.senderUrlPhoto,
                senderUID = userId,
                receiverUID = standardMessagesUiState.selectedConversation?.receiverUID,
                content = standardMessagesUiState.messageContent,
                receiverAccountType = standardMessagesUiState.selectedConversation?.receiverAccountType,
            ) {
                standardMessagesUiState = standardMessagesUiState.copy(messageAddedStatus = it)
            }
        }
    }


    fun onMessageContentChange(content: String) {
        standardMessagesUiState = standardMessagesUiState.copy(messageContent = content)
    }
}

data class StandardMessagesUiState(
    val conversationsList: Ressources<List<Conversation>> = Ressources.Loading(),
    val messagesList: Ressources<List<Conversation.Message>> = Ressources.Loading(),

    val selectedConversation: Conversation? = null,
    val selectedMessage: Conversation.Message? = null,

    val messageContent: String? = null,

    val messageAddedStatus: Boolean = false,

    )