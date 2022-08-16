package com.sardes.thegabworkproject.repository

import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sardes.thegabworkproject.models.Compte_Standard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


const val COMPTES_STANDARD_COLLECTION_REF = "ComptesStandard"


class SignUpRepository {



    val currentUser: FirebaseUser? = Firebase.auth.currentUser
    fun user() = Firebase.auth.currentUser
    fun hasUser(): Boolean = Firebase.auth.currentUser != null

    fun getUserId(): String = Firebase.auth.currentUser?.uid.orEmpty()


    private val comptesStandardRef: CollectionReference = Firebase
        .firestore.collection(COMPTES_STANDARD_COLLECTION_REF)


    // FUNCTION FOR CREATE A USER
    suspend fun createUser(
        email: String,
        password: String,
        onComplete:(Boolean) -> Unit
    ) = withContext(Dispatchers.IO){
        Firebase.auth
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    onComplete.invoke(true)
                }else
                    onComplete.invoke(false)
            }
    }.await()



    fun addUserInformations(
        userId: String,
        userName: String,
        userForeName: String,
        userPassword: String,
        sex: String,
        phone: String,
        email: String,
        city: String,
        nationality: String,
        address: String,
        urlPhoto: String,
        timestamp: Timestamp,
        onComplete: (Boolean) -> Unit
    ){
        val standardUser = Compte_Standard(
            userId,
            userName,
            userForeName,
            userPassword,
            sex,
            phone,
            email,
            city,
            nationality,
            address,
            urlPhoto,
            timestamp
        )

        comptesStandardRef
            .document(userId)
            .set(standardUser)
            .addOnCompleteListener {result ->
                onComplete.invoke(result.isSuccessful)
            }
    }
}

