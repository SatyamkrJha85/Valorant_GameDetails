package com.suatzengin.infovalorant.data.remote.ranks

data class Ranks(
    val uuid: String,
    val assetObjectName: String,
    val tiers: List<Tier>
)
