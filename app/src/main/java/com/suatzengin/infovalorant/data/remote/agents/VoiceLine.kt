package com.suatzengin.infovalorant.data.remote.agents

data class VoiceLine(
    val minDuration: Double,
    val maxDuration: Double,
    val mediaLis: List<ValorantMedia>
)
