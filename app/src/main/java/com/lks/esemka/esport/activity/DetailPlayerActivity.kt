package com.lks.esemka.esport.activity

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.lks.esemka.esport.R
import com.lks.esemka.esport.model.Player

class DetailPlayerActivity : AppCompatActivity() {

    private lateinit var playerImg: ImageView
    private lateinit var tvPlayerName: TextView
    private lateinit var tvPlayerTeam: TextView
    private lateinit var tvPlayerRole: TextView
    private lateinit var btnBack: ImageView

    companion object {
        const val DATA_PEMAIN = "data_pemain"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_player)
        initComponents()
        onClickAction()

        val data  = intent.getParcelableExtra<Player>(DATA_PEMAIN)
        playerImg.setImageURI(Uri.parse(data?.image))
        tvPlayerName.text = data?.ign
        tvPlayerTeam.text = "(${data?.team?.name})"
        Log.d("Detail-Player ${data?.ign}", "Team of player ${data?.ign} is ${data?.team?.name}")
        tvPlayerRole.text = data?.playerRole?.name

    }

    private fun initComponents() {
        playerImg = findViewById(R.id.fotoPemain)
        tvPlayerName = findViewById(R.id.tvName)
        tvPlayerTeam = findViewById(R.id.tvTeam)
        tvPlayerRole = findViewById(R.id.tvRole)
        btnBack = findViewById(R.id.btnBackFromDetailPemain)
    }

    private fun onClickAction() {

        btnBack.setOnClickListener {
            finish()
        }

    }

}