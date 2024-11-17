package com.lks.esemka.esport.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.lks.esemka.esport.activity.mainscreen.MainScreenContainer
import com.lks.esemka.esport.network.ApiClient
import com.lks.esemka.esport.repository.AuthRepository
import com.lks.esemka.esport.repository.AuthViewModelFactory
import com.lks.esemka.esport.viewmodel.AuthViewModel

class SignupActivity : AppCompatActivity() {

    private lateinit var fullNameTxt: TextInputEditText
    private lateinit var usernameTxt: TextInputEditText
    private lateinit var emailTxt: TextInputEditText
    private lateinit var passwordTxt: TextInputEditText
    private lateinit var phoneNumberTxt: TextInputEditText
    private lateinit var signUpBtn: Button
    private lateinit var backBtn: ImageView
    private lateinit var toSignInBtn: TextView

    // inisialisasi authViewModel di activity
    private val authViewModel: AuthViewModel by viewModels {
        AuthViewModelFactory(AuthRepository(ApiClient()))
    }

    // override function
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        initComponents()
//        registerObserver()
        onClickHandler()

    }

    // my own function

    private fun initComponents() {
        fullNameTxt = findViewById(R.id.fullNameTxt)
        usernameTxt = findViewById(R.id.usernameSignUpTxt)
        emailTxt = findViewById(R.id.emailTxt)
        passwordTxt = findViewById(R.id.passwordSignUpTxt)
        phoneNumberTxt = findViewById(R.id.phoneNumberTxt)
        signUpBtn = findViewById(R.id.signup_btn)
        backBtn = findViewById(R.id.backBtn)
        toSignInBtn = findViewById(R.id.toSignInFromSignUp)
    }

    private fun onClickHandler() {

        backBtn.setOnClickListener {
            finish()
        }

        toSignInBtn.setOnClickListener {
            startActivity(Intent(this, SigninActivity::class.java))
            finish()
        }

        signUpBtn.setOnClickListener {
            val fullName = fullNameTxt.text.toString()
            val username = usernameTxt.text.toString()
            val email = emailTxt.text.toString()
            val password = passwordTxt.text.toString()
            val phoneNumber = phoneNumberTxt.text.toString()

            Log.d("SignUpInput", "FullName: $fullName, Username: $username, Email: $email, PhoneNumber: $phoneNumber")

            if (fullName.isNotBlank() && username.isNotBlank() && email.isNotBlank() && password.isNotBlank()) {
                Log.d("SignupActivity", "Sign-up initiated with: $fullName, $username, $email, $phoneNumber")
                authViewModel.signUp(fullName, username, email, phoneNumber, password)
                startActivity(Intent(this, SigninActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                Log.d("SignupActivity", "Sign-up failed: Fields are blank")
                Log.d("on-click-regis", "fail in on-click-regis")
            }
        }

    }

    // untuk sementara tidak digunakan
    private fun registerObserver() {
        authViewModel.user.observe(this, Observer { user ->
            Log.d("Observer", "Observed user: $user")
            if (user != null) {
                // user berhasil register
                Toast.makeText(this, "Welcome ${user.fullName}", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, SigninActivity::class.java))
                finish()
            } else {
                // user gagal register
                Toast.makeText(this, "Sign-up Failed", Toast.LENGTH_SHORT).show()
                Log.d("regis-obs", "fail in regis-observer")
                Log.d("Observer", "Observed user: null")
            }

        })
    }

}