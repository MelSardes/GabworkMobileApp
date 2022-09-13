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
): ViewModel() {

    var standardMessagesUiState by mutableStateOf(StandardMessagesUiState())

    val user = repository.user()
    val hasUser: Boolean
        get() = repository.hasUser()

    private val userId: String
        get() = repository.getUserId()

    //===============================================

    fun loadAllConversations(){
        if (hasUser){
            getAllConversations(userId)
        }else{
            standardMessagesUiState = standardMessagesUiState.copy(conversationsList = Ressources.Error(
                throwable = Throwable(message = "OOPS! Utilisateur non connecté")
            ))
        }
    }
    private fun getAllConversations(userId: String) = viewModelScope.launch {
        repository.getGetAllConversations(userId).collect(){
            standardMessagesUiState = standardMessagesUiState.copy(conversationsList = it)
        }
    }

    fun getConversation(conversationId: String){
        repository.getConversation(
            conversationId = conversationId,
            onError = {}
        ){
            standardMessagesUiState = standardMessagesUiState.copy(selectedConversation = it)
        }
    }




    fun loadAllMessages(){
        if (hasUser){
            getAllMessages(userId)
        }else{
            standardMessagesUiState = standardMessagesUiState.copy(messagesList = Ressources.Error(
                throwable = Throwable(message = "OOPS! Utilisateur non connecté")
            ))
        }
    }
    private fun getAllMessages(userId: String) = viewModelScope.launch {
        repository.getAllMessages(userId).collect(){
            standardMessagesUiState = standardMessagesUiState.copy(messagesList = it)
        }
    }

    fun getMessage(messageId: String){
        repository.getMessage(
            messageId = messageId,
            onError = {}
        ){
            standardMessagesUiState = standardMessagesUiState.copy(selectedMessage = it)
        }
    }


}

data class StandardMessagesUiState(
    val conversationsList: Ressources<List<Conversation>> = Ressources.Loading(),
    val messagesList: Ressources<List<Conversation.Message>> = Ressources.Loading(),

    val selectedConversation: Conversation? = null,
    val selectedMessage: Conversation.Message? = null
)