package com.sardes.thegabworkproject.repository.ressources

sealed class Ressources<T>(
    val data: T? = null,
    val throwable: Throwable? = null,
){
    class Loading<T>: Ressources<T>()
    class Success<T>(data: T?): Ressources<T>(data = data)
    class Error<T>(throwable: Throwable?): Ressources<T>(throwable = throwable)


}
