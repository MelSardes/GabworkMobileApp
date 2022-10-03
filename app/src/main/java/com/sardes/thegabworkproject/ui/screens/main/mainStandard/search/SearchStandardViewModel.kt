package com.sardes.thegabworkproject.ui.screens.main.mainStandard.search

/*
class SearchStandardViewModel(
    private val repository: MainStandardRepository = MainStandardRepository(),
    private val searchRepository: SearchRepository = SearchRepository(),
): ViewModel() {

    var searchUiState by mutableStateOf(SearchViewModel.SearchUiState())

    val hasUser: Boolean
        get() = repository.hasUser()

    fun loadFiveLatestPosts() {
        if (hasUser) {
            getFiveLatestPosts()
        } else {
            searchUiState = searchUiState.copy(
                fiveLatestPostsList = Ressources.Error(
                    throwable = Throwable(message = "Utilisateur non connecté")
                )
            )
        }
    }



    private fun getFiveLatestPosts() = viewModelScope.launch {
        repository.getFiveLatestPosts().collect {
            searchUiState = searchUiState.copy(fiveLatestPostsList = it)
        }
    }



}
*/

/*
data class SearchUiState(
    val query:String? = null,

    val fiveLatestPostsList: Ressources<List<CompteEntreprise.Post>> = Ressources.Loading(),
    val queryPostsList: Ressources<List<CompteEntreprise.Post>> = Ressources.Loading(),

    val selectedPost: CompteEntreprise.Post? = null,

    )*/
