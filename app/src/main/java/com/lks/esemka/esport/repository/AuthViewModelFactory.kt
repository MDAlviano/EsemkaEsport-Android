package com.lks.esemka.esport.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lks.esemka.esport.viewmodel.AuthViewModel

class AuthViewModelFactory(private val authRepository: AuthRepository): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(authRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}