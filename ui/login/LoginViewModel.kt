package com.example.firebaseauthapp.ui.login

import androidx.lifecycle.*
import com.example.firebaseauthapp.domain.usecase.LoginUseCase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginState = MutableLiveData<Result<FirebaseUser?>>()
    val loginState: LiveData<Result<FirebaseUser?>> = _loginState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = loginUseCase(email, password)
        }
    }
}
