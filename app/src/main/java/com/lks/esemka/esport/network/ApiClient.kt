package com.lks.esemka.esport.network

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.lks.esemka.esport.model.AuthModel
import com.lks.esemka.esport.model.Player
import com.lks.esemka.esport.model.PlayerRole
import com.lks.esemka.esport.model.RegisterModel
import com.lks.esemka.esport.model.Team
import com.lks.esemka.esport.model.User
import org.json.JSONArray
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class ApiClient {

    private val base_url = "http://10.0.2.2:5000"

    // auth
    fun signIn(request: AuthModel): User? {
        val url = URL("$base_url/api/sign-in")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json; utf-8")
        connection.doOutput = true

        // build json request body
        val jsonRequest = JSONObject().apply {
            put("usernameOrEmail", request.usernameOrEmail)
            put("password", request.password)
        }

        // write request data
        OutputStreamWriter(connection.outputStream).use {
            it.write(jsonRequest.toString())
        }

        return if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            val jsonResponse = connection.inputStream.bufferedReader().use { it.readText() }
            parseUserFromJson(JSONObject(jsonResponse))
        } else {
            null
        }

    }

    fun signUp(request: RegisterModel): User? {
        return try {
            val url = URL("$base_url/api/sign-up")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.setRequestProperty("Content-Type", "application/json; utf-8")
            connection.doOutput = true

            // build json request body
            val jsonRequest = JSONObject().apply {
                put("fullName", request.fullName)
                put("username", request.username)
                put("email", request.email)
                put("phoneNumber", request.phoneNumber)
                put("password", request.password)
            }

            Log.d("SIGN-UP", "Request Data: ${jsonRequest.toString()}")

            // write request data
            OutputStreamWriter(connection.outputStream).use {
                it.write(jsonRequest.toString())
            }

            return if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val jsonResponse = connection.inputStream.bufferedReader().use { it.readText() }
                parseUserFromJson(JSONObject(jsonResponse))
            } else {
                val errorStream = connection.errorStream?.bufferedReader()?.use { it.readText() }
                Log.d(
                    "SIGN-UP",
                    "Server returned response code: ${connection.responseCode}, error: $errorStream"
                )
                null
            }
        } catch (e: Exception) {
            Log.e("SIGN-UP", e.message.toString(), e)
            null
        }


    }

    // get all
    fun getTeams(callback: (List<Team>) -> Unit) {
        thread {
            val url = URL("$base_url/api/teams")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.setRequestProperty("Content-Type", "application/json; utf-8")
            connection.connectTimeout = 5000
            connection.readTimeout = 5000
            try {
                if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                    val responseText = connection.inputStream.bufferedReader().use { it.readText() }
                    val teams = parseTeamFromJson(responseText)
                    callback(teams)
                    Log.i("GET-Teams", "Success to fetch data, code: ${connection.responseCode}")
                    Log.i("GET-Teams", "Success to fetch data, data: $teams")
                } else {
                    Log.e("GET-Teams", "Failed to fetch data, code: ${connection.responseCode}")
                }
            } catch (e: Exception) {
                Log.e("GET-Teams", "Message: ${e.message.toString()}")
            } finally {
                connection.disconnect()
            }
        }
    }

    fun getPlayers(callback: (List<Player>) -> Unit) {
        thread {
            val url = URL("$base_url/api/players")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.setRequestProperty("Content-Type", "application/json; utf-8")
            connection.connectTimeout = 5000
            connection.readTimeout = 5000

            try {
                if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                    val responseText = connection.inputStream.bufferedReader().use { it.readText() }
                    val players = parsePlayerFromJson(responseText)
                    callback(players)
                    Log.i("GET-Teams", "Success to fetch data, code: ${connection.responseCode}")
                    Log.i("GET-Teams", "Success to fetch data, data: $players")
                } else {
                    Log.e(
                        "GET-Players",
                        "Failed to fetch players, code: ${connection.responseCode}"
                    )
                }
            } catch (e: Exception) {
                Log.e(
                    "GET-Players",
                    "Message: ${e.message.toString()}, code: ${connection.responseCode}"
                )
            } finally {
                connection.disconnect()
            }
        }
    }

    private fun parseUserFromJson(jsonObject: JSONObject): User {
        return User(
            id = jsonObject.getString("id"),
            fullName = jsonObject.getString("fullName"),
            username = jsonObject.getString("username"),
            email = jsonObject.getString("email"),
            phoneNumber = jsonObject.getString("phoneNumber"),
            password = jsonObject.getString("password"),
            image = jsonObject.getString("image")
        )
    }

    private fun parseTeamFromJson(response: String): List<Team> {
        val teams = ArrayList<Team>()
        val jsonArray = JSONArray(response)
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val team = Team(
                id = jsonObject.getInt("id"),
                name = jsonObject.getString("name"),
                about = jsonObject.getString("about"),
                kills = jsonObject.getInt("kills"),
                deaths = jsonObject.getInt("deaths"),
                assists = jsonObject.getInt("assists"),
                gold = jsonObject.getInt("gold"),
                damage = jsonObject.getInt("damage"),
                lordKills = jsonObject.getInt("lordKills"),
                tortoiseKills = jsonObject.getInt("tortoiseKills"),
                towerDestroy = jsonObject.getInt("towerDestroy"),
                logo256 = jsonObject.getString("logo256"),
                logo500 = jsonObject.getString("logo500")
            )
            teams.add(team)
        }
        return teams
    }

    private fun parsePlayerFromJson(response: String): List<Player> {
        val players = ArrayList<Player>()
        val jsonArray = JSONArray(response)
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)

            val roleJSONObject = jsonObject.optJSONObject("playerRole")
            val role = if (roleJSONObject != null) {
                PlayerRole(
                    id = roleJSONObject.getInt("id"),
                    name = roleJSONObject.getString("name")
                )
            } else {
                PlayerRole(
                    id = 0,
                    name = "Unknown"
                )
            }

            val teamJSONObject = jsonObject.optJSONObject("string")
            val team = if (teamJSONObject != null) {
                Team(
                    id = teamJSONObject.optInt("id"),
                    name = teamJSONObject.optString("name"),
                    about = teamJSONObject.optString("about"),
                    kills = teamJSONObject.optInt("kills"),
                    deaths = teamJSONObject.optInt("deaths"),
                    assists = teamJSONObject.optInt("assists"),
                    gold = teamJSONObject.optInt("gold"),
                    damage = teamJSONObject.optInt("damage"),
                    lordKills = teamJSONObject.optInt("lordKills"),
                    tortoiseKills = teamJSONObject.optInt("tortoiseKills"),
                    towerDestroy = teamJSONObject.optInt("towerDestroy"),
                    logo256 = teamJSONObject.optString("logo256"),
                    logo500 = teamJSONObject.optString("logo500")
                )
            } else {
                Team(
                    id = 0,
                    name = "Unknown",
                    about = "Unknown",
                    kills = 0,
                    deaths = 0,
                    assists = 0,
                    gold = 0,
                    damage = 0,
                    lordKills = 0,
                    tortoiseKills = 0,
                    towerDestroy = 0,
                    logo256 = "Unknown",
                    logo500 = "Unknown"
                )
            }

            val player = Player(
                id = jsonObject.optInt("id"),
                playerRoleId = jsonObject.optInt("playerRoleId"),
                teamId = jsonObject.optInt("teamId"),
                fullName = jsonObject.optString("fullName"),
                ign = jsonObject.optString("ign"),
                image = jsonObject.optString("image"),
                playerRole = role,
                team = team
            )
            players.add(player)
        }
        return players
    }

}