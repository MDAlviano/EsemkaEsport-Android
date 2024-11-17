package com.lks.esemka.esport.model

data class HomeMatchDetailModel(
    val matchId: Int,
    val match: String?,
    val homeTeam: String?,
    val awayTeam: String?,
    val startMatch: TimeSpan,
    val logoHomeTeam: String?,
    val logoAwayTeam: String?
)
