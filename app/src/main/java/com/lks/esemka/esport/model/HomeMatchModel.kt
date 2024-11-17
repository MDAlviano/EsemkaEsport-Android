package com.lks.esemka.esport.model

data class HomeMatchModel(
    val id: Int,
    val week: String?,
    val date: String?,
    val details: Array<HomeMatchDetailModel>?
)
