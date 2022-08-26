package com.sardes.thegabworkproject.repository.ressources

sealed class PostsRessources<T>(
    val data: T? = null,
    val throwable: Throwable? = null,
){
    class Loading<T>: PostsRessources<T>()
    class Success<T>(data: T?): PostsRessources<T>(data = data)
    class Error<T>(throwable: Throwable?): PostsRessources<T>(throwable = throwable)


}
