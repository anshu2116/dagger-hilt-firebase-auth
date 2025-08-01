package com.example.firebaseauthapp.ui.signup

import androidx.lifecycle.*
import com.example.firebaseauthapp.domain.usecase.SignupUseCase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signupUseCase: SignupUseCase
) : ViewModel() {

    private val _signupState = MutableLiveData<Result<FirebaseUser?>>()
    val signupState: LiveData<Result<FirebaseUser?>> = _signupState

    fun signup(name: String, email: String, password: String) {
        viewModelScope.launch {
            _signupState.value = signupUseCase(name, email, password)
        }
    }
}
