package com.lks.esemka.esport.adapter

import android.app.Activity
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lks.esemka.esport.R
import com.lks.esemka.esport.model.Player
import java.net.HttpURLConnection
import java.net.URL

class PlayerAdapter(private val playerList: List<Player>): RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    class PlayerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val playerImage: ImageView = itemView.findViewById(R.id.imagePemainTxt)
        val playerName: TextView = itemView.findViewById(R.id.pemainNameTxt)
        val playerRole: TextView = itemView.findViewById(R.id.rolePemainTxt)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_pemain, parent, false)
        return PlayerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = playerList[position]
        val getPlayerImg = "http://10.0.2.2:5000/players/${player.image}"
        holder.playerName.text = player.ign
        holder.playerRole.text = player.playerRole.name

        Thread {
            try {
                // Unduh gambar dari URL
                val url = URL(getPlayerImg)
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()

                val inputStream = connection.inputStream
                val bitmap = BitmapFactory.decodeStream(inputStream)

                // Perbarui UI di thread utama
                (holder.itemView.context as Activity).runOnUiThread {
                    holder.playerImage.setImageBitmap(bitmap)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(playerList[holder.adapterPosition]) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Player)
    }

}