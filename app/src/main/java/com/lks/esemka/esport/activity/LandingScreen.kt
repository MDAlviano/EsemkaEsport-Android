package com.lks.esemka.esport.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lks.esemka.esport.R

class LandingScreen : AppCompatActivity() {
    
    private lateinit var toSignInBtn: Button
    private lateinit var toSignUpbtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_landing_screen)

        toSignInBtn = findViewById(R.id.btn_signIn)
        toSignUpbtn = findViewById(R.id.btn_signUp)

        toSignInBtn.setOnClickListener {
            val iSignIn = Intent(this, SigninActivity::class.java)
            startActivity(iSignIn)
        }

        toSignUpbtn.setOnClickListener {
            val iSignUp = Intent(this, SignupActivity::class.java)
            startActivity(iSignUp)
        }

    }
}