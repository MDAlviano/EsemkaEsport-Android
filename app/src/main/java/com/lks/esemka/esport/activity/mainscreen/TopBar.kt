package com.lks.esemka.esport.activity.mainscreen

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lks.esemka.esport.R

class TopBar : AppCompatActivity() {

    private lateinit var usernameTxt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_top_bar)
        initComponents()

        val username = intent.getStringExtra(MainScreenContainer.USN_KEY) ?: "Guest"
        Log.d("TopBar", "user : $username")
        usernameTxt.text = "Halo $username 👋"

    }

    private fun initComponents() {
        usernameTxt = findViewById(R.id.namePemainFragmentTxt)
    }

    private fun onClickAction() {

    }


}