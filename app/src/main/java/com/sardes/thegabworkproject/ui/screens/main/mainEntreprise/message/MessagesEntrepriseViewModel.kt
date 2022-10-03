package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.message

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

class MessagesEntrepriseViewModel(
    private val repository: MessagesRepository = MessagesRepository(),
    private val mainRepository: MainStandardRepository = MainStandardRepository()

) : ViewModel() {

    var entrepriseMessagesUiState by mutableStateOf(EntrepriseMessagesUiState())

    val user = repository.user()
    val hasUser: Boolean
        get() = repository.hasUser()

    val userId: String
        get() = repository.getUserId()

    //===============================================


    fun getUserInformations() {
        mainRepository.getUserInformations(userId, onError = {}) {
            entrepriseMessagesUiState = entrepriseMessagesUiState.copy(userInformations = it)
        }
    }


    fun getUserAccountType(userId: String) {
        repository.getUserAccountType(userId = userId, onError = {}) {
            entrepriseMessagesUiState = entrepriseMessagesUiState.copy(chatUserType = it)
        }
    }


    fun loadAllConversations() {
        if (hasUser) {
            getAllConversations(userId)
        } else {
            entrepriseMessagesUiState = entrepriseMessagesUiState.copy(
                conversationsList = Ressources.Error(
                    throwable = Throwable(message = "OOPS! Utilisateur non connecté")
                )
            )
        }
    }

    private fun getAllConversations(userId: String) = viewModelScope.launch {
        repository.getGetAllConversations(userId).collect() {
            entrepriseMessagesUiState = entrepriseMessagesUiState.copy(conversationsList = it)
        }
    }

    fun getConversation(conversationId: String) {
        repository.getConversation(
            conversationId = conversationId,
            onError = {}
        ) {
            entrepriseMessagesUiState = entrepriseMessagesUiState.copy(selectedConversation = it)
        }
    }


    fun loadAllMessages(conversationId: String) {
        if (hasUser) {
            getAllMessages(conversationId)
        } else {
            entrepriseMessagesUiState = entrepriseMessagesUiState.copy(
                messagesList = Ressources.Error(
                    throwable = Throwable(message = "OOPS! Utilisateur non connecté")
                )
            )
        }
    }

    private fun getAllMessages(userId: String) = viewModelScope.launch {
        repository.getAllMessages(userId).collect() {
            entrepriseMessagesUiState = entrepriseMessagesUiState.copy(messagesList = it)
        }
    }

    fun getMessage(messageId: String, conversationId: String) {
        repository.getMessage(
            messageId = messageId,
            conversationId = conversationId,
            onError = {}
        ) {
            entrepriseMessagesUiState = entrepriseMessagesUiState.copy(selectedMessage = it)
        }
    }


    fun addMessage() {

        if (entrepriseMessagesUiState.messageContent?.isNotEmpty() == true) {
            if (entrepriseMessagesUiState.selectedConversation == null) {
                when (entrepriseMessagesUiState.chatUserType?.account) {
                    "Standard" ->
                        createConversation(
                            receiverName = entrepriseMessagesUiState.chatWithStandard?.name.toString(),
                            receiverID = entrepriseMessagesUiState.chatWithStandard?.userId.toString(),
                            receiverAccountType = entrepriseMessagesUiState.chatWithStandard?.accountType.toString(),
                            receiverUrlPhoto = entrepriseMessagesUiState.chatWithStandard?.urlPhoto.toString()
                        )

                    "Entreprise" -> {
                        createConversation(
                            receiverName = entrepriseMessagesUiState.chatWithEntreprise?.nom.toString(),
                            receiverID = entrepriseMessagesUiState.chatWithEntreprise?.entrepriseId.toString(),
                            receiverAccountType = entrepriseMessagesUiState.chatWithEntreprise?.typeDeCompte.toString(),
                            receiverUrlPhoto = entrepriseMessagesUiState.chatWithEntreprise?.urlLogo.toString()
                        )
                    }
                }
            } else {
                when (entrepriseMessagesUiState.chatUserType?.account) {
                    "Standard" -> createConversation(
                        receiverName = entrepriseMessagesUiState.chatWithStandard?.name.toString(),
                        receiverID = entrepriseMessagesUiState.chatWithStandard?.userId.toString(),
                        receiverAccountType = entrepriseMessagesUiState.chatWithStandard?.accountType.toString(),
                        receiverUrlPhoto = entrepriseMessagesUiState.chatWithStandard?.urlPhoto.toString()
                    )

                    "Entreprise" -> createConversation(
                        receiverName = entrepriseMessagesUiState.chatWithEntreprise?.nom.toString(),
                        receiverID = entrepriseMessagesUiState.chatWithEntreprise?.entrepriseId.toString(),
                        receiverAccountType = entrepriseMessagesUiState.chatWithEntreprise?.typeDeCompte.toString(),
                        receiverUrlPhoto = entrepriseMessagesUiState.chatWithEntreprise?.urlLogo.toString()
                    )
                }
            }
        }

        entrepriseMessagesUiState = entrepriseMessagesUiState.copy(messageContent = "")
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
            senderUrlPhoto = entrepriseMessagesUiState.userInformations?.urlPhoto,
            senderName = entrepriseMessagesUiState.userInformations?.name,
            content = entrepriseMessagesUiState.messageContent!!,
            receiverUrlPhoto = receiverUrlPhoto,
        ) {
            entrepriseMessagesUiState = entrepriseMessagesUiState.copy(messageAddedStatus = it)
        }
    }

    fun getStandardInformations(userId: String) {
        repository.getStandardInformations(
            userId,
            onError = {}
        ) {
            entrepriseMessagesUiState = entrepriseMessagesUiState.copy(chatWithStandard = it)
        }
    }

    fun getEntrepriseInformations(userId: String) {
        repository.getEntrepriseInformations(
            userId,
            onError = {}
        ) {
            entrepriseMessagesUiState = entrepriseMessagesUiState.copy(chatWithEntreprise = it)
        }
    }

    fun onMessageContentChange(content: String) {
        entrepriseMessagesUiState = entrepriseMessagesUiState.copy(messageContent = content)
    }

}

data class EntrepriseMessagesUiState(
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