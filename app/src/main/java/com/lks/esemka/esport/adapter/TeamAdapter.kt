package com.lks.esemka.esport.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lks.esemka.esport.R
import com.lks.esemka.esport.model.Team

class TeamAdapter(private val teamList: List<Team>): RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    class TeamViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val teamImage: ImageView = itemView.findViewById(R.id.imageTeamTxt)
        val teamName: TextView = itemView.findViewById(R.id.teamNameTxt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_team, parent, false)
        return TeamViewHolder(view)
    }

    override fun getItemCount(): Int {
        return teamList.size
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team = teamList[position]
        val teamLogo = "http://10.0.2.2:5000/logos/${team.logo500}"
        holder.teamImage.setImageURI(Uri.parse(teamLogo))
        holder.teamName.text = team.name
    }

}