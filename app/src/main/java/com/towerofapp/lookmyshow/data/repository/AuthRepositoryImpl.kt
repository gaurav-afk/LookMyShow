// data/repository/AuthRepositoryImpl.kt
package com.towerofapp.lookmyshow.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.towerofapp.lookmyshow.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override suspend fun signUp(email: String, password: String): Result<Unit> =
        try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }

    override suspend fun login(email: String, password: String): Result<Unit> =
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }

    override fun isUserLoggedIn(): Boolean = firebaseAuth.currentUser != null

    override fun logout() = firebaseAuth.signOut()
}


//
//package com.towerofapp.lookmyshow.data.repository
//
//import com.google.firebase.auth.FirebaseAuth
//import javax.inject.Inject
//
//class AuthRepository @Inject constructor(
//    private val firebaseAuth: FirebaseAuth
//) {
//
//    fun signUp(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
//        firebaseAuth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener { task ->
//                onResult(task.isSuccessful, task.exception?.message)
//            }
//    }
//
//    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
//        firebaseAuth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener { task ->
//                onResult(task.isSuccessful, task.exception?.message)
//            }
//    }
//
//    fun isUserLoggedIn() = firebaseAuth.currentUser != null
//
//    fun logout() = firebaseAuth.signOut()
//}