package com.sardes.thegabworkproject.repository

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sardes.thegabworkproject.data.models.UserType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


private const val USERS_COLLECTION_REF = "Users"

class AuthRepository {
    // GET THE USER ID
    fun getUserId(): String = Firebase.auth.currentUser?.uid.orEmpty()

    private val usersRef: CollectionReference = Firebase
        .firestore.collection(USERS_COLLECTION_REF)


    // TO GET THE CURRENT USER
    val currentUser: FirebaseUser? = Firebase.auth.currentUser

    fun hasUser(): Boolean = Firebase.auth.currentUser != null


    // FUNCTION FOR LOGIN A USER
    suspend fun login(
        email: String,
        password: String,
        onComplete: (Boolean) -> Unit,
    ) = withContext(Dispatchers.IO) {
        Firebase.auth
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    onComplete.invoke(true)
                } else
                    onComplete.invoke(false)
            }
    }.await()!!


    fun getUserAccountType(
        userId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserType?) -> Unit,
    ) {
        usersRef
            .document(userId)
            .get()
            .addOnSuccessListener {
                onSuccess.invoke(it?.toObject(UserType::class.java))
            }
            .addOnFailureListener { result ->
                result.cause?.let { onError.invoke(it) }
            }
    }
}