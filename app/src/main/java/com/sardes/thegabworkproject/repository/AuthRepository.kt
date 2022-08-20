package com.sardes.thegabworkproject.repository

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthRepository {
    // TO GET THE CURRENT USER
    val currentUser: FirebaseUser? = Firebase.auth.currentUser

    fun hasUser(): Boolean = Firebase.auth.currentUser != null

    // GET THE USER ID
    fun getUserId(): String = Firebase.auth.currentUser?.uid.orEmpty()

    // FUNCTION FOR LOGIN A USER
    suspend fun login(
        email: String,
        password: String,
        onComplete:(Boolean) -> Unit
    ) = withContext(Dispatchers.IO){
        Firebase.auth
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    onComplete.invoke(true)
                }else
                    onComplete.invoke(false)
            }
    }.await()!!
}