package com.sardes.thegabworkproject.ui.screens.main.mainStandard.message

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.data.models.CompteStandard
import com.sardes.thegabworkproject.data.models.Conversation
import com.sardes.thegabworkproject.data.models.UserType
import com.sardes.thegabworkproject.repository.main.MessagesRepository
import com.sardes.thegabworkproject.repository.main.standard.MainStandardRepository
import com.sardes.thegabworkproject.repository.ressources.Ressources
import kotlinx.coroutines.launch

class MessagesStandardViewModel(
    private val repository: MessagesRepository = MessagesRepository(),
    private val mainRepository: MainStandardRepository = MainStandardRepository()
) : ViewModel() {

    var standardMessagesUiState by mutableStateOf(StandardMessagesUiState())

    val user = repository.user()
    val hasUser: Boolean
        get() = repository.hasUser()

    val userId: String
        get() = repository.getUserId()

    //===============================================


    fun getUserInformations() {
        mainRepository.getUserInformations(userId, onError = {}) {
            standardMessagesUiState = standardMessagesUiState.copy(userInformations = it)
        }
    }


    fun getUserAccountType(userId: String) {
        repository.getUserAccountType(userId = userId, onError = {}) {
            standardMessagesUiState = standardMessagesUiState.copy(chatUserType = it)
        }
    }


    fun loadAllConversations() {
        if (hasUser) {
            getAllConversations(userId)
        } else {
            standardMessagesUiState = standardMessagesUiState.copy(
                conversationsList = Ressources.Error(
                    throwable = Throwable(message = "OOPS! Utilisateur non connecté")
                )
            )
        }
    }

    private fun getAllConversations(userId: String) = viewModelScope.launch {
        repository.getGetAllConversations(userId = userId).collect() {
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



    private fun createConversation(
        receiverName: String,
        receiverID: String,
        receiverAccountType: String,
        receiverUrlPhoto: String,
    ) {
        repository.createConversation(
            UID = userId,
            receiverID = receiverID,
            receiverName = receiverName,
            receiverAccountType = receiverAccountType,
            senderUrlPhoto = standardMessagesUiState.userInformations?.urlPhoto,
            senderName = standardMessagesUiState.userInformations?.name,
            content = standardMessagesUiState.messageContent!!,
            receiverUrlPhoto = receiverUrlPhoto,
        ) {
            standardMessagesUiState = standardMessagesUiState.copy(messageAddedStatus = it)
        }
    }


    fun getStandardInformations(userId: String) {
        repository.getStandardInformations(
            userId,
            onError = {}
        ) {
            standardMessagesUiState = standardMessagesUiState.copy(chatWithStandard = it)
        }
    }

    fun getEntrepriseInformations(userId: String) {
        repository.getEntrepriseInformations(
            userId,
            onError = {}
        ) {
            standardMessagesUiState = standardMessagesUiState.copy(chatWithEntreprise = it)
        }
    }


    fun addMessage() {

        if (standardMessagesUiState.messageContent?.isNotEmpty() == true) {
            if (standardMessagesUiState.selectedConversation == null) {
                when (standardMessagesUiState.chatUserType?.account) {
                    "Standard" ->
                        createConversation(
                            receiverName = standardMessagesUiState.chatWithStandard?.name.toString(),
                            receiverID = standardMessagesUiState.chatWithStandard?.userId.toString(),
                            receiverAccountType = standardMessagesUiState.chatWithStandard?.accountType.toString(),
                            receiverUrlPhoto = standardMessagesUiState.chatWithStandard?.urlPhoto.toString()
                        )

                    "Entreprise" -> {
                        createConversation(
                            receiverName = standardMessagesUiState.chatWithEntreprise?.nom.toString(),
                            receiverID = standardMessagesUiState.chatWithEntreprise?.entrepriseId.toString(),
                            receiverAccountType = standardMessagesUiState.chatWithEntreprise?.typeDeCompte.toString(),
                            receiverUrlPhoto = standardMessagesUiState.chatWithEntreprise?.urlLogo.toString()
                        )
                    }
                }
            } else {
                when (standardMessagesUiState.chatUserType?.account) {
                    "Standard" -> createConversation(
                        receiverName = standardMessagesUiState.chatWithStandard?.name.toString(),
                        receiverID = standardMessagesUiState.chatWithStandard?.userId.toString(),
                        receiverAccountType = standardMessagesUiState.chatWithStandard?.accountType.toString(),
                        receiverUrlPhoto = standardMessagesUiState.chatWithStandard?.urlPhoto.toString()
                    )

                    "Entreprise" -> createConversation(
                        receiverName = standardMessagesUiState.chatWithEntreprise?.nom.toString(),
                        receiverID = standardMessagesUiState.chatWithEntreprise?.entrepriseId.toString(),
                        receiverAccountType = standardMessagesUiState.chatWithEntreprise?.typeDeCompte.toString(),
                        receiverUrlPhoto = standardMessagesUiState.chatWithEntreprise?.urlLogo.toString()
                    )
                }
            }
        }

        standardMessagesUiState = standardMessagesUiState.copy(messageContent = "")
    }

    fun onMessageContentChange(content: String) {
        standardMessagesUiState = standardMessagesUiState.copy(messageContent = content)
    }
}

data class StandardMessagesUiState(
    val userInformations: CompteStandard? = null,

    val conversationsList: Ressources<List<Conversation>> = Ressources.Loading(),
    val messagesList: Ressources<List<Conversation.Message>> = Ressources.Loading(),

    val selectedConversation: Conversation? = null,
    val selectedMessage: Conversation.Message? = null,

    val messageContent: String? = "",

    val messageAddedStatus: Boolean = false,

    val chatWithStandard: CompteStandard? = null,
    val chatWithEntreprise: CompteEntreprise? = null,

    val chatUserType: UserType? = null
)