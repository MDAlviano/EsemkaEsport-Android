package com.lks.esemka.esport.model

data class MatchModel(
    val matchId: Int,
    val homeTeamId: Int,
    val homeTeam: String?,
    val homeTeamLogo: String?,
    val scoreHomeTeam: Int,
    val scoreAwayTeam: Int,
    val awayTeam: String?,
    val awayTeamLogo: String?,
    val details: Array<MatchDetailModel?>
)
