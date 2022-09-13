package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.message

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sardes.thegabworkproject.data.models.Conversation
import com.sardes.thegabworkproject.repository.main.entreprise.MessagesEntrepriseRepository
import com.sardes.thegabworkproject.repository.ressources.Ressources
import kotlinx.coroutines.launch

class MessagesEntrepriseViewModel(
    private val repository: MessagesEntrepriseRepository = MessagesEntrepriseRepository()
) : ViewModel() {

    var entrepriseMessagesUiState by mutableStateOf(EntrepriseMessagesUiState())

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
            entrepriseMessagesUiState = entrepriseMessagesUiState.copy(conversationsList = Ressources.Error(
                throwable = Throwable(message = "OOPS! Utilisateur non connecté")
            ))
        }
    }
    private fun getAllConversations(userId: String) = viewModelScope.launch {
        repository.getGetAllConversations(userId).collect(){
            entrepriseMessagesUiState = entrepriseMessagesUiState.copy(conversationsList = it)
        }
    }

    fun getConversation(conversationId: String){
        repository.getConversation(
            conversationId = conversationId,
            onError = {}
        ){
            entrepriseMessagesUiState = entrepriseMessagesUiState.copy(selectedConversation = it)
        }
    }




    fun loadAllMessages(){
        if (hasUser){
            getAllMessages(userId)
        }else{
            entrepriseMessagesUiState = entrepriseMessagesUiState.copy(messagesList = Ressources.Error(
                throwable = Throwable(message = "OOPS! Utilisateur non connecté")
            ))
        }
    }
    private fun getAllMessages(userId: String) = viewModelScope.launch {
        repository.getAllMessages(userId).collect(){
            entrepriseMessagesUiState = entrepriseMessagesUiState.copy(messagesList = it)
        }
    }

    fun getMessage(messageId: String){
        repository.getMessage(
            messageId = messageId,
            onError = {}
        ){
            entrepriseMessagesUiState = entrepriseMessagesUiState.copy(selectedMessage = it)
        }
    }


}

data class EntrepriseMessagesUiState(
    val conversationsList: Ressources<List<Conversation>> = Ressources.Loading(),
    val messagesList: Ressources<List<Conversation.Message>> = Ressources.Loading(),

    val selectedConversation: Conversation? = null,
    val selectedMessage: Conversation.Message? = null
)