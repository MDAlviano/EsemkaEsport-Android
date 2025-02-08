package com.lks.esemka.esport.activity.main.team

import android.app.Activity
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lks.esemka.esport.R
import com.lks.esemka.esport.model.Team
import java.net.HttpURLConnection
import java.net.URL

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
        holder.teamName.text = team.name

        Thread {
            try {
                // Unduh gambar dari URL
                val url = URL(teamLogo)
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()

                val inputStream = connection.inputStream
                val bitmap = BitmapFactory.decodeStream(inputStream)

                // Perbarui UI di thread utama
                (holder.itemView.context as Activity).runOnUiThread {
                    holder.teamImage.setImageBitmap(bitmap)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }

}