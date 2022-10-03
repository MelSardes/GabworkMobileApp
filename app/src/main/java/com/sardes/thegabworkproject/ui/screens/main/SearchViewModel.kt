package com.sardes.thegabworkproject.ui.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.repository.SearchRepository
import com.sardes.thegabworkproject.repository.main.standard.MainStandardRepository
import com.sardes.thegabworkproject.repository.ressources.Ressources
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: SearchRepository = SearchRepository(),
    private val homeRepository: MainStandardRepository = MainStandardRepository()
) : ViewModel() {

    var searchUiState by mutableStateOf(SearchUiState())

    val hasUser: Boolean
        get() = homeRepository.hasUser()

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
        homeRepository.getFiveLatestPosts().collect {
            searchUiState = searchUiState.copy(fiveLatestPostsList = it)
        }
    }


    fun queryPlainText(query: String) {
        if (query.isNotBlank()) {
            searchUiState = searchUiState.copy(
                filteredPostsQuery = searchUiState.allPosts.data?.filter {
                    it.postName.contains(query)
                }
            )
        }
    }

    fun queryDomain(query: String) {
        if (query.isNotBlank()) {
            searchUiState = searchUiState.copy(
                filteredPostsQuery = searchUiState.allPosts.data?.filter {
                    it.domain.contains(query)
                }
            )
        }
    }

    fun queryJobType(query: String) {
        if (query.isNotBlank()) {
            searchUiState = searchUiState.copy(
                filteredPostsQuery = searchUiState.allPosts.data?.filter {
                    it.jobType.contains(query)
                }
            )
        }
    }

    fun queryLocation(query: String) {
        if (query.isNotBlank()) {
            searchUiState = searchUiState.copy(
                filteredPostsQuery = searchUiState.allPosts.data?.filter {
                    it.city.contains(query)
                }
            )
        }
    }


    fun queryFiltered(
        jobName: String,
        experienceLevel: String,
        jobType: String,
        city: String,
        province: String,
        domain: String,
    ) {
        if (jobName.isNotBlank()) {
            searchUiState = searchUiState.copy(
                filteredPostsQuery = searchUiState.allPosts.data?.filter {
                    it.experience.contains(experienceLevel) &&
                    it.jobType.contains(jobType) &&
                    it.city.contains(city) &&
                    it.province.contains(province) &&
                    it.domain.contains(domain) &&
                    it.postName.contains(jobName)
                }
            )
        } else {
            searchUiState = searchUiState.copy(
                filteredPostsQuery = searchUiState.allPosts.data?.filter {
                    it.experience.contains(experienceLevel) &&
                    it.jobType.contains(jobType) &&
                    it.city.contains(city) &&
                    it.province.contains(province) &&
                    it.domain.contains(domain)
                }
            )
        }
    }


    fun getAllPost() = viewModelScope.launch {
        repository.getAllPosts().collect {
            searchUiState = searchUiState.copy(allPosts = it)
        }
    }

    fun onQueryChange(query: String){
        searchUiState = searchUiState.copy(query = query)
    }
    fun onSearchStateChange(state: Boolean){
        searchUiState = searchUiState.copy(searchState = state)
    }

    data class SearchUiState(
        val fiveLatestPostsList: Ressources<List<CompteEntreprise.Post>> = Ressources.Loading(),

        val allPosts: Ressources<List<CompteEntreprise.Post>> = Ressources.Loading(),
        val filteredPostsQuery: List<CompteEntreprise.Post>? = null,
        val numberOfResults: Int? = null,
        val isLoading: Boolean = false,
        val queryPosts: Ressources<List<CompteEntreprise.Post>> = Ressources.Loading(),

        val query: String = "",
        val searchState: Boolean = false
    )
}