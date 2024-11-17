package com.lks.esemka.esport.model

data class MatchDetailModel(
    val match: String?,
    val detailMatchId: Int,
    val homeTeamId: Int,
    val homeTeam: String?,
    val homeTeamLogo: String?,
    val scoreHomeTeam: Int,
    val scoreAwayTeam: Int,
    val awayTeamId: Int,
    val awayTeam: String?,
    val awayTeamLogo: String?,
    val startMatch: TimeSpan,
    val matchTime: TimeSpan
)
