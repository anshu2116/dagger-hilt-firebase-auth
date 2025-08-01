package com.example.firebaseauthapp.data.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<FirebaseUser?>
    suspend fun signup(name: String, email: String, password: String): Result<FirebaseUser?>
}
