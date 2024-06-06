package com.suatzengin.infovalorant.data.remote.weapons

data class DamageRange(
    val rangeStartMeters: Double,
    val rangeEndMeters: Double,
    val headDamage: Double,
    val bodyDamage: Double,
    val legDamage: Double,
)
