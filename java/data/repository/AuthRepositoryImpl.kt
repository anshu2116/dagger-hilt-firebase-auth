package com.example.firebaseauthapp.data.repository

import com.example.firebaseauthapp.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<FirebaseUser?> {
        return try {
            val user = auth.signInWithEmailAndPassword(email, password).await().user
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signup(name: String, email: String, password: String): Result<FirebaseUser?> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val user = result.user
            user?.let {
                val userInfo = User(it.uid, it.email ?: "", name)
                firestore.collection("users").document(it.uid).set(userInfo).await()
            }
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
