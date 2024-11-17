package com.lks.esemka.esport.repository

import com.lks.esemka.esport.model.AuthModel
import com.lks.esemka.esport.model.RegisterModel
import com.lks.esemka.esport.model.User
import com.lks.esemka.esport.network.ApiClient

class AuthRepository(private val apiClient: ApiClient) {

    fun signIn(usernameOrEmail: String, password: String): User? {
        val request = AuthModel(usernameOrEmail, password)
        return apiClient.signIn(request)
    }

    fun signUp(
        fullName: String,
        username: String,
        email: String,
        phoneNumber: String,
        password: String
    ): User? {
        val request = RegisterModel(fullName, username, email, phoneNumber, password)
        return apiClient.signUp(request)
    }

}