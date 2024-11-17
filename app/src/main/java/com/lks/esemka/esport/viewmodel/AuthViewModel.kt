package com.lks.esemka.esport.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lks.esemka.esport.model.User
import com.lks.esemka.esport.repository.AuthRepository

class AuthViewModel(private val authRepository: AuthRepository): ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    fun signIn(usernameOrEmail: String, password: String) {
        Thread {
            val loggedInUser = authRepository.signIn(usernameOrEmail, password)
            _user.postValue(loggedInUser)
        }.start()
    }

    fun signUp(
        fullName: String,
        username: String,
        email: String,
        phoneNumber: String,
        password: String
    ) {
        Thread {
            val signedUser = authRepository.signUp(fullName, username, email, phoneNumber, password)
            Log.d("ViewModel", "Sign-up response: $signedUser") // Tambahkan log ini
            _user.postValue(signedUser)
        }.start()
    }

}