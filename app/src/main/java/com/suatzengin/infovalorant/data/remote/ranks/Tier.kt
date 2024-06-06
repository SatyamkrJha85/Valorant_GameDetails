package com.suatzengin.infovalorant.data.remote.ranks

data class Tier(
    val tier: Int,
    val tierName: String,
    val division: String,
    val divisionName: String,
    val color: String,
    val backgroundColor: String,
    val smallIcon: String?,
    val largeIcon: String?,
    val rankTriangleDownIcon: String?,
    val rankTriangleUpIcon: String?,
)
