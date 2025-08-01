package com.example.firebaseauthapp.domain.usecase

import com.example.firebaseauthapp.data.repository.AuthRepository
import javax.inject.Inject

class SignupUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(name: String, email: String, password: String) = authRepository.signup(name, email, password)
}
