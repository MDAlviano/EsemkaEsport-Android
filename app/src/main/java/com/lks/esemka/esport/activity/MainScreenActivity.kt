package com.lks.esemka.esport.activity

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lks.esemka.esport.R
import com.lks.esemka.esport.adapter.PlayerAdapter
import com.lks.esemka.esport.adapter.TeamAdapter
import com.lks.esemka.esport.model.Player
import com.lks.esemka.esport.model.Team
import com.lks.esemka.esport.network.ApiClient

class MainScreenActivity : AppCompatActivity() {

    private lateinit var usernameTxt: TextView
    private lateinit var showTeamsBtn: LinearLayout
    private lateinit var showPlayersBtn: LinearLayout
    private lateinit var showTeamsBtnTxt: TextView
    private lateinit var showPlayersBtnTxt: TextView
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_screen)
        initComponents()
        onClickHandlers()

        // display username
        val username = intent.getStringExtra(SigninActivity.USN_KEY) ?: "Guest"
        usernameTxt.text = "Halo $username 👋"

        // set default recycler view for teams
//        val teams = mutableListOf<Team>()
//        val teamAdapter = TeamAdapter(teams)
//        recyclerView.layoutManager = GridLayoutManager(this, 2)
//        recyclerView.adapter = teamAdapter
//
//        ApiClient().getTeams { fetchedTeams ->
//            teams.clear()
//            teams.addAll(fetchedTeams)
//            teamAdapter.notifyDataSetChanged()
//        }

        // set recycler view for players
        val players = mutableListOf<Player>()
        val playerAdapter = PlayerAdapter(players)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = playerAdapter

        playerAdapter.setOnItemClickCallback(object : PlayerAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Player) {
                sendDataToDetailPlayer(data)
            }

        })

        ApiClient().getPlayers { fetchedPlayers ->
            players.clear()
            players.addAll(fetchedPlayers)
            playerAdapter.notifyDataSetChanged()
        }

    }

    private fun initComponents() {
        usernameTxt = findViewById(R.id.showUsernameTxt)
        showTeamsBtn = findViewById(R.id.showTeamsBtn)
        showPlayersBtn = findViewById(R.id.showPlayersBtn)
        showTeamsBtnTxt = findViewById(R.id.showTeamsBtnTxt)
        showPlayersBtnTxt = findViewById(R.id.showPlayersBtnTxt)
        recyclerView = findViewById(R.id.dataTimAndPlayers)
    }

    private fun onClickHandlers() {

        showPlayersBtn.setOnClickListener {
            // change background and text color
            showPlayersBtn.setBackgroundColor(resources.getColor(R.color.color1))
            showPlayersBtnTxt.setTextColor(resources.getColor(R.color.white))
            showTeamsBtn.setBackgroundColor(resources.getColor(R.color.white))
            showTeamsBtnTxt.setTextColor(resources.getColor(R.color.color1))

            // set recycler view for teams
//            val teams = mutableListOf<Team>()
//            val teamAdapter = TeamAdapter(teams)
//            recyclerView.layoutManager = GridLayoutManager(this, 2)
//            recyclerView.adapter = teamAdapter
//
//            ApiClient().getTeams { fetchedTeams ->
//                teams.clear()
//                teams.addAll(fetchedTeams)
//                teamAdapter.notifyDataSetChanged()
//            }
        }

        showTeamsBtn.setOnClickListener {
            // change background and text color
            showTeamsBtn.setBackgroundColor(resources.getColor(R.color.color1))
            showTeamsBtnTxt.setTextColor(resources.getColor(R.color.white))
            showPlayersBtn.setBackgroundColor(resources.getColor(R.color.white))
            showPlayersBtnTxt.setTextColor(resources.getColor(R.color.color1))


        }

    }

    private fun sendDataToDetailPlayer(data: Player) {
        val intentDetail = Intent(this@MainScreenActivity, DetailPlayerActivity::class.java)
        intentDetail.putExtra(DetailPlayerActivity.DATA_PEMAIN, data)
        startActivity(intentDetail)
    }


}