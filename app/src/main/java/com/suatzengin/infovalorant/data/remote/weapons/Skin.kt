package com.suatzengin.infovalorant.data.remote.weapons

data class Skin(
    val uuid: String,
    val displayName: String,
    val displayIcon: String?,
    val levels: List<Level>
)
