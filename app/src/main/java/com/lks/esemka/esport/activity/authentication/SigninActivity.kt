package com.lks.esemka.esport.activity.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputEditText
import com.lks.esemka.esport.R
import com.lks.esemka.esport.activity.main.MainScreenActivity
import com.lks.esemka.esport.network.ApiClient
import com.lks.esemka.esport.repository.AuthRepository
import com.lks.esemka.esport.viewmodel.AuthViewModelFactory
import com.lks.esemka.esport.viewmodel.AuthViewModel

class SigninActivity : AppCompatActivity() {

    private lateinit var usernameTxt: TextInputEditText
    private lateinit var passwordTxt: TextInputEditText
    private lateinit var backBtn: ImageView
    private lateinit var toSignUpBtn: TextView
    private lateinit var loginBtn: Button

    private val authViewModel: AuthViewModel by viewModels {
        AuthViewModelFactory(AuthRepository(ApiClient()))
    }

    companion object {
        const val USN_KEY = "USERNAME"
    }

    // override function

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signin)
        initComponents()
        onClickHandler()

    }

    // my own function

    private fun initComponents() {
        usernameTxt = findViewById(R.id.usernameSignInTxt)
        passwordTxt = findViewById(R.id.passwordSignInTxt)
        backBtn = findViewById(R.id.backBtn)
        toSignUpBtn = findViewById(R.id.toSignUpFromSignIn)
        loginBtn = findViewById(R.id.signin_btn)
    }

    private fun onClickHandler() {

        backBtn.setOnClickListener {
            finish()
        }

        toSignUpBtn.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }

        loginBtn.setOnClickListener {
            val username = usernameTxt.text.toString()
            val password = passwordTxt.text.toString()
            if (username.isNotEmpty() && password.isNotEmpty()) {
                // execute the function
                authViewModel.signIn(username, password)
                loginObserver()
            } else {
                Toast.makeText(this, "Sign In Failed", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun loginObserver() {
        authViewModel.user.observe(this, Observer { user ->
            if (user != null) {
                // user berhasil login
                Toast.makeText(this, "Welcome back ${user.fullName}", Toast.LENGTH_SHORT).show()
                // send data username to team fragment
                val i = Intent(this, MainScreenActivity::class.java)
                i.putExtra(USN_KEY, user.fullName)
                startActivity(i)
                finish()
            } else {
                // user gagal login
                Toast.makeText(this, "Sign-in Failed", Toast.LENGTH_SHORT).show()
            }

        })
    }


}