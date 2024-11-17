package com.lks.esemka.esport.model

data class TimeSpan(
    val ticks: Long,
    val days: Int,
    val hours: Int,
    val milliseconds: Int,
    val microseconds: Int,
    val nanoseconds: Int,
    val minutes: Int,
    val seconds: Int,
    val totalDays: Double,
    val totalHours: Double,
    val totalMilliseconds: Double,
    val totalMicroseconds: Double,
    val totalNanoseconds: Double,
    val totalMinutes: Double,
    val totalSeconds: Double
)

