package com.sardes.thegabworkproject.repository

sealed class Ressources<T>(
    val data: T? = null,
    val throwable: Throwable? = null,
){
    class Loading<T>: Ressources<T>()
    class Success<T>(data: T?):Ressources<T>(data = data)
    class Error<T>(throwable: Throwable?):Ressources<T>(throwable = throwable)


}
